/**
 * @author Ashley.Ridout
 * @date July 23, 2017
 */
package homework3;

//import all classes in the io package and the Scanner class in the util package
import java.io.*;
import java.util.Scanner;

//class to test all the vowels within a user inputed text file are counted and stored by vowel in a TreeMap
public class VowelCounterTest {
	
	public static void main(String args[]) throws IOException {
		//try statement for user to enter a valid file name
		try {
			System.out.println("Please enter the name of the file to count the number of vowels.");

			//finds the file and string within that the user inputed if it is a valid file name
			Scanner fileScan = new Scanner(System.in);
			String fileName = fileScan.nextLine();
			File file = new File(fileName);
			Scanner input = new Scanner(file);

			//converts scanner text within file to a VowelCounter object
			VowelCounter vc = new VowelCounter(input);
			//method to print the count of total vowels and the TreeMap with each individual vowel's count
			vc.countVowels();
			
			//close resources
			fileScan.close();
			input.close();
			
		//catch if user inputs an invalid file name and prints location of error
		} catch (IOException e) {
			System.out.println("Please enter a valid file name.");
			e.printStackTrace();
		}
	}
}
