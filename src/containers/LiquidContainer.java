
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * the class that includes properties and methods of a liquid container.
 * It extends container class.
 * @author BASARAN
 *
 */
public class LiquidContainer extends HeavyContainer {
	/**
	 * constructor with two parameters which implies super constructor
	 * @param ID takes an int value and initializes Container's ID
	 * @param weight takes an int value and initializes Container's weight
	 */
	public LiquidContainer(int ID, int weight){
		super(ID,weight);
	}
	/**
	 * fuel consumption of container per kilometer and per weight
	 */
	{super.fuelConsumption = 4.00;}
	/**
	 * type of container
	 */
	{super.type = "LiquidContainer";}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

