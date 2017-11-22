/**
 * @author Ashley Ridout
 * @date August 5, 2017
 * @references	Boston University CS 622 Summer 2 2017 Module 5 
 * 				
 */

package homework5;

//import all sql packages
import java.sql.*;
import java.util.Vector;

//class to access the database and run SQL queries
public class EmployeePromotedSQL {
	public static void main(String[] args) {
		// try connecting to database
		try {
			// get database connection and store as 'connect' variable
			Connection connect = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\ashley.ridout\\Documents\\db-derby-10.13.1.1-bin\\bin\\employeedb", "Ashley",
					"Ridout");

			// output to console that the database connected
			System.out.println("Database connected.\n");

			// create statements
			Statement statement = connect.createStatement();
			Statement statement2 = connect.createStatement();

			/**
			 * drop promoted table if previously exists
			 */
			statement.executeUpdate("drop table promoted");

			// create promoted table
			statement.executeUpdate("create table promoted (id int not null, " + "first_name varchar (50) not null, "
					+ "last_name varchar (50) not null, new_salary int not null, primary key (id))");

			// prepared statement to choose promotional salary and years in
			// service
			PreparedStatement salaryService = connect
					.prepareStatement("Select * from employee where salary >= ? " + "AND years_in_service >= ?");
			salaryService.setInt(1, 100000);
			salaryService.setInt(2, 5);

			// create new ResultSet to hold employees who will be promoted
			ResultSet promote = salaryService.executeQuery();

			// manipulate data from employee table
			while (promote.next()) {
				int pid = promote.getInt(1);
				String name = promote.getString(2);
				// split name into first, optional middle and last name array
				String[] nameArray = name.split("\\s+");
				// get first name at index zero
				String firstName = nameArray[0];
				// get last name after the last index of blank space
				String lastName = name.substring(name.lastIndexOf(" ") + 1);

				// equation to get new salary after promotion
				int newSalary = (int) (promote.getInt(3) * 1.1);
				// print the promoted employees first name, last name and new salary
				System.out.println(firstName + " " + lastName + "'s new salary is " + newSalary);

				// insert data into promoted table
				statement2.executeUpdate("INSERT into promoted Values(" + pid + ", '" + firstName + "', '" + lastName
						+ "', " + newSalary + ")");
			}

			// output to console
			System.out.println("\nThe promoted table: ");

			// select all columns from promoted
			ResultSet pro = statement2.executeQuery("Select * from promoted");
			// get metadata on promoted table
			ResultSetMetaData metaData = pro.getMetaData();

			// variables for printing promoted table
			int i = 0;
			Vector<String> columnNames = new Vector<String>();

			// print column names for first query
			while (i < metaData.getColumnCount()) {
				i++;
				System.out.print(metaData.getColumnName(i) + " ");
				columnNames.add(metaData.getColumnName(i));
			}
			System.out.println("\n");

			// print data from promoted table
			while (pro.next()) {
				for (i = 0; i < columnNames.size(); i++) {
					System.out.print(pro.getString(columnNames.get(i)) + "\t");
				}
				System.out.print("\n");
			}

			// create EmployeePromotedSQL object
			EmployeePromotedSQL emp = new EmployeePromotedSQL();

			// execute getPercentPromoted method
			emp.getPercentPromoted();

			// execute getAverageSalary method
			emp.getAverageSalary();

			// close resources
			promote.close();
			pro.close();
			salaryService.close();
			statement.close();
			statement2.close();
			connect.close();
			// catch SQL exception and print location of error
		} catch (SQLException sq) {
			System.out.println("SQL exception caught.");
			sq.printStackTrace();
		}
	}

	/**
	 * Method to get the percentage of employees promoted
	 */
	public void getPercentPromoted() {
		try {
			// get database connection and store as 'conn' variable
			Connection conn = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\ashley.ridout\\Documents\\db-derby-10.13.1.1-bin\\bin\\employeedb", "Ashley",
					"Ridout");

			// create statements
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();

			// create ResultSets to hold queries
			ResultSet empCount = stmt.executeQuery("Select COUNT(ID) from employee");
			ResultSet proCount = stmt2.executeQuery("Select COUNT(ID) from promoted");

			// get the count of all employees and promoted employees
			while (empCount.next() && proCount.next()) {
				double empInt = empCount.getInt(1);
				double proInt = proCount.getInt(1);

				// equate the percentage of employees promoted
				double percent = 100 * (proInt / empInt);
				// ouput to console
				System.out.println("\nThe percent of employees beings promoted is " + percent);

			}
			// catch SQL exception within the getPercentPromoted method
		} catch (SQLException ex) {
			System.out.println("SQLException caught in getPercentPromoted method.");
			ex.printStackTrace();
		}
	}

	/**
	 * Method to get average salary of promoted employees
	 */
	public void getAverageSalary() {
		try {
			// get database connection and store as 'conn' variable
			Connection connAve = DriverManager.getConnection(
					"jdbc:derby:C:\\Users\\ashley.ridout\\Documents\\db-derby-10.13.1.1-bin\\bin\\employeedb", "Ashley",
					"Ridout");

			// create statement
			Statement state = connAve.createStatement();

			// create ResultSets to hold queries
			ResultSet aveSalary = state.executeQuery("Select AVG(new_salary) from promoted");

			// get new average salary of promoted employees
			while (aveSalary.next()) {
				System.out.println("The average new salary of promoted employees is " + aveSalary.getInt(1));
			}
			// catch SQL exception and print location of error
		} catch (SQLException sql) {
			System.out.println("SQLException caught in getPercentPromoted method.");
			sql.printStackTrace();
		}
	}
}
