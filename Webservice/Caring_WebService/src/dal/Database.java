package dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.google.gson.JsonElement;

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
		      String myUrl = "jdbc:oracle:thin:@10.0.6.111:1521:ora11g";
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
	public ArrayList<Schaden> getSchoolEventList() {
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
	public Schaden getSchaden(int _id) throws Exception {
		Schaden schaden = null;
		try {
			createCon();
			PreparedStatement stmt = null;
			con.setAutoCommit(true);
			stmt = con.prepareStatement("select * from Schaden Where id=?");
			stmt.setInt(1, _id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			schaden = new Schaden(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			throw new Exception("no schaden found with id: " + _id);
		}

		return schaden;
	}

	
	

	
}
