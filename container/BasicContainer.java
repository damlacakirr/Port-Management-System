


package container;


public class BasicContainer extends Container{
	
	
		public BasicContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	

	@Override
	public double consumption() {
		return this.weight*2.5;
	}
	
}



