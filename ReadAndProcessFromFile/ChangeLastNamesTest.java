/**
 * @author Ashley.Ridout
 * @date July 17, 2017
 */

package homework2;

//import java classes to process input and produce output
import java.io.*;

//class to test 'e' and 'a' in last names are changed to 'E' and 'A', and print the total number of last names
public class ChangeLastNamesTest {

	public static void main(String[] args) {

		try {
			//String names to hold last names text from LastName.txt
			String names;
			//LastNames Object to invoke changeEA() and numberOfLastNames() methods
			ChangeLastNames ln = new ChangeLastNames();

			//rename LastName.txt to an easier variable "file"
			File file = new File("LastName.txt");
			//name file reader fr
			FileReader fr = new FileReader(file);
			//name buffered reader br
			BufferedReader br = new BufferedReader(fr);

			//assign 'names' to text in br and do the following while not null
			while ((names = br.readLine()) != null) {
				//if names is not null
				//if (names != null) {
					//print original file to console
					System.out.println("Original last names in LastName.txt file: " + names);
					
					//change 'e' and 'a' in last names to 'E' and 'A' and print last names with this change to console
					String lastNames = ln.changeEA(names);
					System.out.print("Last names with 'e' and 'a' changed to 'E' and 'A': " + lastNames);
					
					//find number of last names and print to console
					String numberOfNames = ln.NumberOfLastNames(lastNames);
					System.out.print(numberOfNames);
					
					//PrintWriter object outfile creates "UpdatedLastName.txt"
					PrintWriter outFile = new PrintWriter("UpdatedLastName.txt");
					//print last names with 'E' and 'A' updates to UpdatedLastName.txt
					outFile.println(lastNames);
					//print number of last names to UpdatedLastName.txt
					outFile.println(numberOfNames);
					
					outFile.close(); //close print writer outfile
				//} 
			} br.close(); //close buffered reader br
			fr.close();
		}

		//catch any input/output exception and print location of error
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
