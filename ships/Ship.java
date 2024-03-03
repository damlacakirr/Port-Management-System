
package ships;
import interfaces.IShip;
import java.util.*;
import ports.Port;
import container.*;


public class Ship implements IShip{
	
	
	private int ID;
	
	private double fuel;
	
	private Port currentPort;
	
	private int currentTotalWeight = 0;
	
	private int totalWeightCapacity;
	
	private int maxNumberOfAllContainers;
	
	private int maxNumberOfHeavyContainers;
	
	private int maxNumberOfRefrigeratedContainers;
	
	private int maxNumberOfLiquidContainers;
	
	private double fuelConsumptionPerKM;
	
	ArrayList<Container> allContainers = new ArrayList<Container>();
	
	ArrayList<Container> basics = new ArrayList<Container>();
	
	ArrayList<Container> heavies = new ArrayList<Container>();
	
	ArrayList<Container> refrigerateds = new ArrayList<Container>();
	
	ArrayList<Container> liquids = new ArrayList<Container>();
	
	private int nofHeavyContainers = 0;
	
	private int nofRefrigeratedContainers = 0;
	
	private int nofLiquidContainers = 0;
	
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, 
			int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
			int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) 
	{
		this.ID = ID;
		currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		
	}
	
	@Override
	public boolean load(Container cont) {
		if (allContainers.size()<maxNumberOfAllContainers && currentTotalWeight+cont.getWeight()<=totalWeightCapacity &&
				!allContainers.contains(cont)) {
			if (cont instanceof BasicContainer) {
				basics.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}

			else if (cont instanceof LiquidContainer && nofLiquidContainers<maxNumberOfLiquidContainers && nofHeavyContainers<maxNumberOfHeavyContainers) {
				nofLiquidContainers+=1;
				liquids.add(cont);
				nofHeavyContainers+=1;
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else if (cont instanceof RefrigeratedContainer && nofRefrigeratedContainers<maxNumberOfRefrigeratedContainers && nofHeavyContainers<maxNumberOfHeavyContainers) {
				nofRefrigeratedContainers+=1;
				nofHeavyContainers+=1;
				refrigerateds.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else if (cont instanceof HeavyContainer && nofHeavyContainers<maxNumberOfHeavyContainers && !(cont instanceof LiquidContainer) && !(cont instanceof RefrigeratedContainer)) {
				nofHeavyContainers+=1;
				heavies.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else {return false;}
			
		}
		else {return false;}
	}
	
	@Override
	public boolean unLoad(Container cont) {
		if (allContainers.contains(cont)) {
			currentPort.addContainer(cont);
			allContainers.remove(cont);
			if (cont instanceof BasicContainer) {
				basics.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof LiquidContainer) {
				nofLiquidContainers-=1;
				nofHeavyContainers-=1;
				liquids.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof RefrigeratedContainer) {
				nofRefrigeratedContainers-=1;
				nofHeavyContainers-=1;
				refrigerateds.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof HeavyContainer) {
				nofHeavyContainers-=1;
				heavies.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else {return false;}
		}
		else {return false;}
	}
	
	@Override
	public void reFuel(double newFuel) {
		fuel+=newFuel;
	}
	
	@Override
	public boolean sailTo(Port p) {
		double distance = currentPort.getDistance(p);
		double consumptionAmount = 0.0;
		for(Container container:allContainers) {
			consumptionAmount+=container.consumption();
		}
		consumptionAmount=(this.fuelConsumptionPerKM+consumptionAmount)*distance;
		
		if (consumptionAmount<=fuel) {
			currentPort.outgoingShip(this);
			p.incomingShip(this);
			currentPort = p;
			fuel-=consumptionAmount;
			return true;
		}
		else {return false;}
		
	}
	
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(allContainers, new CompareContainer());
		return allContainers;
	}
	
	public int getID() {
		return ID;
	}
	
	public Port getCurrentPort() {
		return currentPort;
	}
	
	public ArrayList<Container> getBasics() {
		Collections.sort(basics, new CompareContainer());
		return basics;
	}
	
	public ArrayList<Container> getHeavies() {
		Collections.sort(heavies, new CompareContainer());
		return heavies;
	}
	
	public ArrayList<Container> getRefrigerateds() {
		Collections.sort(refrigerateds, new CompareContainer());
		return refrigerateds;
	}
	
	public ArrayList<Container> getLiquids() {
		Collections.sort(liquids, new CompareContainer());
		return liquids;
	}
	
	public double getFuel() {
		return fuel;
	}
	
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}
}




