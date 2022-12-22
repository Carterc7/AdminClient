package adminservices;
import adminsalable.Salable;
import adminservices.InventoryService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adminsalable.Armor;
import adminsalable.Health;
import adminsalable.Weapon;

/**
 * The inventory manager class holds the inventory for the store and allows the user to 
 * add or remove contents from the inventory and move them into the shopping cart.
 * The inventory manager's attribute is an ArrayList to hold the inventory.
 * @author cartercampbell
 *
 */
public class InventoryManager
{
		/**
		 * Attribute of the inventory manager.
		 * List to hold the inventory.
		 */
		List<Salable> inventory = new ArrayList<Salable>();
		
		/**
		 * Initializes the salable object inventory and adds the objects to the cart 
		 * using the JSON File Service
		 * @throws IOException - thrown exception
		 */
		public void intializeInventory() throws IOException
		{
			InventoryService service = new InventoryService();
			try 
			{
				inventory = service.readFromFile("save.json");
			}
			catch(IOException e)
			{
				throw new IOException();
				// e.printStackTrace();
			}
		}
		
		/**
		 * Method to return the inventory
		 * @return - returns the inventory
		 */
		public List<Salable> returnList()
		{
			return inventory;
		}
		
		/**
		 * Method to add a salable to the inventory
		 * @param item - item that we are adding
		 * @return - returns a salable with an incremented quantity
		 */
		public Salable addSalable(Salable item)
		{
			// iterate through and find the item
			// increment the quantity
			// make a copy of the item
			// return the copy
			for(int i = 0; i < inventory.size(); i++)
			{
				if(inventory.get(i).compareTo(item) == 0)
				{
					int temp = item.getQuantity();
					temp++;
					item.setQuantity(temp);
				}
			}
			// copy constructor to make a new salable with the same attributes
			Salable tempItem = new Salable(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
			return tempItem;
			
 		}
		
		/**
		 * Method to remove a salable from the inventory
		 * @param item - the item being removed
		 * @return - returns a salable with a decremented quantity
		 */
		public Salable removeSalable(Salable item)
		{
			// iterate through and find the item
			// decrement the quantity
			// make a copy of item
			// return the copy
			for(int i = 0; i < inventory.size(); i++)
			{
				if(inventory.get(i).compareTo(item) == 0)
				{
					int temp = item.getQuantity();
					temp--;
					item.setQuantity(temp);
				}
			}
			// copy constructor to make a new salable with the same attributes
			Salable tempItem = new Salable(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
			return tempItem;
		}
			
			
		}
		
		
		
		

	
