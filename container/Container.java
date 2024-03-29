

package container;


public abstract class Container {
	
	protected int ID;
	
		protected int weight;
	
		public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	
	
	public abstract double consumption();
	
	
	public boolean equals(Container other) {
		return this==other;
	}
	
		public int getWeight() {
		return weight;
	}
	
		public int getID() {
		return ID;
	}
}



