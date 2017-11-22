/**
 * @author Ashley.Ridout
 * @date July 17, 2017
 */

package homework2;

//import String Tokenizer to count number of Strings
import java.util.StringTokenizer;

//class to hold changeEA and NumberOfLastNames methods
public class ChangeLastNames {
	
	/**
	 * @param lName is the string of last names held in the file
	 * @param le is the string of last names with "e" changed to "E"
	 * @return la is the string of last names already changed from "e" to "E" and now change "a" to "A"
	 */
	public String changeEA(String lName){	
		String le = lName.replaceAll("e", "E");
		String la = le.replaceAll("a", "A");
		return la;
	}
	
	/**
	 * @param ln is the string of last names with capital "E" and "A"
	 * @return message to the console of the total number of last names
	 */
	public String NumberOfLastNames(String ln){
		StringTokenizer st = new StringTokenizer(ln);
		return "\nTotal number of last names: " + st.countTokens();
	}
}
