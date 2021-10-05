package Enemies;

import Util.Mob;
import Util.Thing;

public class Goblin extends Thing implements Mob {
    private int hp = 50;
    private int attackDamage = 20;

    public Goblin(String name, String description) {
        super(name, description);
    }


    @Override
    public int attack() {
        System.out.println("The goblin stabs the hero with a dagger, dealing " + attackDamage + " damage.");
        return attackDamage;
    }

    private String backstab() {
        return "Attacked";
    }
    @Override
    public void takesDamage(int damage) {
        hp -= damage;
    }

    @Override
    public int getHp() {
        return hp;
    }

}
