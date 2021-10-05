import java.util.List;

public class Spreader extends Thing implements Actor {

	private int speed;
		
	// pattern for method with 2 default parameter values:
	public Spreader( String name, String description ) {
		this(name, description, null, 1);
	}
	
	public Spreader( String name, String description, Place location ) {
		this( name, description, location, 1);
	}
	
	public Spreader ( String name, String description, Place location, int speed) {
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
			List<Place> placeList = location.getExits();
			placeList.add(location);
			Place spreadTarget = Util.randomItem(placeList);
			if (spreadTarget == null) {
				System.out.println(name + " tries to spread at " + location.getName() + ", but failed for some reason.");
				continue;
			}
			System.out.println(name + " spreads from " + location.getName() + " to " + spreadTarget.getName() + ".");
			Spreader newSpreader = new Spreader(this.name, this.description, spreadTarget, this.speed);
			// Note: This doesn't blow up exponentially (yet!) because the new Spreader object isn't being added to the players list
			// in the main class.
			
			// Idea: make everything that is an actor have its doSomething method invoked every turn, without being in players list.
			// Idea: give Spreader access to the players list, so that it can add to it.
		}
	}

}
