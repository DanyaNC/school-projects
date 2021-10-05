package Enemies;

import Util.Mob;
import Util.Thing;

public class Dragon extends Thing implements Mob {
    private int hp = 75;
    private int attackDamage = 30;

    public Dragon(String name, String description) {
        super(name, description);
    }

    @Override
    public int attack() {
        System.out.println("The dragon breathes fire on the hero, dealing " + attackDamage + " damage.");
        return attackDamage;
    }

    //Unused content
    private String fireBreath() {
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
