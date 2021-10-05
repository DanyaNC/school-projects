package Util;
import Enemies.*;

public class MobSpawner {


    public MobSpawner() {

    }

    public Thing getEnemy() {
        int chance = (int) (Math.random()*100 + 1);
        //40% Chance Enemies.Spider/Enemies.Goblin, 20% chance Enemies.Dragon
        if(chance <= 40) {
            return new Spider("Spider", "A menacing black spider, whose height reaches up to " +
                    "hero's knees.");
        } else if (chance <= 80) {
            return new Goblin("Goblin", "A small green creature half your height who glares at " +
                    "the hero with a menacing" +
                    " gaze");
        } else {
            return new Dragon("Dragon", "A tall, grandiose creature with shining red scales " +
                    "that deems the hero trivial" +
                    " but seems perturbed by the fact that the hero has entered its domain.");
        }
    }

}
