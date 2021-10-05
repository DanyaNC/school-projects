package Enemies;

import Util.Mob;
import Util.Thing;

public class Boss extends Thing implements Mob {
    private int hp = 100;
    private int attackDamage = 40;

    public Boss() {
        super("Artorias", "A knight clad in dark armor covered by a purple aura, wielding " +
                "a sword easily the size of their own body.");
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int attack() {
        System.out.println("Artorias swings his blade and lands an attack dealing " + attackDamage + " damage.");
        return attackDamage;
    }
    @Override
    public void takesDamage(int damage) {
        hp -= damage;
    }

}
