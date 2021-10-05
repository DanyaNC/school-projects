import java.util.List;

public class Taker extends Place implements Actor {	
	
	// pattern for method with 2 default parameter values:
	public Taker( String name, String description ) {
		this(name, description, null);
	}
	
	public Taker( String name, String description, Place location ) {
		super( name, description, location);
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		Thing chosenItem = Util.randomItem(location.getContents());
		if (chosenItem == null) {
			System.out.println(name + " tries to take something, but fails.");
			return;
		}
		if (chosenItem == this) {
			System.out.println(name + " tries to take itself, but decides not to.");
			return;
		}
		System.out.println(name + " puts " + chosenItem.getName() + " in its pocket.");
		location.removeContent(chosenItem);
		addContent(chosenItem);
	}

}
