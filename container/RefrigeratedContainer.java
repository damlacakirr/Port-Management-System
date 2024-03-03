

package container;

public class RefrigeratedContainer extends HeavyContainer{
	
	
	public RefrigeratedContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	
	
	@Override
	public double consumption() {
		return this.weight*5;
	}
	
}



