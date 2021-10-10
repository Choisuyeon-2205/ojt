public class Seat {
	private String name;
	
	public Seat() {
		name= null;
	}
	
	//getter
	public String getName() {
		return name;
	}

	public void reserveName(String name) {
		this.name= name;
	}
	
	public boolean isOccupied() {
		if(name==null)
			return false;
		else
			return true;
	}
	
	public boolean match(String name) {
		return (name.equals(name));
	}
	
	public void cancel() {
		name= null;
	}
}
