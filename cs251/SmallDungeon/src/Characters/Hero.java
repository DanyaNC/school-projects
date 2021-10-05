package Characters;

import Util.Thing;

import java.util.Random;

public class Hero extends Thing {
    private int hp;
    private static String[] names = new String[] {"Tom", "Ben", "Dan", "Matthew", "Ricardo", "John", "Evan",
    "Rebecca", "Vanessa", "Sophie", "Emily", "Hannah", "Olivia", "Samantha"};
    private int attackDamage;
    private Random random;
    private int location;

    public Hero(){
        super(names[(int) (Math.random()*names.length)], "A feeble hero who sets out in hopes" +
                " of conquering a dungeon.");
        attackDamage = 25;
        hp = 100;
        random = new Random();
    }

    public int attack() {
        int negativeOrPositive = random.nextInt(2);
        int randomDamageModifier = random.nextInt(10);
        int damageDealt;
        //Negative damage modifier
        if(negativeOrPositive == 0) {
            damageDealt = attackDamage - randomDamageModifier;
            System.out.println(super.getName() + " attacked for " + damageDealt);
        } else {
            //Positive damage modifier
            damageDealt = attackDamage + randomDamageModifier;
            System.out.println(super.getName() + " attacked for " + damageDealt);
        }
        return damageDealt;
    }

    public int getLocation() {
        return location;
    }

    public void drinkPotion() {
        int hpIncrease = random.nextInt(51);
        hp += hpIncrease;
        System.out.println(super.getName() + " drinks the potion and feels healthier. They gained " +
                hpIncrease + " hp.");
        System.out.println(super.getName() + " now has " + hp + " hp.");

    }

    public void takesDamage(int damage) {
        hp -= damage;
    }

    public int getHp() {
        return hp;
    }

    public void receiveSword() {
        int damageIncrease = random.nextInt(25);
        attackDamage += damageIncrease;
        System.out.println("The hero equips the new sword and feels as if they are stronger. They gained " +
                damageIncrease + " attack damage.");
    }

    public void moveForward() {
        location++;
    }

}
