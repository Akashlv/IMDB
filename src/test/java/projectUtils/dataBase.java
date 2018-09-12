package projectUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Reporter;

public class dataBase {

	Connection conn = null;
	Statement stmt = null;


	public void deleteDBIfExists(String Connectionurl,String DBname) {

		File file = new File(DBname + ".db");
		System.out.println(System.getProperty("user.dir"));

		if (file.exists()) // here's how to check
		{
			file.delete();
			System.out.print("This database by name " + DBname + " already exists So deleting it.");

		} else {

			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(Connectionurl);
				System.out.println("Opened database successfully");

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}

	}

	public void connectToDB(String Connectionurl) {

		System.out.println(System.getProperty("user.dir"));

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(Connectionurl);
			Reporter.log("Successfully connected to Sqlite.", true);

		} catch (Exception e) {
			Reporter.log("Couldn't connect to Sqlite...!!!", true);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	public void createTableInDB(String Connectionurl, String DBname, String tabelName, String Query) {

		try {
			System.out.println(Query);

			conn = DriverManager.getConnection(Connectionurl + DBname + ".db");
			stmt = conn.createStatement();

			// Drops the table if it already exists.
			String tableDropQuery = "DROP TABLE IF EXISTS '" + tabelName + "'";
//			Reporter.log(tableDropQuery, true);
			stmt.executeUpdate(tableDropQuery);

			// Creating the table
			stmt.executeUpdate(Query);
			stmt.close();
			conn.close();
			Reporter.log("Create table Query : " + Query, true);
			Reporter.log("Successfully created table by name : " + tabelName);

		} catch (Exception e) {
			Reporter.log("Failed to create table, Please check the code...!!! ", true);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void inserIntoDB(String Connectionurl, String DBname, String tabelName, String Query) {

		try {

			conn = DriverManager.getConnection(Connectionurl + DBname + ".db");
			stmt = conn.createStatement();
			stmt.executeUpdate(Query);
			stmt.close();
			conn.close();
			Reporter.log("Insert to table Query : " + Query, true);
			Reporter.log("Successfully Inserted data into table : " + tabelName);
			System.out.println("Successfully inserted data into table by executing Query : " + Query);

		} catch (Exception e) {
			Reporter.log("Failed to Insert data into table, Please check the code...!!! ", true);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void selectFromDB(String Connectionurl, String DBname, String Query) {

		try {
			conn = DriverManager.getConnection(Connectionurl + DBname + ".db");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			Reporter.log("<table>", true);
			// loop through the result set
			
			while (rs.next()) {				
				String id = rs.getString("id");
				String name = rs.getString("filmname");
				String rating = rs.getString("rating");
				String yearOfRelease = rs.getString("yearofrelease");
		
				Reporter.log("<tr><td>" + id + "</td><td>" + name + "</td><td>" + rating + "</td><td>" + yearOfRelease +"</td></tr>",true);
			
			}
			Reporter.log("</table>", true);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
