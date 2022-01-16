
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * the class that includes common properties and methods of all containers.
 * It implements comparable interface
 * @author BASARAN
 *
 */
public abstract class Container implements Comparable<Container> {
	/**
	 * ID of container
	 */
	protected final int ID;
	/**
	 * weight of container
	 */
	protected int weight;
	/**
	 * fuel consumption per kilometer and per unit weight of container
	 */
	protected double fuelConsumption;
	/**
	 * type of container
	 */
	protected String type;
	
	/**
	 * Constructor with two parameters
	 * @param ID takes an int value and initializes Container's ID
	 * @param weight takes an int value and initializes Container's weight
	 */
	Container(int ID, int weight){
		this.ID = ID;
		this.weight = weight;
	}
	/**
	 * fuel consumption of container according to weight
	 * @return
	 */
	public double consumption() {
		return weight * this.fuelConsumption;
	}
	/**
	 * checks whether two containers are the same according to their ID and type
	 * @param other container which we try to compare with current container
	 * @return a boolean value which demonstrates whether two containers are the same according to their ID and type
	 */
	public boolean equals(Container other) {
		return (this.type == other.type && this.weight == other.weight && this.ID == other.ID);
	}
	/**
	 * getter for type of container
	 * @return a String value which is type of the container
	 */
	public String getType() {
		return type;
	}
	/**
	 * getter for weight of container
	 * @return an int value which is weight of the container
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * getter for ID of container
	 * @return an int value which is ID of the container
	 */
	public int getID() {
		return ID;
	}
	/**
	 * compareTo method for comparable interface
	 * @param cont container that we want to compare with current container.
	 * @return a positive int if current ID is bigger than other container's , zero if current ID is equals other container's, a negative int if current ID is less than other container's 
	 */
	public int compareTo(Container cont) {
        if (this.ID > cont.getID()) {
            return 1;
        }else if(equals(cont)) {
        	return 0;
        }else {
            return -1;
        }
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

