
public class Mover extends Thing implements Actor {

	private int speed;
	
	
	// pattern for method with 2 default parameter values:
	public Mover( String name, String description ) {
		this(name, description, null, 1);
	}
	
	public Mover( String name, String description, Place location ) {
		this( name, description, location, 1);
	}
	
	public Mover ( String name, String description, Place location, int speed) {
		super( name, description, location);
		this.speed = speed;
	}

	public void setSpeed( int s ) {
		speed = s;
	}
	
	public int getSpeed( ) {
		return speed;
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		for (int i = 0; i < speed; i++) {
			Place chosenExit = Util.randomItem(location.getExits());
			if (chosenExit == null) {
				System.out.println(name + " tries to leave " + location.getName() + ", but can't find a way out.");
				continue;
			}
			System.out.println(name + " moves from " + location.getName() + " to " + chosenExit.getName() + ".");
			location.removeContent(this);
			chosenExit.addContent(this);
		}
	}

}
