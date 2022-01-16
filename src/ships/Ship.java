
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.*;
import containers.Container;
import interfaces.IShip;
import ports.Port;
/**
 * the class that includes properties and methods of a ship.
 * It implements IShip interface and comparable interface.
 * @author BASARAN
 *
 */
public class Ship implements IShip, Comparable<Ship> {
	/**
	 *  ID of the ship
	 */
	private final int ID;
	/**
	 *  fuel of the ship
	 */
	private double fuel = 0;
	/**
	 * port where the ship currently in
	 */
	private Port currentPort;
	/**
	 * maximum weight that the ship can carry
	 */
	private int totalWeightCapacity;
	/**
	 * maximum number of containers that the ship can carry
	 */
	private int maxNumberOfAllContainers;
	/**
	 * maximum number of heavy containers that the ship can carry
	 */
	private int maxNumberOfHeavyContainers;
	/**
	 * maximum number of refrigerated containers that the ship can carry
	 */
	private int maxNumberOfRefrigeratedContainers;
	/**
	 * maximum number of liquid containers that the ship can carry
	 */
	private int maxNumberOfLiquidContainers;
	/**
	 * current number of containers that is carried by the ship
	 */
	private int numberOfAllContainers = 0;
	/**
	 * current number of heavy containers that is carried by the ship
	 */
	private int numberOfHeavyContainers = 0;
	/**
	 * current number of refrigerated containers that is carried by the ship
	 */
	private int numberOfRefrigeratedContainers = 0;
	/**
	 * current number of liquid containers that is carried by the ship
	 */
	private int numberOfLiquidContainers = 0;
	/**
	 * fuel consumption of the ship per kilometer
	 */
	private double fuelConsumptionPerKM = 0;
	/**
	 * current weight of the ship and including containers
	 */
	private int currentWeight = 0;
	/**
	 * list of containers in the ship
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * list of heavy containers in the ship
	 */
	private ArrayList<Container> heavyContainers = new ArrayList<Container>();
	/**
	 * list of refrigerated containers in the ship
	 */
	private ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
	/**
	 * list of basic containers in the ship
	 */
	private ArrayList<Container> basicContainers = new ArrayList<Container>();
	/**
	 * list of liquid containers in the ship
	 */
	private ArrayList<Container> liquidContainers = new ArrayList<Container>();
	
	/**
	 * constructor of Ship with 8 parameters. it also adds ship to the port's current list
	 * @param ID initializes Ship's ID
	 * @param p initializes port Ship's current port
	 * @param totalWeightCapacity initializes Ship's total weight capacity
	 * @param maxNumberOfAllContainers initializes maximum number of all containers the ship can carry
	 * @param maxNumberOfHeavyContainers initializes maximum number of heavy containers the ship can carry
	 * @param maxNumberOfRefrigeratedContainers initializes maximum number of refrigerated containers the ship can carry
	 * @param maxNumberOfLiquidContainers initializes maximum number of liquid containers the ship can carry
	 * @param fuelConsumptionPerKM initializes fuel needed per kilometer
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, 
			int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, 
			int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		currentPort.incomingShip(this);
	}
	/**
	 * calculates fuel consumption per kilometer due to containers that currently in ship.
	 * @return fuel consumption per kilometer due to containers that currently in ship
	 */
	public double fuelForContainers() {
		double consumption = 0;
		for (int i = 0; i < containers.size(); i++) {
			consumption += containers.get(i).consumption();
		}
		return consumption;
	}
	
	/**
	 * allows the ship to go to the given port if ship's fuel is enough to sail.
	 * @param p port we want to sail to
	 * @return true if ship can sail successfully, false if it cannot
	 */
	public boolean sailTo(Port p) {
		double fuelNeeded = (fuelConsumptionPerKM  + this.fuelForContainers())* currentPort.getDistance(p);
		if (fuelNeeded <= fuel) {
			currentPort.outgoingShip(this);
			this.currentPort = p;
			p.incomingShip(this);
			fuel -= fuelNeeded;
			return true;
		}
		else {
			return false;
			}
	}
	/**
	 * adds fuel to ship
	 * @param newFuel amount of fuel that added to ship
	 */
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}
	/**
	 * loads the given container to ship if it is possible according to given conditions.
	 * Checks container's weight and type than compare with max number of containers and weight capacity
	 * of ship than returns true if container has loaded to ship returns false if it has not loaded to ship.
	 * @param cont container we want to load to ship
	 * @return true if container can be loaded successfully, false if it cannot.
	 */
	public boolean load(Container cont) {
		if (this.currentPort.checkLoad(this, cont)) {
			if (cont.getType().equals("BasicContainer")) {
				if((currentWeight + cont.getWeight())<= totalWeightCapacity && numberOfAllContainers < maxNumberOfAllContainers) {
					this.containers.add(cont);
					this.basicContainers.add(cont);
					this.currentPort.getBasicContainers().remove(cont);
					this.currentPort.getContainers().remove(cont);
					this.currentWeight += cont.getWeight();
					this.numberOfAllContainers += 1;
					return true;
				}
				else {return false;}
			}
			else if (cont.getType().equals("HeavyContainer")) {
				if((currentWeight + cont.getWeight())<= totalWeightCapacity && numberOfHeavyContainers < maxNumberOfHeavyContainers 
						&& numberOfAllContainers < maxNumberOfAllContainers) {
					this.containers.add(cont);
					this.heavyContainers.add(cont);
					this.currentPort.getHeavyContainers().remove(cont);
					this.currentPort.getContainers().remove(cont);
					this.currentWeight += cont.getWeight();
					this.numberOfAllContainers += 1;
					this.numberOfHeavyContainers += 1;
					return true;
				}
				else {return false;}
				}
			else if (cont.getType().equals("LiquidContainer")) {
				if((currentWeight + cont.getWeight())<= totalWeightCapacity && numberOfHeavyContainers < maxNumberOfHeavyContainers 
						&& numberOfAllContainers < maxNumberOfAllContainers && numberOfLiquidContainers < maxNumberOfLiquidContainers) {
					this.containers.add(cont);
					this.liquidContainers.add(cont);
					this.currentPort.getLiquidContainers().remove(cont);
					this.currentPort.getContainers().remove(cont);
					this.currentWeight += cont.getWeight();
					this.numberOfAllContainers += 1;
					this.numberOfHeavyContainers += 1;
					this.numberOfLiquidContainers += 1;
					return true;
				}
				else {return false;}
				}
			else if (cont.getType().equals("RefrigeratedContainer")) {
				if((currentWeight + cont.getWeight())<= totalWeightCapacity && numberOfHeavyContainers < maxNumberOfHeavyContainers 
						&& numberOfAllContainers < maxNumberOfAllContainers && numberOfRefrigeratedContainers < maxNumberOfRefrigeratedContainers) {
					this.containers.add(cont);
					this.refrigeratedContainers.add(cont);
					this.currentPort.getRefrigeratedContainers().remove(cont);
					this.currentPort.getContainers().remove(cont);
					this.currentWeight += cont.getWeight();
					this.numberOfAllContainers += 1;
					this.numberOfHeavyContainers += 1;
					this.numberOfRefrigeratedContainers += 1;
					return true;
				}
				else {return false;}
			}
			else {return false;}
		}
		else {return false;}
	}
	
	/**
	 * loads the given container to ship if it is possible
	 * @param cont container we want to unload to ship
	 * @return true if container can successfully loaded to ship, false otherwise
	 */
	public boolean unLoad(Container cont) {
		if (containers.contains(cont)) {
			if (cont.getType().equals("BasicContainer")) {
				containers.remove(cont);
				basicContainers.remove(cont);
				currentPort.getBasicContainers().add(cont);
				currentPort.getContainers().add(cont);
				numberOfAllContainers -= 1;
				currentWeight -= cont.getWeight();
			}
			else if (cont.getType().equals("HeavyContainer")) {
				containers.remove(cont);
				heavyContainers.remove(cont);
				currentPort.getHeavyContainers().add(cont);
				currentPort.getContainers().add(cont);
				numberOfAllContainers -= 1;
				numberOfHeavyContainers -= 1;
				currentWeight -= cont.getWeight();
			}	
			else if (cont.getType().equals("LiquidContainer")) {
				containers.remove(cont);
				liquidContainers.remove(cont);
				currentPort.getLiquidContainers().add(cont);
				currentPort.getContainers().add(cont);
				numberOfAllContainers -= 1;
				numberOfHeavyContainers -= 1;
				numberOfLiquidContainers -= 1;
				currentWeight -= cont.getWeight();
			}				
			else if (cont.getType().equals("RefrigeratedContainer")) {
				containers.remove(cont);
				refrigeratedContainers.remove(cont);
				currentPort.getRefrigeratedContainers().add(cont);
				currentPort.getContainers().add(cont);
				numberOfAllContainers -= 1;
				numberOfHeavyContainers -= 1;
				numberOfRefrigeratedContainers -= 1;
				currentWeight -= cont.getWeight();
			}	
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * getter for current weight of the ship
	 * @return current weight of the ship
	 */
	public int getWeight() {
		return currentWeight;
	}
	/**
	 * getter for ID of ship
	 * @return ID of ship
	 */
	public int getID() {
		return ID;
	}
	/**
	 * compareTo method for comparable interface
	 * @param s  ship that we want to compare with current ship.
	 * @return a positive int if current ID is bigger than other ship's , zero if current ID is equals other ship's, a negative int if current ID is less than other ship's
	 */
	public int compareTo(Ship s) {
        if (this.ID > s.getID()) {
            return 1;
        } else {
            return -1;
        }
	}
	/**
	 * getter for current fuel of ship
	 * @return current fuel of ship
	 */
	public double getFuel() {
		return fuel;
	}
	/**
	 * getter for current container list. It also sorts the elements in container list.
	 * @return current container list
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(containers);
		return containers;
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
	 * getter for heavy container list in the ship
	 * @return heavy container list in the ship
	 */
	public ArrayList<Container> getHeavyContainers(){
		return heavyContainers;
	}
	/**
	 * getter for liquid container list in the ship
	 * @return liquid container list in the ship
	 */
	public ArrayList<Container> getLiquidContainers(){
		return liquidContainers;
	}
	/**
	 * getter for refrigerated container list in the ship
	 * @return refrigerated container list in the ship
	 */
	public ArrayList<Container> getRefrigeratedContainers(){
		return refrigeratedContainers;
	}
	/**
	 * getter for basic container list in the ship
	 * @return basic container list in the ship
	 */
	public ArrayList<Container> getBasicContainers(){
		return basicContainers;
	}
	/**
	 * setter for containers list in the ship
	 * @param cont new containers list
	 */
	public void setContainers(ArrayList<Container> cont) {
		this.containers = cont;
	}
	/**
	 * setter for heavy containers list in the ship
	 * @param cont new heavy containers list
	 */
	public void setHeavyContainers(ArrayList<Container> cont) {
		this.heavyContainers = cont;
	}
	/**
	 * setter for liquid containers list in the ship
	 * @param cont new liquid containers list
	 */
	public void setLiquidContainers(ArrayList<Container> cont) {
		this.liquidContainers = cont;
	}
	/**
	 * setter for refrigerated containers list in the ship
	 * @param cont new refrigerated containers list
	 */
	public void setRefgrigeratedContainers(ArrayList<Container> cont) {
		this.refrigeratedContainers = cont;
	}
	/**
	 * setter for basic containers list in the ship
	 * @param cont new basic containers list
	 */
	public void setBasicContainers(ArrayList<Container> cont) {
		this.basicContainers = cont;
	}
	/**
	 * setter for port of the ship
	 * @param p new port of the ship
	 */
	public void setPort(Port p) {
		this.currentPort = p;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

