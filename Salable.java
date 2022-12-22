// CST-239
package adminsalable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;




/**
 * The salable class is the base class for the armor, health, and weapon classes.
 * The salable class is extended to these objects to provide base attributes and a non-default
 * constructor.
 * @author cartercampbell
 *
 */
@JsonTypeInfo
(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type"
)
@JsonSubTypes
(
	{
	@Type(value = Health.class, name = "health"),
	@Type(value = Armor.class, name = "armor"),
	@Type(value = Weapon.class, name = "weapon")
	}
)
public class Salable implements Comparable<Salable>
{
	/**
	 * protected variable to describe a salable object
	 * these variables are used to describe armor, health, and weapons.
	 */
	protected String name;
	protected String description;
	protected float price;
	protected int quantity;
	
	/**
	 * non-default constructor for base class
	 * @param name - name for salable object
	 * @param description - description of salable object
	 * @param price - price of salable object
	 * @param quantity - amount of salable object in the inventory manager
	 */
	public Salable(String name, String description, float price, int quantity)
	{
		// Initialize internal variables
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * Default Constructor for base-class
	 */
	public Salable()
	{
		name = "";
		description = "";
		price = 0;
		quantity = 0;
	}


	public String getName() 
	{
		return name;
	}

	public String getDescription() 
	{
		return description;
	}

	public float getPrice() 
	{
		return price;
	}

	public int getQuantity() 
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	/**
	 * Compares this.name to a.name and returns
	 * checks if names are equal
	 */
	public int compareTo(Salable a) 
	{
		int value = this.name.compareTo(a.name);
		return value;
	}
	
	
	
}
