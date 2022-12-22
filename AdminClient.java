package adminservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import adminsalable.Salable;


/**
 * AdminClient class to update salable products in the inventory and view the entire inventory
 * "R" command to send the inventory to client
 * "U" command to receive a new inventory from client
 * "0" command to quit
 * @author cartercampbell
 *
 */
public class AdminClient 
{
	/**
	 * Socket and Reader/Writer variables
	 */
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	static AdminClient client = new AdminClient(); // client object
	static InventoryService service = new InventoryService();
	static InventoryManager inventory = new InventoryManager();
	
	
	/**
	 * Main method to run logic for 
	 * "U" Command to update the inventory
	 * "R" Command to view the inventory
	 * "0" Command to quit
	 * @param args - default
	 * @throws IOException - thrown exception
	 * @throws InterruptedException - thrown exception
	 */
	public static void main(String[] args) throws IOException, InterruptedException
	{
		client.start("127.0.0.1", 6666); // start the client at specific ip and port
		System.out.println("**-------------------------------**");
		System.out.println("Welcome to the Admin Client!"); // welcome message 
		System.out.println("**-------------------------------**");
		System.out.println("");
		Scanner scan = new Scanner(System.in); // scanner to receive input 
		boolean ok = true;
		String message; // message to send to server
		String response; // sent message 
		String reply = "";
		while(ok == true) // loop for UI
		{
			System.out.println("Enter 'U' to update the inventory, enter 'R' to view the inventory, or enter '0' to quit: ");
			String input = scan.nextLine(); // store input
			if(input.equals("U")) // "U" command
			{
				System.out.println("Update Inventory\n");
				List<Salable> newInv = new ArrayList<Salable>();
				newInv = service.readFromFile("save.json"); // read current inventory
				System.out.println("**--------------------------------------**");
				System.out.println("Choose item to increment quantity."); // display current inventory
				System.out.println("1.) " + newInv.get(0).getName() + " - Quantity: " + newInv.get(0).getQuantity());
				System.out.println("2.) " + newInv.get(1).getName() + " - Quantity: " + newInv.get(1).getQuantity());
				System.out.println("3.) " + newInv.get(2).getName() + " - Quantity: " + newInv.get(2).getQuantity());
				System.out.println("4.) " + newInv.get(3).getName() + " - Quantity: " + newInv.get(3).getQuantity());
				System.out.println("5.) " + newInv.get(4).getName() + " - Quantity: " + newInv.get(4).getQuantity());
				System.out.println("Enter number of item to update quantity: ");
				System.out.println("**--------------------------------------**");
				String update = scan.next(); // store index as string
				int index = Integer.parseInt(update); // convert string to int to get index
				System.out.println("\n**--------------------------------------**");
				System.out.println("Enter new quantity for " + newInv.get(index-1).getName() + ": ");
				System.out.println("**--------------------------------------**");
				String quantityString = scan.next(); // store quantity as string
				int quantity = Integer.parseInt(quantityString); // convert quantity to int
				newInv.get(index-1).setQuantity(quantity); // set quantity at specific index
				System.out.println("\n**--------------------------------------**");
				System.out.println("Quantity of " + newInv.get(index-1).getName() + " set to " + newInv.get(index-1).getQuantity());
				System.out.println("**--------------------------------------**\n");
				service.saveToFile("save.json", newInv); // save to file with new quantity
				ObjectMapper objectMapper = new ObjectMapper();
				for(Salable salable : newInv) // iterate through the inventory
				{
					String json = objectMapper.writeValueAsString(salable); // write each salable in JSON
					reply += json; // add JSON to reply
				}
				String updatedInventory = "U | " + reply;
				message = updatedInventory; // send U command with new JSON inventory
			}
			else if(input.equals("R")) // "R" command
			{
				System.out.println("View Inventory\n");
				message = "R"; // send R command over server
			}
			else if(input.equals("0")) // "0" command (quit) 
			{
				System.out.println("Quit\n");
				message = "0";
				ok = false;
				break;
			}
			else // Invalid input
			{
				System.out.println("Invalid Input\n");
				message = "invalid";
			}
			response = client.sendMessage(message); // send message and store as response
			if(response != null) // check null before printing response
			{
				System.out.println("Server response was " + response + "\n");
			}
		}
		client.cleanup(); // cleanup to close all sockets and reader/writers
		scan.close(); // close scanner
	}
	
	/**
	 * Method to connect to remote server on specific ip address and port
	 * @param ip - IP Address to connect to
	 * @param port - port to connect to
	 * @throws UnknownHostException - Thrown for network resolution exception
	 * @throws IOException - Thrown if anything bad happens in Server application
	 */
	public void start(String ip, int port) throws UnknownHostException, IOException
	{
		// connect to server on specified ip address and port
		clientSocket = new Socket(ip, port);
		
		// Create input and output buffers to communicate back and forth
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	/**
	 * Method to send a message to the Server
	 * @param msg - message being sent
	 * @return - String being returned 
	 * @throws IOException - Thrown if anything bad happens in Server application
	 */
	public String sendMessage(String msg) throws IOException
	{
		// print msg to Server 
		out.println(msg);
		// return response from the Server
		return in.readLine();
	}
	
	/**
	 * Method to close all sockets and readers/writers
	 * @throws IOException - thrown exception to be handled
	 */
	public void cleanup() throws IOException
	{
		// close all input and output buffers and sockets
		in.close();
		out.close();
		clientSocket.close();
	}

}
