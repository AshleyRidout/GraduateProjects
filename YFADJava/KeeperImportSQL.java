/**
 * @author Ashley Ridout
 * @date August 5, 2017
 * @references	Boston University CS 622 Summer 2 2017 Module 5 
 * 				
 */

package cs622Project;

// import all sql packages
import java.sql.*;
import java.util.ArrayList;

// class to access the database and run SQL queries
public class KeeperImportSQL{

	// empty constructor
	public KeeperImportSQL (){};
	
	// static method to import the YFAD database
	public static ArrayList<KeeperItem> importDatabase () {
			
		ArrayList<KeeperItem> askerSQL = new ArrayList<KeeperItem>();
		
		// try connecting to database
		try {
			// get database connection and store as 'connect' variable
			Connection connect = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\ashley.ridout\\Documents\\db-derby-10.13.1.1-bin\\bin\\YFADdb", "Ashley",
					"Ridout");

			// output to console that the database connected
			System.out.println("\nDatabase connected.\n");

			// create statement
			Statement statement = connect.createStatement();

			// get rental_item, price and quantity from keeper table
			ResultSet rentalItem = statement.executeQuery("Select rental_item, price, quantity from keeper");

			
			// print number of rental items available
			  while (rentalItem.next()) {
				  KeeperItem keep = new KeeperItem();
		            keep.setName(rentalItem.getString("rental_item"));
		            keep.setPrice(rentalItem.getDouble("price"));
		            keep.setQuantity(rentalItem.getInt("quantity"));
		            askerSQL.add(keep);
			  }     

			// close resources
			statement.close();
			connect.close();
			// catch SQL exception and print location of error
		} catch (SQLException sq) {
			System.out.println("SQL exception caught.");
			sq.printStackTrace();
			// return ArrayList with KeeperItems from database
		} return askerSQL;
	}
	
}
