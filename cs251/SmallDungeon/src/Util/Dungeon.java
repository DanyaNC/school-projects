package Util;
import Enemies.*;
import Characters.*;

import java.util.ArrayList;
import java.util.List;
public class Dungeon {
    private List<Room> Rooms;
    private int length;
    private RoomTurnResolver resolver;

    public Dungeon() {

        Hero hero = new Hero();
        MobSpawner spawner = new MobSpawner();
        Rooms = new ArrayList<>();
        int numRooms = (int) (Math.random()*3 + 3);
        length = numRooms*2 + 1;
        Boss b = new Boss();
        for(int i = 0; i < numRooms-1; i++) {
            Rooms.add(new Room(spawner, hero));
        }
        Rooms.add(new Room(b));
        drawMap();
        printContents();
        System.out.println("The hero enters the dungeon.");
        resolver = new RoomTurnResolver(Rooms, hero);
        resolver.gameTurn();

    }

    private void printContents() {
        int itemRooms = 0;
        int enemyRooms = 0;
        int merchants = 0;
        for(int i = 0; i < Rooms.size()-1; i++) {
            char roomType = Rooms.get(i).getType();
            if(roomType == '?') {
                itemRooms++;
            } else if (roomType == 'X') {
                enemyRooms++;
            } else {
                merchants++;
            }
        }
        System.out.println("The dungeon contains: ");
        System.out.println(itemRooms + " item rooms. ?");
        System.out.println(enemyRooms + " enemy rooms. X");
        System.out.println(merchants + " merchants. M");
        System.out.println("And one boss room. B");
        System.out.println();
    }

    //Create map of dungeon every turn
    public void drawMap() {
        System.out.println("Map of dungeon:");
        for(int i = 0; i < length; i++) {
            for(int x = 0; x < 5; x++) {
                if(i == 0) {
                    System.out.print("* ");
                } else {
                    if(x == 2) {
                        if(i%2==1) {
                            System.out.print(Rooms.get(Rooms.size() - (i/2) - 1).getType() + " ");
                        } else if(i!=0) {
                            System.out.print("| ");
                        }
                    } else {
                        System.out.print("* ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
