/**
 * @author Ashley.Ridout
 * @date August 14, 2017
 * @reference Boston University CS 622 Summer 2 2017 Module 6 
 */

package homework6;

//import all classes in java io and net packages
import java.io.*;
import java.net.*;
import java.util.Scanner;

//client class to get acceptance decision and composite score for a SECOND Client 
//to demonstrate GradAcceptMultiThreadServer
public class GradAcceptSecondClient {

	public static void main(String[] args) {
		// message to console once client connects
		System.out.println("Client 2 says: client started");

		// try with resources so they close in any situation
		try (
				// socket variable
				Socket socket = new Socket("localhost", 9000);
				// output stream to socket variable
				ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
				// input stream from socket variable
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			// input stream from console
			Scanner scanInput = new Scanner(new InputStreamReader(System.in));

			// output to console and input doubles
			System.out.println("Please enter your GPA.");
			double gpa = scanInput.nextDouble();
			System.out.println("Please enter your GRE.");
			double gre = scanInput.nextDouble();
			System.out.println("Please enter your score for the first Letter of Recommendation.");
			double lor1 = scanInput.nextDouble();
			System.out.println("Please enter your score for the second Letter of Recommendation.");
			double lor2 = scanInput.nextDouble();

			// assign GradAcceptOneObject for inputed doubles
			GradAcceptOneObject clientob = new GradAcceptOneObject(gpa, gre, lor1, lor2);
			// output to console using GradAcceptantOneObject toString method
			System.out.println("Object to be written: " + clientob);

			// output to server
			toServer.writeObject(clientob);
			scanInput.close();

			// catch Unknown host exception, print message and location of error
		} catch (UnknownHostException uhe) {
			System.out.println("Unknown host.");
			uhe.printStackTrace();
			// catch IO exception, print message and location of error
		} catch (IOException excep) {
			System.out.println("Input/Output exception caught in the client.");
			excep.printStackTrace();
		}
	}
}
