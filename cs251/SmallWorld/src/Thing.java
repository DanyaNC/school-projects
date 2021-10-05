
public class Thing {

	protected String name;
	protected String description;
	protected Place location;
	
	public String getDescription( ) {
		return name + ": " + description;
	}
	
	public String getName( ) {
		return name;
	}
	
	// Here is the pattern for "default parameter values" in Java:
	public Thing( String name, String description ) {

		this(name,description,null);
	}
	
	public Thing( String name, String description, Place location ) {
		this.name = name;
		this.description = description;
		if (location != null) {
			location.addContent(this);
		}
		System.out.println("Created a " + name);
	}
	
	public void setLocation(Place p) {
		location = p;
	}
	
	public Place getLocation( ) {
		return location;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		if (o == null)
//			return false;
//		if (o.getClass() != getClass())
//			return false;
//		return (name.equals(((Thing)o).name));
//	}
	
	
}
