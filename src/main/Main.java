
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.util.Scanner;

import containers.RefrigeratedContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.BasicContainer;
import ships.Ship;
import ports.Port;
import java.util.ArrayList;

/**
 * Creating ships, ports, and containers occurs in this class.
 * load, unload, refuel and sailing processes occur in this class
 * It takes an input file and gives an output file. 
 * @author BASARAN
 *
 */
public class Main {
	/**
	 * method which triggers the whole project. Takes input and output files' location as parameter 
	 * and if there is no such file in that directory throws file not found exception.
	 * @param args takes input and output files' direction
	 * @throws FileNotFoundException there is no such file in this directory
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));

		int N = in.nextInt();
		in.nextLine();
		
		
		int containerID = 0;
		int portID = 0;
		int shipID = 0;
		
		ArrayList<Port> portList = new ArrayList<Port>();
		ArrayList<Container> containerList = new ArrayList<Container>();
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		
		
		for (int i=0; i<N; i++) {
			String line = in.nextLine();
			line = line.strip();
			line = line.replaceAll("\\s+", " ");
			String[] itemArray;
			itemArray = line.split(" ");
			if (itemArray[0].equals("1")) {
				if (itemArray.length == 4) {
					if (itemArray[3].equals("R")) {
						RefrigeratedContainer refrigeratedContainer = new RefrigeratedContainer(containerID,Integer.parseInt(itemArray[2]));
						containerList.add(refrigeratedContainer);
						portList.get(Integer.parseInt(itemArray[1])).getContainers().add(refrigeratedContainer);
						portList.get(Integer.parseInt(itemArray[1])).getRefrigeratedContainers().add(refrigeratedContainer);
						containerID += 1;
					}
					else if (itemArray[3].equals("L")) {
						LiquidContainer liquidContainer = new LiquidContainer(containerID,Integer.parseInt(itemArray[2]));
						containerList.add(liquidContainer);
						portList.get(Integer.parseInt(itemArray[1])).getContainers().add(liquidContainer);
						portList.get(Integer.parseInt(itemArray[1])).getLiquidContainers().add(liquidContainer);
						containerID += 1;
					}}
					
				else {
					if (Integer.parseInt(itemArray[2])>3000) {
						HeavyContainer heavyContainer = new HeavyContainer(containerID,Integer.parseInt(itemArray[2]));
						containerList.add(heavyContainer);
						portList.get(Integer.parseInt(itemArray[1])).getContainers().add(heavyContainer);
						portList.get(Integer.parseInt(itemArray[1])).getHeavyContainers().add(heavyContainer);
						containerID += 1;
					}
					else if (Integer.parseInt(itemArray[2])<=3000 && Integer.parseInt(itemArray[2])>=0) {
						BasicContainer basicContainer = new BasicContainer(containerID,Integer.parseInt(itemArray[2]));
						containerList.add(basicContainer);
						portList.get(Integer.parseInt(itemArray[1])).getContainers().add(basicContainer);
						portList.get(Integer.parseInt(itemArray[1])).getBasicContainers().add(basicContainer);
						containerID += 1;
				}
				}
				
			}
			else if (itemArray[0].equals("2") ) {
				if (Integer.parseInt(itemArray[2]) > 0) 
				{
				Ship ship = new Ship(shipID,portList.get(Integer.parseInt(itemArray[1])),Integer.parseInt(itemArray[2]),Integer.parseInt(itemArray[3]),
						Integer.parseInt(itemArray[4]), Integer.parseInt(itemArray[5]), Integer.parseInt(itemArray[6]), Double.parseDouble(itemArray[7]));
				shipList.add(ship);
				shipID += 1;
				}
			}
			else if (itemArray[0].equals("3")) {
				Port port = new Port(portID, Double.parseDouble(itemArray[1]),Double.parseDouble(itemArray[2]));
				portList.add(port);
				portID += 1;
			}
			else if (itemArray[0].equals("4") ) {
				if (Integer.parseInt(itemArray[1]) < shipID && Integer.parseInt(itemArray[2])< containerID) {
					shipList.get(Integer.parseInt(itemArray[1])).load(containerList.get(Integer.parseInt(itemArray[2])));
				}
			}
			else if (itemArray[0].equals("5")) {
				if (Integer.parseInt(itemArray[1]) < shipID && Integer.parseInt(itemArray[2])< containerID) {
					shipList.get(Integer.parseInt(itemArray[1])).unLoad(containerList.get(Integer.parseInt(itemArray[2])));
				}
			}
			else if (itemArray[0].equals("6") ) {
				if (Integer.parseInt(itemArray[1]) < shipID && Integer.parseInt(itemArray[2])< portID) {
					shipList.get(Integer.parseInt(itemArray[1])).sailTo(portList.get(Integer.parseInt(itemArray[2])));
				}
			}
			else if (itemArray[0].equals("7")) {
				if(Integer.parseInt(itemArray[1]) < shipID) {
					shipList.get(Integer.parseInt(itemArray[1])).reFuel(Double.parseDouble(itemArray[2]));
				}
			}
		}
		
		
		 
		for (int i=0 ; i<portID ; i++) {
			out.println("Port " + i + ": "+ portList.get(i).getCoordinates());
			if(portList.get(i).getBasicContainers().size() != 0) {
				out.print("  BasicContainer:");
				for (Container c : portList.get(i).containerSort(portList.get(i).getBasicContainers())) {
					out.print(" "+c.getID());
					}
				out.println();
			}
			if(portList.get(i).getHeavyContainers().size() != 0) {
				out.print("  HeavyContainer:");
				for (Container c : portList.get(i).containerSort(portList.get(i).getHeavyContainers())) {
					out.print(" " + c.getID());
					}
				out.println();}
			if(portList.get(i).getRefrigeratedContainers().size() != 0) {
				out.print("  RefrigeratedContainer:");
				for (Container c : portList.get(i).containerSort(portList.get(i).getRefrigeratedContainers())) {
					out.print(" " + c.getID());
					}
				out.println();}
			if(portList.get(i).getLiquidContainers().size() != 0) {
				out.print("  LiquidContainer:");
				for (Container c : portList.get(i).containerSort(portList.get(i).getLiquidContainers())) {
					out.print(" " + c.getID());
					}
				out.println();}
			if(portList.get(i).getCurrentShips().size() != 0) {
				for (Ship s : portList.get(i).getCurrentShips()) {
					out.println("  Ship " + s.getID() + ": " + String.format("%.2f", s.getFuel()));
					
					if(s.getBasicContainers().size() != 0) {
						out.print("    BasicContainer:");
						for (Container c : s.containerSort(s.getBasicContainers())) {
							out.print(" " + c.getID());
							}
						out.println();
					}
					if(s.getHeavyContainers().size() != 0) {
						out.print("    HeavyContainer:");
						for (Container c : s.containerSort(s.getHeavyContainers())) {
							out.print(" " + c.getID());
							}
						out.println();
					}
					if(s.getRefrigeratedContainers().size() != 0) {
						out.print("    RefrigeratedContainer:");
						for (Container c : s.containerSort(s.getRefrigeratedContainers())) {
							out.print(" " + c.getID());
							}
						out.println();
					}
					if(s.getLiquidContainers().size() != 0) {
						out.print("    LiquidContainer:");
						for (Container c : s.containerSort(s.getLiquidContainers())) {
							out.print(" " + c.getID());
							}
						out.println();
					}
				
				}
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

