
package main;

import container.*;
import ports.*;
import ships.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {

		
		Scanner in = new Scanner(new File("input0.txt"));
		
		PrintStream out = new PrintStream(new File("output0.txt"));

		
		ArrayList<Ship> ships = new ArrayList<Ship>();
		
		
		ArrayList<Port> ports = new ArrayList<Port>();
		
		
		ArrayList<Container> conts = new ArrayList<Container>();
		
		
		int N = in.nextInt();
		
		int contID = 0;
		
		
		int portID = 0;
		
		
		int shipID = 0;
		in.nextLine();

		for (int i = 0; i < N; i++) {
			
			
			String line = in.nextLine();
			
			String[] parts = line.split("\\s+");
			
			int action = Integer.parseInt(parts[0]);

			if (action == 1) {
				
				
				int portOfCont = Integer.parseInt(parts[1]);
				
				
				int weightOfCont = Integer.parseInt(parts[2]);

				if (parts.length == 3) {
					if (weightOfCont <= 3000) {
						
						
						BasicContainer container = new BasicContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					} else if (weightOfCont > 3000) {
						
						
						HeavyContainer container = new HeavyContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}

				}

				else if (parts.length == 4) {
					
					
					String contType = parts[3];

					if (contType.equals("R")) {
						
						RefrigeratedContainer container = new RefrigeratedContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}

					else if (contType.equals("L")) {
						
						LiquidContainer container = new LiquidContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}
				}
			}

			else if (action == 2) {
				
				int portOfShip = Integer.parseInt(parts[1]);
				
				int maxWeight = Integer.parseInt(parts[2]);
				
				int maxNofConts = Integer.parseInt(parts[3]);
				
				int maxNofHeavy = Integer.parseInt(parts[4]);
				
				int maxNofRefrigerated = Integer.parseInt(parts[5]);
				
				int maxNofLiquid = Integer.parseInt(parts[6]);
				
				double fuelConsumption = Double.parseDouble(parts[7]);
				
				Ship ship = new Ship(shipID, ports.get(portOfShip), maxWeight, maxNofConts, maxNofHeavy,
						maxNofRefrigerated, maxNofLiquid, fuelConsumption);
				
				ships.add(ship);
				ports.get(portOfShip).incomingShip(ship);
				shipID += 1;

			}

			else if (action == 3) {
				
				double x = Double.parseDouble(parts[1]);
				
				double y = Double.parseDouble(parts[2]);
				
				Port port = new Port(portID, x, y);
				ports.add(port);
				portID += 1;
			}

			else if (action == 4) {
				
				Ship shipToLoad = ships.get(Integer.parseInt(parts[1]));
				
				
				Container contToLoad = conts.get(Integer.parseInt(parts[2]));

				if (shipToLoad.getCurrentPort().getContainers().contains(contToLoad)) {
					if (shipToLoad.load(contToLoad)) {
						shipToLoad.getCurrentPort().removeContainer(contToLoad);
					}
				}
			}

			else if (action == 5) {
				
				
				Ship shipToUnload = ships.get(Integer.parseInt(parts[1]));
				
				
				Container contToUnload = conts.get(Integer.parseInt(parts[2]));
				if (shipToUnload.getCurrentContainers().contains(contToUnload)) {
					shipToUnload.unLoad(contToUnload);
				}
			}

			else if (action == 6) {
				
				Ship shipToTravel = ships.get(Integer.parseInt(parts[1]));
				
				Port destinationPort = ports.get(Integer.parseInt(parts[2]));
				shipToTravel.sailTo(destinationPort);
			}

			else if (action == 7) {
				
				Ship shipiFulle = ships.get(Integer.parseInt(parts[1]));
				
				double amount = Double.parseDouble(parts[2]);
				shipiFulle.reFuel(amount);				
				
			}
		}

		for (Port port : ports) {
			out.printf("Port " + port.getId() + ": (" + String.format("%.2f", port.getX()) + ", "
					+ String.format("%.2f", port.getY()) + ")\n");
			if (port.getNofBasic() > 0) {
				out.print("  BasicContainer:");
				for (Container basic : port.getBasics()) {
					out.print(" "+basic.getID());
				}
				out.println();
			}
			if (port.getNofHeavy() > 0) {
				out.print("  HeavyContainer:");
				for (Container heavy : port.getHeavies()) {
					out.print(" "+heavy.getID());
				}
				out.println();
			}
			if (port.getNofRefrigerated() > 0) {
				out.print("  RefrigeratedContainer:");
				for (Container ref : port.getRefrigerateds()) {
					out.print(" "+ref.getID());
				}
				out.println();
			}
			if(port.getNofLiquid()>0) {
				out.print("  LiquidContainer:");
				for (Container liquid:port.getLiquids()) {
					out.print(" "+liquid.getID());
				}
				out.println();
			}
			
			if (port.getCurrent().size()!=0) {
				for(Ship ship:port.getCurrent()) {
					out.println("  Ship "+ship.getID()+": "+String.format("%.2f", ship.getFuel()));
					if (ship.getBasics().size()>0) {
						out.print("    BasicContainer:");
						for (Container basic: ship.getBasics()) {
							out.print(" "+basic.getID());
						}
						out.println();
					}
					if (ship.getHeavies().size()>0) {
						out.print("    HeavyContainer:");
						for (Container heavy: ship.getHeavies()) {
							out.print(" "+heavy.getID());
						}
						out.println();
					}
					if (ship.getRefrigerateds().size()>0) {
						out.print("    RefrigeratedContainer:");
						for (Container ref: ship.getRefrigerateds()) {
							out.print(" "+ref.getID());
						}
						out.println();
					}
					if (ship.getLiquids().size()>0) {
						out.print("    LiquidContainer:");
						for (Container liq: ship.getLiquids()) {
							out.print(" "+liq.getID());
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
