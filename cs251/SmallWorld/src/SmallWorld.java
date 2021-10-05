import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * 
 */
public class SmallWorld {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1: Set up the environment:

		List< Thing > stage = new ArrayList<>();
		
		Place room1 = new Place("Classroom","The CS 361 lecture room.");
		stage.add(room1);
		Thing chalk = new Thing("Chalk","A piece of chalk.");
		stage.add(chalk);
		Thing chair = new Thing("Chair","A chair you can sit on.");
		stage.add(chair);
		Thing chair2 = new Thing("Chair","A different chair you can sit on.");
		stage.add(chair2);
		Thing chair3 = new Thing("Chair","A third chair you can sit on.");
		stage.add(chair3);
		room1.addContent(chalk);
		room1.addContent(chair);
		room1.addContent(chair2);
		room1.addContent(chair3);
		
		Looker alice = new Looker("Alice","A generic student.");
		room1.addContent(alice);
		stage.add(alice);
		
		
		System.out.println(room1.getDescription());
//		System.exit(0);
		
		List< Actor > players = new ArrayList<>();
		Looker tom = new Looker("Tom","A nutty professor.");
	//	tom.setLocation(room1);
		room1.addContent(tom);
		stage.add(tom);
		players.add(tom);
		players.add(alice);
		
		// 2: Simulation loop: in each time step, prompt all the actors to do something fun.		
		final int endOfTime = 5;		
		for ( int now = 0; now < endOfTime; now++ ) {
			System.out.println("Act " + now + ":");
			System.out.print("Stage contains: ");
			for (Thing item : stage) {
				System.out.print(item.getName() + ",");
			}
			System.out.println();
			
			for ( Actor a : players ) {
				a.doSomething( );
			}
			
		}
		
	}

}
