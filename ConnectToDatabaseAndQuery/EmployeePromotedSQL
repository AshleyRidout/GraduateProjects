/**
 * @author Ashley Ridout
 * @date August 5, 2017
 * @references	Boston University CS 622 Summer 2 2017 Module 5 
 * 				
 */
package homework5;

//import all java sql & util packages
import java.sql.*;
import java.util.*;

//class to access the database and run SQL queries
public class EmployeeSQL {
	public static void main(String[] args) {
		// try connecting to database
		try {
			// get database connection and store as 'connect' variable
			Connection connect = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\ashley.ridout\\Documents\\db-derby-10.13.1.1-bin\\bin\\employeedb",
					"Ashley", "Ridout");
			
			// output to console that database connected
			System.out.println("Database connected.\n");
			
			// create statement
			Statement statement = connect.createStatement();
			// execute first query and store as highGradeResults variable
			
			ResultSet firstName = statement
							.executeQuery("Select SUBSTR(full_name, 1, LOCATE(' ', full_name, 1)) from employee");
				
			//output to console
			System.out.println("The first name of the employees is ");
			while (firstName.next()){
			System.out.println(firstName.getString(1));
			}
			System.out.print("\n");
			
			//output to console
			System.out.println("The last name of the employees is ");
			
			ResultSet middleAndLastName = statement
					.executeQuery("Select full_name from employee");
							//"Select SUBSTR(SUBSTR(full_name, LOCATE(' ', full_name)), LOCATE(' ', SUBSTR(full_name, LOCATE(' ', full_name, 1)))) from employee");
							//"Select SUBSTR(SUBSTR(full_name, LOCATE(' ', full_name)), LOCATE(' ',SUBSTR(full_name, LOCATE(' ', full_name, 1)), 1)) from employee");
			
			
			while (middleAndLastName.next()){
				String midlast = middleAndLastName.getString(1);
				midlast.split(" ");
				String lastName = midlast.substring(midlast.lastIndexOf(" ") + 1);
				System.out.println(lastName);
			}
			
			
			statement.executeUpdate("INSERT INTO promoted(id, first_name, last_name, new_salary) Select id, firstname, SUBSTR(SUBSTR(full_name, LOCATE(' ', full_name)), LOCATE(' ', SUBSTR(full_name, LOCATE(' ', full_name, 1)))), salary FROM employee");
			//statement.executeQuery("Select * from promoted");
			
			//close Statement
			statement.close();
			//close database connection
			connect.close();
			
		//catch SQL Exception and print location of error
		}catch (SQLException sq){
			System.out.println("SQL exception caught.");
			sq.printStackTrace();
		}
	}
}
