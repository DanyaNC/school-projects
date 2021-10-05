import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * 
 */
public class SmallWorld5 {
	
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
		System.out.println("ACTION!");
		for ( Actor a : players ) {
			a.doSomething( );
		}
	}

	private static void setUpStage() {
		stage = new Place("THE STAGE","The biggest Place object, which shall contain all others.");
		Place lectureRoom = new Place("Classroom","The CS 361 lecture room.");
		stage.addContent(lectureRoom);
		Place gatesBlakeLobby = new Place("Gates-Blake Hall lobby", "There are lots of tables and a coffeeshop.");
		stage.addContent(gatesBlakeLobby);
		Place duckPond = new Place("The duck pond","A nice grassy area.  Also a pond with ducks, turtles, and fountain.");
		stage.addContent(duckPond);
		lectureRoom.addExit(gatesBlakeLobby);
		gatesBlakeLobby.addExit(lectureRoom);
		gatesBlakeLobby.addExit(duckPond);
		duckPond.addExit(gatesBlakeLobby);
		Thing chalk = new Thing("Chalk","A piece of chalk.",lectureRoom);
		Thing chair = new Thing("Chair","A chair you can sit on.",lectureRoom);
		Thing chair2 = new Thing("Chair","A different chair you can sit on.",lectureRoom);
		Thing chair3 = new Thing("Chair","A third chair you can sit on.",lectureRoom);
		
		Looker alice = new Looker("Alice","A generic student.",lectureRoom);
		Looker tom = new Looker("Tom","A nutty professor.",lectureRoom);
		
		Mover duck = new Mover("Duck","A cute little duck.  Quack, quack!",duckPond);
		Mover roadRunner = new Mover("Roadrunner","A fast roadrunner.  Beep, beep!",duckPond,5);

		Taker collector = new Taker("Collector","A clever-looking individual with huge pockets.",lectureRoom);
		Taker livingSack = new Taker("Living Sack","A giant animated sack.  It looks hungry.",lectureRoom);
		
		Spreader smoke = new Spreader("Smoke","A little puff of smoke.",lectureRoom);
		
		System.out.println(lectureRoom.getDescription());
//		System.exit(0);
		
		players = new ArrayList<>();
		players.add(tom);
		players.add(alice);
		players.add(duck);
		players.add(roadRunner);
		players.add(collector);
		players.add(livingSack);
		players.add(smoke);
		
		// Ideas: Add inventory to the actors
		// currently: Taker  =====  PLACE + ACTOR
		//     inventory can have a size limit.   doesn't necessarily get filled up greedily.
		// Things could have weights.
		// Add more physics  -- weights, sizes, 
		// Game attributes (health/ hit points / combat, speech)
		// Make more of a play -- scripted events, more detailed interaction models
		// Simulate other processes or behaviors.  e.g. pandemic
		
	}

}
