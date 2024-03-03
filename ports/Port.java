
package ports;
import interfaces.IPort;
import container.*;
import ships.*;
import java.util.*;


public class Port implements IPort{
	
	private int ID;
	
	private double X;
	
	private double Y;
	
	private int nofBasic;
	
	
	private int nofHeavy;
	
	private int nofRefrigerated;
	
	private int nofLiquid;
	
	ArrayList<Container> containers = new ArrayList<Container>();
	
	ArrayList<Container> basics = new ArrayList<Container>();
	
	ArrayList<Container> heavies = new ArrayList<Container>();
	
	ArrayList<Container> refrigerateds = new ArrayList<Container>();
	
	ArrayList<Container> liquids = new ArrayList<Container>();
	
	ArrayList<Ship> history = new ArrayList<Ship>();
	
	ArrayList<Ship> current = new ArrayList<Ship>();
	
	public Port(int id, double X, double Y){
		ID = id;
		this.X = X;
		this.Y = Y;
	}
	
	@Override
	public void incomingShip(Ship s) {
		if (!history.contains(s)) {
			history.add(s);
		}
		current.add(s);
	}
	
	
	@Override
	public void outgoingShip(Ship s) {
		current.remove(s);
	}
	
	
	public double getDistance(Port other) {
		return Math.pow(Math.pow(other.getX()-X,2)+Math.pow(other.getY()-Y, 2),0.5);
				
	}
	
	public double getX() {
		return X;
	}
	
	public double getY() {
		return Y;
	}
	
	public int getId() {
		return ID;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
		if (container instanceof BasicContainer) {
			nofBasic+=1;
			basics.add(container);
		}
		
		else if (container instanceof RefrigeratedContainer) {
			nofRefrigerated+=1;
			refrigerateds.add(container);
		}
		
		else if (container instanceof LiquidContainer) {
			nofLiquid+=1;
			liquids.add(container);
		}
		
		else if (container instanceof HeavyContainer) {
			nofHeavy+=1;
			heavies.add(container);
		}
	}
	
	
	public void removeContainer(Container container) {
		containers.remove(container);
		if (container instanceof BasicContainer) {
			nofBasic-=1;
			basics.remove(container);
		}
		
		else if (container instanceof RefrigeratedContainer) {
			nofRefrigerated-=1;
			refrigerateds.remove(container);
		}
		
		else if (container instanceof LiquidContainer) {
			nofLiquid-=1;
			liquids.remove(container);
		}
		
		else if (container instanceof HeavyContainer) {
			nofHeavy-=1;
			heavies.remove(container);
		}
	}
	
	public ArrayList<Container> getContainers() {
		Collections.sort(containers, new CompareContainer());
		return containers;
	}
	
	
	public ArrayList<Ship> getCurrent() {
		Collections.sort(current,new CompareShip());
		return current;
	}
	
	
	public int getNofBasic() {
		return nofBasic;
	}
	
	
	public int getNofHeavy() {
		return nofHeavy;
	}
	
	public int getNofLiquid() {
		return nofLiquid;
	}
	
	public int getNofRefrigerated() {
		return nofRefrigerated;
	}
	
	
	public ArrayList<Container> getBasics() {
		Collections.sort(basics, new CompareContainer());
		return basics;
	}
	
	public ArrayList<Container> getRefrigerateds() {
		Collections.sort(refrigerateds, new CompareContainer());
		return refrigerateds;
	}
	
	public ArrayList<Container> getLiquids() {
		Collections.sort(liquids, new CompareContainer());
		return liquids;
	}
	
	public ArrayList<Container> getHeavies() {
		Collections.sort(heavies, new CompareContainer());
		return heavies;
	}
}



