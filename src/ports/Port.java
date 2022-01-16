
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import interfaces.IPort;
import ships.Ship;
import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
 /**
  * the class that includes properties and methods of a port.
  * It implements IPort interface
  * @author BASARAN
  *
  */
public class Port implements IPort {
	/**
	 * ID of the port
	 */
	private final int ID;
	/**
	 * X coordinate of the port
	 */
	private double X;
	/**
	 * Y coordinate of the port
	 */
	private double Y;
	/**
	 * list of containers in the port
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * list of ships which has visited the port
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * list of ships currently in the port
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
	/**
	 * list of heavy containers in the port
	 */
	private ArrayList<Container> heavyContainers = new ArrayList<Container>();
	/**
	 * list of refrigerated containers in the port
	 */
	private ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
	/**
	 * list of basic containers in the port
	 */
	private ArrayList<Container> basicContainers = new ArrayList<Container>();
	/**
	 * list of liquid containers in the port
	 */
	private ArrayList<Container> liquidContainers = new ArrayList<Container>();
	
	
	/**
	 * Constructor with three parameters
	 * @param ID  takes an int value and initializes Port's ID
	 * @param X  takes an double value and initializes Port's location on X axis
	 * @param Y	takes an double value and initializes Port's location on Y axis
	 */
	public Port(int ID, double X, double Y){
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * adds incoming ship to current if it is not already in current
	 * @param s takes an Ship class value
	 */
	public void incomingShip(Ship s) {
		if (!(current.contains(s))) {
			current.add(s);
			s.setPort(this);
			}
	}
	/**
	 * adds outgoing ship to history if it is not already in history
	 * @param s takes an Ship class value
	 */
	public void outgoingShip(Ship s) {
		current.remove(s);
		if (!(history.contains(s))) {
			history.add(s);}
	}
	
	/**
	 * finds distance between current port and given port
	 * @param other  takes an Port class value to find distance between current port 
	 * @return a double value which is distance between 2 ports
	 */
	public double getDistance(Port other) {
		double xSquare = Math.pow(this.X - other.X, 2);
		double ySquare = Math.pow(this.Y- other.Y, 2);
		double distance = Math.sqrt(xSquare + ySquare);
		return distance;
	}
	/**
	 * checks whether the ship and container are in the same port
	 * @param s ship we try to check its port
	 * @param cont container we try to check its port
	 * @return a boolean value which shows whether the ship and container are in the same port
	 */
	public boolean checkLoad(Ship s, Container cont) {
		return current.contains(s) && containers.contains(cont);
	}
	/**
	 * sorts the given container list according to increasing ID with sort method of comparable interface
	 * @param cont container list we want to sort
	 * @return sorted container list according to increasing ID
	 */
	public ArrayList<Container> containerSort(ArrayList<Container> cont){
		Collections.sort(cont);
		return cont;
	}
	/**
	 * converts the coordinates of port to String type
	 * @return converted version of coordinates
	 */
	public String getCoordinates() {
		String x = String.format("%.2f", X);
		String y = String.format("%.2f", Y);
		return "("+ x + ", " + y +")";
	}
	/**
	 * sorts the current ship list according to increasing ID with sort method of comparable interface
	 * @return sorted ship list according to increasing ID
	 */
	public ArrayList<Ship> getCurrentShips(){
		Collections.sort(current);
		return current;
	}
	/**
	 * getter for ID of port
	 * @return an int value which is ID of port
	 */
	public int getID() {
		return ID;
	}
	/**
	 * getter for containers list in the port
	 * @return container list in the port
	 */
	public ArrayList<Container> getContainers(){
		return containers;
	}
	/**
	 * getter for heavy container list in the port
	 * @return heavy container list in the port
	 */
	public ArrayList<Container> getHeavyContainers(){
		return heavyContainers;
	}
	/**
	 * getter for liquid container list in the port
	 * @return liquid container list in the port
	 */
	public ArrayList<Container> getLiquidContainers(){
		return liquidContainers;
	}
	/**
	 * getter for refrigerated container list in the port
	 * @return refrigerated container list in the port
	 */
	public ArrayList<Container> getRefrigeratedContainers(){
		return refrigeratedContainers;
	}
	/**
	 * getter for basic container list in the port
	 * @return basic container list in the port
	 */
	public ArrayList<Container> getBasicContainers(){
		return basicContainers;
	}
	/**
	 * setter for containers list in the port
	 * @param cont new containers list
	 */
	public void setContainers(ArrayList<Container> cont) {
		this.containers = cont;
	}
	/**
	 * setter for heavy containers list in the port
	 * @param cont new heavy containers list
	 */
	public void setHeavyContainers(ArrayList<Container> cont) {
		this.heavyContainers = cont;
	}
	/**
	 * setter for liquid containers list in the port
	 * @param cont new liquid containers list
	 */
	public void setLiquidContainers(ArrayList<Container> cont) {
		this.liquidContainers = cont;
	}
	/**
	 * setter for refrigerated containers list in the port
	 * @param cont new refrigerated containers list
	 */
	public void setRefgrigeratedContainers(ArrayList<Container> cont) {
		this.refrigeratedContainers = cont;
	}
	/**
	 * setter for basic containers list in the port
	 * @param cont new basic containers list
	 */
	public void setBasicContainers(ArrayList<Container> cont) {
		this.basicContainers = cont;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

