package Util;
import Enemies.*;
import Characters.*;
import Items.*;
//Each room will have a type, and contents
//i.e. a mob room, item room, or an event room.
import java.util.List;
import java.util.ArrayList;
public class Room {
    private char type;
    private List<Thing> contents;
    private MobSpawner spawner;
    private Hero hero;

    public Room(MobSpawner spawner, Hero hero) {
        this.spawner = spawner;
        contents = new ArrayList<>();
        contents.add(generateThing());
        this.hero = hero;
    }

    public Room(Boss boss) {
        type = 'B';
        contents = new ArrayList<>();
        contents.add(boss);
    }

    private Thing generateThing() {
        int chance = (int) (Math.random() * 100 + 1);

        //25% Chance of a room containing a merchant
        //25% chance of a room containing an item
        //50% chance of a room containing an enemy
        if(chance <= 25) {
            type = 'M';
            return new Merchant("Merchant", "A humble merchant with something to sell.");
        } else if(chance <= 50) {
            type = '?';
            return new UnknownItem();
        } else {
            type = 'X';
            return generateEnemy();
        }
    }

    public List<Thing> getContents() {
        return contents;
    }



    private Thing generateEnemy() {
        return spawner.getEnemy();
    }


    public char getType() {
        return type;
    }

}
