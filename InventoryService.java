package adminservices;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import adminsalable.Salable;


/**
 * Class to read and save the store inventory using JSON.
 * @author cartercampbell
 *
 */
public class InventoryService 
{
	/**
	 * Method to save the inventory file in JSON format
	 * @param filename - the file we are saving/writing to
	 * @param inventory - what we are adding to the file
	 * @return - int to catch exceptions using try/catch
	 * @throws IOException - the exception we are throwing to be handled in the StoreFront
	 */
	public int saveToFile(String filename, List<Salable> inventory) throws IOException
	{
		
		PrintWriter pw = null; // initialize printwriter
		try
		{
			Salable[] arr = inventory.toArray(Salable[]::new);
			File file = new File(filename);  // create empty file
			FileWriter fw = new FileWriter(file); // set file to the file we are passing in
			pw = new PrintWriter(fw); // set printwriter to filewriter
			
			ObjectMapper objectMapper = new ObjectMapper(); // initialize object mapper
			String json = objectMapper.writeValueAsString(arr); // use object mapper to write values as strings
			
			pw.println(json); // use printwriter
			return 1;
		} 
		catch(IOException e) // catch IOExceptions
		{
			// e.printStackTrace();
			return -1;
		}
		finally
		{
			if(pw != null)
			{
				pw.close(); // close the printwriter
			}
		}
	}
	
	/**
	 * Method to read the saved file, and add those objects to the inventory
	 * @param filename - the file we are reading from
	 * @return - the inventory
	 * @throws IOException - the exception we are throwing to be handled in the StoreFront
	 */
	public List<Salable> readFromFile(String filename) throws IOException
	{
		Salable[] arr = new Salable[30];
 		try
		{
			// read the file and store in the array
			ObjectMapper objectMapper = new ObjectMapper();
			arr = objectMapper.readValue(new File(filename), Salable[].class);
		}
		catch(IOException e) // catch IOExceptions
		{
			// e.printStackTrace();
		}
		
 		List<Salable> salables =  Arrays.asList(arr);
		
		return salables; // return the entire inventory
	}
	
	
	
	

	
}
