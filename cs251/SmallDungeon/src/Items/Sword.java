package Items;

import Util.Item;
import Util.Thing;
import Characters.*;

public class Sword extends Thing implements Item {
    private Hero hero;

    public Sword(String name, String description, Hero hero) {
        super(name, description);
        this.hero = hero;
    }

    @Override
    public void doEffect() {
        hero.receiveSword();
    }

}
