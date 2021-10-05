package Enemies;

import Util.Mob;
import Util.Thing;

public class Spider extends Thing implements Mob {
    private int hp = 35;
    private int attackDamage = 15;

    public Spider(String name, String description) {
        super(name, description);
    }

    public int attack() {
        System.out.println("The spider lunged forwards and attacks for " + attackDamage + " damage.");
        return attackDamage;
    }

    private String webShot() {
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
