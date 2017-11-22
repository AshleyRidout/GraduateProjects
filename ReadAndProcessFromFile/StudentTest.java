/**
 * @author Ashley.Ridout
 * @date July 29, 2017
 */
package homework4;

//import all classes with io package
import java.io.*;
import java.util.Scanner;

//class to test Student objects are created and outputted to a binary file 
public class StudentTest {

	public static void main(String[] args) {
		// try statement for user to enter a file name to hold Student Objects
		try {
			System.out.println("Please enter the name of the file to store student information.");

			// uses the inputted text to create a file with this name
			Scanner fileScan = new Scanner(System.in);
			String fileName = fileScan.nextLine();
			File file = new File(fileName);

			// try statement to create a new ObjectOutputStream file named as the inputted text
			try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream(file));) {
				// create Student objects and write them to the file
				Student A = new Student(101, "George", 95);
				outfile.writeObject(A);
				Student B = new Student(102, "Anna", 99);
				outfile.writeObject(B);
				Student C = new Student(103, "Megan", 84);
				outfile.writeObject(C);
				Student D = new Student(104, "Brian", 88);
				outfile.writeObject(D);
				Student E = new Student(105, "Kelly", 75);
				outfile.writeObject(E);

				// close scanner
				fileScan.close();

				// output to the console
				System.out.print(
						"The objects written to the file are:\n" + A + "\n" + B + "\n" + C + "\n" + D + "\n" + E);
			}

			// catch if user inputs an invalid file name and prints location of error
		} catch (IOException e) {
			System.out.println("Please enter a valid file name.");
			e.printStackTrace();
		}
	}
}
