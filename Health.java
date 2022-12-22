// CST-239
package adminsalable;

/**
 * This class represents Health as a salable product
 * The health does not perform any actions (methods).
 * The health's attributes are it's name, description, price, and quantity.
 * @author cartercampbell
 *
 */
public class Health extends Salable
{
	/**
	 * non-default constructor to initialize the attributes of the "Health"
	 * @param name - name of health item
	 * @param description - description of health item
	 * @param price - price of health item
	 * @param quantity - quantity of health item in the inventory manager
	 */
	public Health(String name, String description, float price, int quantity)
	{
		super(name, description, price, quantity);
	}
	
	/**
	 * default constructor for health to serialize health objects
	 */
	public Health()
	{
		name = "";
		description = "";
		price = 0;
		quantity = 0;
	}

	

	

	
	
	
		
}
