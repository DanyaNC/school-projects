import java.util.ArrayList;
import java.util.List;

public class Place extends Thing {

	protected ArrayList<Thing> contents = new ArrayList<>();
	protected ArrayList<Place> exits = new ArrayList<>();
	
	public Place ( String name, String description ) {
		super(name, description);
	}
	
	public Place ( String name, String description, Place containingLocation ) {
		super(name, description, containingLocation);
	}
	
	public String getDescription( ) {
		StringBuffer sb = new StringBuffer( );
		sb.append(super.getDescription());
		sb.append("\n");
		for (Thing item : contents) {
			sb.append(this.name + " contains " + item.getName( ) + "\n");
			if (item instanceof Place) {
				sb.append(item.getDescription());
			}
		}
		for (Place loc : exits) {
			sb.append("From " + this.name + " one can go to " + loc.getName( ) + ".\n");
		}
		return sb.toString( );
	}

	public void addContent( Thing t ) {
		if (!contents.contains(t))   // short for a loop.
			contents.add(t);
		t.setLocation(this);
	}
	
	public void addExit( Place p ) {
		if (!exits.contains( p ))
			exits.add(p);
	}
	
	public void removeContent( Thing t) {
		if (contents.contains(t))
			contents.remove(t);
	}
	
	public void removeExit( Place p ) {
		if (exits.contains( p ))
			exits.remove(p);
	}
	
	public List<Thing> getContents( ) {
		return contents;
	}
	
	public List<Place> getExits( ) {
		return exits;
	}
	
}
