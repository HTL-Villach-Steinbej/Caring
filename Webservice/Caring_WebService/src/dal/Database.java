package dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.JsonElement;

import bll.Fahrzeug;
import bll.Point;
import bll.Rent;
import bll.Schaden;
import bll.SchadenRent;
import bll.SchadenUser;
import bll.User;
import bll.Zone;

public class Database {
	private static Database instance = null;
	private Connection con = null;

	private Database() {
	}

	public static Database newInstance() {
		if (Database.instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private void createCon() {
		 try {
		      String myDriver = "oracle.jdbc.driver.OracleDriver";
		      //212.152.179.117
		      //10.0.6.111
		      String myUrl = "jdbc:oracle:thin:@212.152.179.117:1521:ora11g";
		      Class.forName(myDriver);
		      this.con= DriverManager.getConnection(myUrl, "d5a21", "d5a");
		} catch (Exception e) {
			System.err.println("Got an exception when connecting to DB! ");
			System.err.println(e.getMessage());
		}
	}

	private void closeCon() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Schaden
	public ArrayList<SchadenUser> getDamages() {
		ArrayList<SchadenUser> result = new ArrayList<SchadenUser>();
		try {
			createCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select  sid, ms_uid,ms_fid,bezeichnung,beschreibung,datum from Schaden inner join meldetschaden on sid = ms_sid");
			while (rs.next())
				result.add(new SchadenUser(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6)));
			closeCon();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	public Schaden getDamgage(int _id) throws Exception {
		Schaden schaden = null;
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("select * from Schaden Where sid=?");
			stmt.setInt(1, _id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			schaden = new Schaden(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			throw new Exception("no Damage found with id: " + _id);
		}

		return schaden;
	}
	public void setDamage(Schaden schaden) throws Exception {
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("INSERT INTO Schaden VALUES (?,?)");
			stmt.setInt(1, schaden.getId());
			stmt.setString(2, schaden.getBezeichnung());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Damage with id " + schaden.getId() + "already existst;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	public void updateDamage(Schaden schaden) throws Exception {
		// TODO Auto-generated method stub
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("Update Schaden SET Bezeichnung=? Where sid = ?");
			stmt.setInt(2, schaden.getId());
			stmt.setString(1, schaden.getBezeichnung());
			int count = stmt.executeUpdate();
			if(count == 0) throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Damage with id " + schaden.getId() + " not found;");
		}  finally {
			closeCon();
		}

	}
	public void deleteDamage(int id) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("Delete From Schaden Where sid = ?");
			//Achtung Verbindung zu einer anderen Tabelle kann nicht gelöscht werden wahrscheinlich
			//Research anstehend 
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Damage with id " + id + "not found;"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}

	//Farhzeuge
	
	public ArrayList<Fahrzeug> getCars() {
		ArrayList<Fahrzeug> result = new ArrayList<Fahrzeug>();
		try {
			createCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT  fid, bezeichnung  , marke, laufleistung , f.geo_point.SDO_POINT.X as X, f.geo_point.SDO_POINT.Y as Y FROM Fahrzeug f");
			while (rs.next())
				result.add(new Fahrzeug(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),new Point(rs.getDouble(5),rs.getDouble(6))));
			closeCon();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	public Fahrzeug getCar(int _id) throws Exception {
		Fahrzeug car = null;
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("SELECT  fid, bezeichnung  , marke, laufleistung , f.geo_point.SDO_POINT.X as X, f.geo_point.SDO_POINT.Y as Y FROM Fahrzeug f Where fid = ?");
			stmt.setInt(1, _id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			car = new Fahrzeug(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),new Point(rs.getDouble(5),rs.getDouble(6)));
		} catch (SQLException e) {
			throw new Exception("no car found with id: " + _id);
		}

		return car;
	}
	public void setCar(Fahrzeug fahrzeug) throws Exception {
		try {
			createCon();
			PreparedStatement stmt = null;
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select Max(fid) from fahrzeug");
			int fid=0;
			while (rs1.next())
			 fid= rs1.getInt(1)+1;
			
			System.out.println(fid);
			con.setAutoCommit(true);
			stmt = con.prepareStatement("insert into fahrzeug values(?,?,?,?, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(?, ?, NULL),NULL,NULL)) ");
			stmt.setInt(1, fid);
			stmt.setString(2, fahrzeug.getBezeichnung());
			stmt.setString(3, fahrzeug.getMarke());
			stmt.setInt(4,fahrzeug.getLaufleistung());
			stmt.setDouble(5, fahrzeug.getLocation().getX());
			stmt.setDouble(6, fahrzeug.getLocation().getY());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Car with id " + fahrzeug.getId() + "already existst;"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	public void updateCar(Fahrzeug car) throws Exception {
		// TODO Auto-generated method stub
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("Update Fahrzeug SET bezeichnung = ?, marke = ?,laufleistung = ?, geo_point = SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(?, ?, NULL),NULL,NULL) WHERE fid = ?");
			stmt.setInt(6, car.getId());
			stmt.setString(1, car.getBezeichnung());
			stmt.setString(2, car.getMarke());
			stmt.setInt(3, car.getLaufleistung());
			stmt.setDouble(4,car.getLocation().getX());
			stmt.setDouble(5, car.getLocation().getY());
			
			int count = stmt.executeUpdate();
			if(count == 0) throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Damage with id " + car.getId() + " not found;");
		}  finally {
			closeCon();
		}

	}
	public void deleteCar(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("Delete From Fahrzeug Where fid=?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Car with id " + id + "not found;"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	//Zonen
	
	public ArrayList<Zone> getZonen() {
		ArrayList<Zone> result = new ArrayList<Zone>();
		try {
			createCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Zone");
			while (rs.next())
				result.add(new Zone(rs.getInt(1), rs.getString(2),rs.getInt(3)));
			closeCon();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	public Zone getZone(int _id) throws Exception {
		Zone zone = null;
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("select * from Zone Where zid=?");
			stmt.setInt(1, _id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			zone = new Zone(rs.getInt(1), rs.getString(2),rs.getInt(3));
		} catch (SQLException e) {
			throw new Exception("no Zone found with id: " + _id);
		}

		return zone;
	}
	public void setZone(Zone zone) throws Exception {
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("insert into zone values(?,?,?)");
			stmt.setInt(1, zone.getId());
			stmt.setString(2, zone.getBezeichnung());
			stmt.setInt(3,zone.getKostensatz());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Zone with id " + zone.getId() + "already existst;"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	public void updateZone(Zone zone) throws Exception {
		// TODO Auto-generated method stub
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("Update Zone SET Bezeichnung=?,Kostensatz=? Where zid = ?");
			stmt.setInt(3, zone.getId());
			stmt.setString(1, zone.getBezeichnung());
			stmt.setInt(2, zone.getKostensatz());
			int count = stmt.executeUpdate();
			if(count == 0) throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Zone with id " + zone.getId() + " not found;");
		}  finally {
			closeCon();
		}

	}
	public void deleteZone(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			createCon();
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			con.setAutoCommit(true);
			//stmt = con.prepareStatement("Delete from leiht_aus Where la_zid=?");
			stmt1 = con.prepareStatement("Delete From Zone Where zid=?");
			
			//stmt.setInt(1, id);
			stmt1.setInt(1, id);
			
			//stmt.execute();
			stmt1.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Zone with id " + id + "not found;"+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	//Rents
	
	public ArrayList<Rent> getRents() {
		ArrayList<Rent> result = new ArrayList<Rent>();
		
		Calendar c1 = null;
		Calendar c2 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		
		
		try {
			createCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from leiht_aus");
			while (rs.next()) {
				c1 = Calendar.getInstance();
			c1.setTime(rs.getTimestamp(5));
			c2 = Calendar.getInstance();
			c2.setTime(rs.getTimestamp(6));
			System.out.println(c2);
				
				result.add(new Rent(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getInt(4),c1.getTime(),c2.getTime()));
			}
			closeCon();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
			
		public Rent getRent(int _id) throws Exception {
			Rent rent = null;
			Calendar c1 = null;
			Calendar c2 = null;
			try {
				createCon();
				PreparedStatement stmt = null;
				stmt = con.prepareStatement("SELECT * FROM leiht_aus  Where lid = ?");
				stmt.setInt(1, _id);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				c1 = Calendar.getInstance();
				c1.setTime(rs.getTimestamp(5));
				c2 = Calendar.getInstance();
				c2.setTime(rs.getTimestamp(6));
				rent = new Rent(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getInt(4),c1.getTime(),c2.getTime());
			} catch (SQLException e) {
				throw new Exception("no rent found with id: " + _id);
			}

			return rent;
		}
		public int setRent(Rent rent) throws Exception {
			int lid=1;

			try {
				Calendar c1 = null;
				Calendar c2 = null;
				
				createCon();
				PreparedStatement stmt = null;
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select Max(lid) from leiht_aus");
				while (rs1.next())
				 lid= rs1.getInt(1)+1;
				con.setAutoCommit(true);
				stmt = con.prepareStatement("insert into leiht_aus values(?,?,?,?,?,?) ");
				stmt.setInt(1, lid);
				stmt.setInt(2, rent.getFid());
				stmt.setString(3, rent.getUid());
				stmt.setInt(4,rent.getZid());
				
				/*c1 = Calendar.getInstance();
				c1.setTime(rent.getVon());
				c2 = Calendar.getInstance();
				c2.setTime(rent.getBis());
				
		        java.sql.Date sDateVON = new  java.sql.Date(c1.getTimeInMillis()); 
		        java.sql.Date sDateBIS = new  java.sql.Date(c2.getTimeInMillis()); */
				Timestamp sDateVON = new Timestamp(rent.getVon().getTime());
				Timestamp sDateBIS = new Timestamp(rent.getBis().getTime());

		       
				stmt.setTimestamp(5,sDateVON );
				stmt.setTimestamp(6,sDateBIS);
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("Rent with id " + rent.getId() + "already existst;"+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				closeCon();
			}
			
			return lid;
		}
		public void updateRent(Rent	 rent) throws Exception {
			// TODO Auto-generated method stub
			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				stmt = con.prepareStatement("Update leiht_aus SET von = ?, bis = ? WHERE lid = ?");
				
				Timestamp sDateVON = new Timestamp(rent.getVon().getTime());
				Timestamp sDateBIS = new Timestamp(rent.getBis().getTime());
				
				stmt.setTimestamp(1, sDateVON);
				stmt.setTimestamp(2, sDateBIS);
				stmt.setInt(3, rent.getId());
				
				int count = stmt.executeUpdate();
				if(count == 0) throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("Rent with id " + rent.getId() + " not found;"+e.getMessage());
			}  finally {
				closeCon();
			}

		}
		public void deleteRent(int id) throws Exception {
			// TODO Auto-generated method stub
			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				stmt = con.prepareStatement("Delete From leiht_aus Where lid=?");
				stmt.setInt(1, id);
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("Rent with id " + id + "not found;"+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeCon();
			}
		}
		public ArrayList<SchadenRent> getDamagesFromRent(int id){
			ArrayList<SchadenRent> result = new ArrayList<SchadenRent>();
			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				stmt = con.prepareStatement("Select sid,lid,kosten,bezeichnung From verursacht_schaden left join schaden on sid = vs_sid  Where lid=?");
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next())
					result.add(new SchadenRent(rs.getInt(2), rs.getInt(1) ,rs.getInt(3), rs.getString(4)));
				closeCon();	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return result;
		}
		public void createDamageFromRent(SchadenRent schadenrent) throws Exception {
			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				
				PreparedStatement stmt1 = null;
				stmt1 = con.prepareStatement("insert into schaden values(?,?) ");
				stmt1.setInt(1, schadenrent.getId());
				stmt1.setString(2,schadenrent.getBezeichnung());
				stmt1.execute();
				
				stmt = con.prepareStatement("insert into verursacht_schaden values(?,?,?) ");
				stmt.setInt(1, schadenrent.getLid());
				stmt.setInt(2, schadenrent.getId());
				stmt.setInt(3, schadenrent.getKosten());
				stmt.execute();
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("Rent with id " + schadenrent.getId() + "already existst;"+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeCon();
			}
		}
		public void removeDamageFromRent(int r_id,int d_id) throws Exception {
			// TODO Auto-generated method stub
			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				stmt = con.prepareStatement("Delete From verursacht_schaden Where lid=? AND vs_sid=?");
				stmt.setInt(1, r_id);
				stmt.setInt(2, d_id);
				stmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("User with id " + r_id + "not found;"+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeCon();
			}
		}
		//User
		
		public ArrayList<User> getUsers() {
				ArrayList<User> result = new ArrayList<User>();
				try {
					createCon();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from users");
					while (rs.next())
						result.add(new User(rs.getString(1)));
					closeCon();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return result;
			}
				
		public User getUser(String _id) throws Exception {
				User user = null;
				try {
					createCon();
					PreparedStatement stmt = null;
					con.setAutoCommit(true);
					stmt = con.prepareStatement("SELECT * FROM users  Where u_id = ?");
					stmt.setString(1, _id);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					user = new User(rs.getString(1));
				} catch (SQLException e) {
					throw new Exception("no User found with id: " + _id+e.getMessage());
				}

				return user;
			}
		
		public void setUser(User user) throws Exception {
				try {
					createCon();
					PreparedStatement stmt = null;
					con.setAutoCommit(true);
					stmt = con.prepareStatement("insert into users values(?) ");
					stmt.setString(1, user.getId());
					
					stmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new Exception("User with id " + user.getId() + "already existst;");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeCon();
				}
			}
		
		/* Vielleicht noch benötigt
			public void updateUser(Rent	 rent) throws Exception {
				// TODO Auto-generated method stub
				try {
					createCon();
					PreparedStatement stmt = null;
					con.setAutoCommit(true);
					stmt = con.prepareStatement("Update leiht_aus SET von = ?, bis = ? WHERE lid = ?");
					
					stmt.setDate(1, rent.getVon());
					stmt.setDate(2, rent.getBis());
					stmt.setInt(3, rent.getId());
					
					int count = stmt.executeUpdate();
					if(count == 0) throw new Exception();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("Rent with id " + rent.getId() + " not found;"+e.getMessage());
				}  finally {
					closeCon();
				}

			}*/
		
		public void deleteUser(String id) throws Exception {
				// TODO Auto-generated method stub
				try {
					createCon();
					PreparedStatement stmt = null;
					con.setAutoCommit(true);
					stmt = con.prepareStatement("Delete From users Where u_id=?");
					stmt.setString(1, id);
					stmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new Exception("User with id " + id + "not found;"+e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeCon();
				}
			}
			
		public void createDamageFromUser(SchadenUser schadenuser) throws Exception {
			int sid=0;

			try {
				createCon();
				PreparedStatement stmt = null;
				con.setAutoCommit(true);
				
				Statement stmt2 = con.createStatement();
				ResultSet rs1 = stmt2.executeQuery("select Max(sid) from schaden");
				while (rs1.next())
				 sid= rs1.getInt(1)+1;
				
				
				PreparedStatement stmt1 = null;
				stmt1 = con.prepareStatement("insert into schaden values(?,?,?,?) ");
				stmt1.setInt(1, sid);
				stmt1.setString(2,schadenuser.getBezeichnung());
				stmt1.setString(3,schadenuser.getBeschreibung());
				stmt1.setDate(4, schadenuser.getDatum());
				stmt1.execute();
				
				stmt = con.prepareStatement("insert into meldetSchaden values(?,?,?) ");
				stmt.setString(1, schadenuser.getUd());
				stmt.setInt(2, sid);
				stmt.setInt(3, schadenuser.getFid());
				stmt.execute();
				
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("Rent with id " + sid + "already existst;"+e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeCon();
			}
		}
	
}
