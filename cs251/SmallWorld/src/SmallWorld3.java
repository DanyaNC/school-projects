import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * 
 */
public class SmallWorld3 {
	
	private static Place stage;
	private static List<Actor> players = new ArrayList<>();
	private static int endOfTime = 5;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1: Set up the environment:
		
		setUpStage();
		
		for ( int now = 0; now < endOfTime; now++ ) {
			performAct( now );		
		}
		
	}

	private static void performAct(int now) {
		System.out.println("Act " + now + ":");
		System.out.println(stage.getDescription());
		System.out.println();
		
		for ( Actor a : players ) {
			a.doSomething( );
		}
	}

	private static void setUpStage() {
		stage = new Place("THE STAGE","The biggest Place object, which shall contain all others.");
		Place room1 = new Place("Classroom","The CS 361 lecture room.");
		stage.addContent(room1);
		Thing chalk = new Thing("Chalk","A piece of chalk.",room1);
		Thing chair = new Thing("Chair","A chair you can sit on.",room1);
		Thing chair2 = new Thing("Chair","A different chair you can sit on.",room1);
		Thing chair3 = new Thing("Chair","A third chair you can sit on.",room1);
		
		Looker alice = new Looker("Alice","A generic student.",room1);
		Looker tom = new Looker("Tom","A nutty professor.",room1);

		System.out.println(room1.getDescription());
//		System.exit(0);
		
		players = new ArrayList<>();
		players.add(tom);
		players.add(alice);
	}

}
