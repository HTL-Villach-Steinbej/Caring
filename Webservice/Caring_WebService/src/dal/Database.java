package dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.google.gson.JsonElement;

import bll.Fahrzeug;
import bll.Point;
import bll.Schaden;

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
	public ArrayList<Schaden> getDamages() {
		ArrayList<Schaden> result = new ArrayList<Schaden>();
		try {
			createCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Schaden");
			while (rs.next())
				result.add(new Schaden(rs.getInt(1), rs.getString(2)));
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
			stmt = con.prepareStatement("Delete From Schaden Where sid=?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Damage with id " + id + "not found;");
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
			con.setAutoCommit(true);
			stmt = con.prepareStatement("insert into fahrzeug values(?,?,?,?, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(?, ?, NULL),NULL,NULL)) ");
			stmt.setInt(1, fahrzeug.getId());
			stmt.setString(2, fahrzeug.getBezeichnung());
			stmt.setString(3, fahrzeug.getMarke());
			stmt.setInt(4,fahrzeug.getLaufleistung());
			stmt.setDouble(5, fahrzeug.getLocation().getX());
			stmt.setDouble(6, fahrzeug.getLocation().getY());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Car with id " + fahrzeug.getId() + "already existst;");
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
			stmt = con.prepareStatement("Delete From fahrzeug Where fid=?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Car with id " + id + "not found;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon();
		}
	}
	
	

	
}
