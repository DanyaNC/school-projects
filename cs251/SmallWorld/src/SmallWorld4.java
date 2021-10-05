import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author tom
 * 
 */
public class SmallWorld4 {
	
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

		System.out.println(lectureRoom.getDescription());
//		System.exit(0);
		
		players = new ArrayList<>();
		players.add(tom);
		players.add(alice);
	}

}
