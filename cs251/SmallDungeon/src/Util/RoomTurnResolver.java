package Util;

import java.util.List;
import Enemies.*;
import Characters.*;
import Items.*;

public class RoomTurnResolver {
    private List<Room> roomsList;
    private Room currentRoom;
    private Hero hero;
    boolean gameState;
    private String name;

    public RoomTurnResolver(List<Room> rooms, Hero hero) {
        roomsList = rooms;
        currentRoom = roomsList.get(hero.getLocation());
        this.hero = hero;
        gameState = true;
        name = hero.getName();
    }

    public void gameTurn() {
        if(gameState) {
            switch(currentRoom.getType()) {
                case 'M':
                    System.out.println(name + " enters the room and inspects the figure " +
                            "siting down in front of a blanket with wares.");
                    System.out.println(currentRoom.getContents().get(0).getDescription());
                    int randomItem = (int) (Math.random()*2);
                    if(randomItem == 0) {
                        System.out.println("The merchant has a sword for sale.");
                    } else {
                        System.out.println("The merchant has a health potion for sale.");
                    }
                    int enoughMoney = (int) (Math.random()*2);
                    if(enoughMoney == 0) {
                        System.out.println(name + " attempts to buy the item but doesn't have enough gold.");
                    } else {
                        System.out.println(name + " purchases the item.");
                        if(randomItem == 0) {
                            Sword sword = new Sword("Iron Sword", "A sword sold by a merchant," +
                                    " while it's definitely better than your last, you can't help but feel" +
                                    " as if you got ripped off.", hero);
                            System.out.println(name + " receives a new sword and inspects it");
                            sword.getDescription();
                            sword.doEffect();
                        } else {
                            Potion potion = new Potion("Health Potion", "A small glass bottle " +
                                    "containing a strange" +
                                    " green liquid. Doesn't look particularly tasty.", hero);
                            System.out.println(name + " receives a health potion and inspects it.");
                            System.out.println(potion.getDescription());
                            potion.doEffect();
                        }
                    }
                    System.out.println("The hero moves on to the next room.");
                    hero.moveForward();
                    updateRoom();
                    break;
                case '?':
                    Item item = generateItem();
                    System.out.println(name+ " walks into the room and notices an item on the floor.");
                    Thing thing = (Thing) item;
                    System.out.println(thing.getDescription());
                    item.doEffect();
                    System.out.println(name + " moves on to the next room.");
                    hero.moveForward();
                    updateRoom();
                    break;
                case 'X':
                    System.out.println(name + " goes into the room and is met with an enemy. " +
                            "In front of them stands : " +
                            currentRoom.getContents().get(0).getDescription() );
                    Mob enemy = (Mob) currentRoom.getContents().get(0);
                    System.out.println(name + " begins combat with the enemy.");
                    battleResolver(enemy);
                    if(gameState) {
                        System.out.println(name + " moves on to the next room.");
                        hero.moveForward();
                        updateRoom();
                    }
                    break;
                case 'B':
                    System.out.println(name + " goes into the final room and is met with the boss." +
                            " In front of them stands : " + currentRoom.getContents().get(0).getName() + " " +
                            currentRoom.getContents().get(0).getDescription());
                    Mob boss = (Mob) currentRoom.getContents().get(0);
                    System.out.println(name + " begins combat with Artorias.");
                    battleResolver(boss);
                    if(boss.getHp() <= 0) {
                        gameState = false;
                    }
                    break;
                default:

            }
            gameTurn();
        } else if(hero.getHp() > 0) {
            System.out.println(name + " has beaten the dungeon!");
        }

    }

    private void updateRoom() {
        currentRoom = roomsList.get(hero.getLocation());
    }

    private Item generateItem() {
        int chance = (int) Math.random() * 100 + 1;
        //70% chance for potion
        //30% chance for sword
        if(chance <= 70) {
            return new Potion("Health Potion", "A small glass bottle containing a strange" +
                    " green liquid. Doesn't look particularly tasty.", hero);
        } else {
            return new Sword("An iron sword", "A fine looking sword, nothing too fancy but" +
                    " definitely sharper than the current one.", hero);
        }
    }


    private void battleResolver(Mob enemy) {
        while(hero.getHp() > 0 && enemy.getHp() > 0) {
            enemy.takesDamage(hero.attack());
            System.out.println("The " + ((Thing) enemy).getName() + " now has " + enemy.getHp() + " hp.");
            if(enemy.getHp() > 0) {
                hero.takesDamage(enemy.attack());
                System.out.println(name + " now has " + hero.getHp() + " hp.");
                if(hero.getHp() <= 0 ) {
                    System.out.println(name + " has fallen to a " + ((Thing) enemy).getName());
                    System.out.println("Game Over.");
                    gameState = false;
                    break;
                }
            } else {
                System.out.println("The enemy has fallen. " + name + " successfully defeated the " +
                        ((Thing) enemy).getName() + "!");
                break;
            }
        }
    }

}
