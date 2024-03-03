


package container;


public class HeavyContainer extends Container{

	
	public HeavyContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	@Override
	public double consumption() {
		return this.weight*3;
	}
	
}

