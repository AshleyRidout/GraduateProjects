/**
 * @author Ashley.Ridout
 * @date August 14, 2017
 * @reference Boston University CS 622 Summer 2 2017 Module 6
 */

package homework6;

import java.io.*;
import java.net.*;

//server class to get acceptance decision and composite score
public class GradAcceptOneServer {

	public static void main(String[] args) {
		// message to console once server connects	
		System.out.println("Server says: Server started …");
		
		// try with resources so they close in any situation
		try (
				// server socket variable
				ServerSocket serversocket = new ServerSocket(9000);
				// listens for a connection and accepts it
				Socket socket = serversocket.accept();
				// output stream to client
				PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);
				// input stream from client
				ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());) {
			
			//assign GradAcceptOneObject
			GradAcceptOneObject co = (GradAcceptOneObject) inputFromClient.readObject();
			
			//output to console using GradAcceptantOneObject toString method
			System.out.println("Object to be processed by server: " + co);

			// initiate processInput method and print output
			String outputAsString = co.processInput();
			System.out.print(outputAsString);
			
			// write outputAsString to client
			outputToClient.write(outputAsString);
			
			// catch IO exception, print message and location of error
		} catch (IOException excep) {
			System.out.println("IO Excpetion caught in the server.");
			excep.printStackTrace();
			// catch Number Format exception, print message and location of error
		} catch (NumberFormatException nfe) {
			System.out.println("Please try again by entering a number, not a letter.");
			nfe.printStackTrace();
			//catch Class Not Found exception, print message and location of error
		} catch(ClassNotFoundException cnfe){
			System.out.println("Class not found.");
			cnfe.printStackTrace();
		}
	}
}
