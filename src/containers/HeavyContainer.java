
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * the class that includes properties and methods of a heavy container.
 * It extends container class.
 * @author BASARAN
 *
 */
public class HeavyContainer extends Container {
	/**
	 * constructor with two parameters which implies super constructor
	 * @param ID takes an int value and initializes Container's ID
	 * @param weight takes an int value and initializes Container's weight
	 */
	public HeavyContainer(int ID, int weight){
		super(ID, weight);
	}
	/**
	 * fuel consumption of container per kilometer and per weight
	 */
	{super.fuelConsumption=3.00;}
	/**
	 * type of container
	 */
	{super.type = "HeavyContainer";}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

