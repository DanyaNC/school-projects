import java.util.List;

public class Looker extends Thing implements Actor {

	public Looker(String name, String description) {
		super(name, description);
	}

	public Looker(String name, String description, Place location) {
		super(name, description,location);
	}
	
	@Override
	public void doSomething() {
		// TODO Auto-generated method stub

		List<Thing> stuff = location.getContents();
		
		Thing chosenItem = Util.randomItem(stuff);
		System.out.println(name + " looks at " + chosenItem.getName());
		System.out.println(name + " sees: " + chosenItem.getDescription());
	}

}
