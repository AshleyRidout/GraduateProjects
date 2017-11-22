package homework6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GradAcceptMultiThreadServer {
	public static void main(String[] args) {

		// try with resources so they close in any situation
		try (
				// server socket variable
				ServerSocket serversocket = new ServerSocket(9000);) {
			// message to console once server connects
			System.out.println("Server says: MultiThread Server started …");

			// new connection
			while (true) {
				// listens for a connection and accepts it
				Socket socket = serversocket.accept();
				// get inetAddress
				InetAddress inet = socket.getInetAddress();
				// output to console host address and name
				System.out.println("Client IP address: " + inet.getHostAddress() + " host name: " + inet.getHostName());

				// start a new thread
				new Thread(new ProcessOneClient(socket)).start();

			}
			// catch IO exception, print message and location of error
		} catch (IOException excep) {
			System.out.println("IO Excpetion caught in the server.");
			excep.printStackTrace();
		}
	}
}

// class to include run method
class ProcessOneClient implements Runnable {
	private Socket socket;

	// constructor with socket argument
	public ProcessOneClient(Socket s) {
		socket = s;
	}

	// run method to contain code executed in thread
	public void run() {
		try {
			// output to console thread id starts
			System.out.println("Thread Id = " + Thread.currentThread().getId() + " start");
			// output stream to client
			PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);
			// input stream from client
			ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());

			// assign GradAcceptOneObject to input from Client
			GradAcceptOneObject co = (GradAcceptOneObject) inputFromClient.readObject();

			// output to console using toString method what will be processed by
			// server
			System.out.println("Object to be processed by server: " + co);

			// initiate processInput method and print output
			String outputAsString = co.processInput();
			System.out.print(outputAsString);

			// write outputAsString to client
			outputToClient.write(outputAsString);

			// output to console thread id ends
			System.out.println("\nThread Id = " + Thread.currentThread().getId() + " end");

			// catch IO exception, print message and location of error
		} catch (IOException ex) {
			System.out.println("IO Excpetion caught in the multithread server.");
			ex.printStackTrace();
			// catch Number Format exception, print message and location of
			// error
		} catch (NumberFormatException nfe) {
			System.out.println("Please try again by entering a number, not a letter.");
			nfe.printStackTrace();
			// catch Class Not Found exception, print message and location of
			// error
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class not found.");
			cnfe.printStackTrace();
		}
	}
}
