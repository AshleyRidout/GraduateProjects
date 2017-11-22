/**
 * @author Ashley.Ridout
 * @date July 31, 2017
 */
package homework4;

//import java classes
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//class to test Student Objects are inputted and the average score of the students is found
public class StudentAverageTest {

	public static void main(String[] args) {
		// try statement for user to enter a file name to input data
		try {
			System.out.println("Please enter the name of the file to input student information.");

			// uses the inputted text to create a file with this name
			Scanner fileScan = new Scanner(System.in);
			String fileName = fileScan.nextLine();
			File file = new File(fileName);

			// try with resources the ObjectInputStream for the file name
			try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream(file));) {

				// initialize variables
				int count = 0;
				int scores = 0;

				// create Integer ArrayList to hold scores from the file
				ArrayList<Integer> scoreList = new ArrayList<>();

				// output to console
				System.out.println("The scores from the students within the file are:");

				// while input stream is true
				while (true) {
					// read in Student objects and assign to students variable
					Student students = (Student) infile.readObject();
					// get score from Student objects and assign to scores variable
					scores = students.getScore();
					// add scores to ArrayList scoreList
					scoreList.add(scores);
					// count each loop
					count++;

					// output to console to show score for each student
					System.out.println("Student number " + count + ": score is " + scores);

					// output to console to show average
					// getAverage returns the average of the currently read-in student scores
					System.out.println(
							"The average score of these students is " + String.format("%.2f", getAverage(scoreList)));

					// close resources
					fileScan.close();
				}
			}
			// catch end of file exception and print appropriate message
		} catch (EOFException eof) {
			System.out.println("End of file reached.");

			// catch file not found exception, print appropriate message and
			// location of error
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found.");
			fnf.printStackTrace();

			// catch input/output exception, print appropriate message and
			// location of error
		} catch (IOException ioe) {
			System.out.println("Problem with handling input/output.");

			// catch class not found exception, print appropriate message and
			// location of error
		} catch (ClassNotFoundException cnf) {
			System.out.println("Cannot found the Student class.");
			cnf.printStackTrace();
		}
	}

	/**
	 * The getAverage method finds the average of the student scores within an
	 * ArrayList
	 * 
	 * @param sum
	 *            is the double sum of the Student scores
	 * @param score
	 *            is the Integer current score within the for loop
	 * @param scoreList
	 *            is the Integer ArrayList
	 * @param scoreList.size
	 *            is the current size of the ArrayList by which the sum should
	 *            be divided to return the correct average
	 * @return ave is the average of the Student scores
	 *
	 */
	private static double getAverage(ArrayList<Integer> scoreList) {
		double sum = 0;
		double ave = 0;
		for (Integer score : scoreList) {
			sum += score;
			ave = sum / scoreList.size();
		}
		return ave;
	}
}
