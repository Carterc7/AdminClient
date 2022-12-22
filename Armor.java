// CST-239
package adminsalable;

/**
 * This class represents Armor as a salable product.
 * The armor does not perform any actions (methods).
 * The armor's attributes are it's name, description, price, and quantity.
 * @author cartercampbell
 *
 */
public class Armor extends Salable
{
	/**
	 * non-default constructor to initialize the attributes of the "Armor"
	 * @param name - name of armor
	 * @param description - description of armor
	 * @param price - price of armor
	 * @param quantity - quantity of armor in the inventory manager
	 */
	public Armor(String name, String description, float price, int quantity)
	{
		super(name, description, price, quantity);
	}
	
	/**
	 * default constructor for armor to serialize armor objects
	 */
	public Armor()
	{
		name = "";
		description = "";
		price = 0;
		quantity = 0;
	}

	
	
	
}
