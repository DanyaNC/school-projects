package Items;

import Characters.Hero;
import Util.Item;
import Util.Thing;

public class Potion extends Thing implements Item {
    private Hero hero;

    public Potion(String name, String description, Hero hero) {
        super(name,description);
        this.hero = hero;
    }

    @Override
    public void doEffect() {
        hero.drinkPotion();
    }

}
