// CST-239
package adminsalable;

/**
 * This class represents a Weapon as a salable product.
 * The weapon does not perform any actions (methods).
 * The weapon's attributes are it's name, description, price, and quantity.
 * @author cartercampbell
 *
 */
public class Weapon extends Salable
{
	/**
	 * Non-default constructor to the initialize the attributes of the "Weapon"
	 * @param name - name of weapon
	 * @param description - description of weapon
	 * @param price - price of weapon
	 * @param quantity - quantity of weapon in the inventory manager
	 */
	public Weapon(String name, String description, float price, int quantity)
	{
		super(name, description, price, quantity);
	}
	
	/**
	 * default constructor for weapon to serialize weapon objects
	 */
	public Weapon()
	{
		name = "";
		description = "";
		price = 0;
		quantity = 0;
	}

	
	
}
