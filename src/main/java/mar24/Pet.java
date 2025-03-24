package mar24;

import java.io.Serializable;

public class Pet implements Serializable {
    String name;

    public Pet(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println("eating");
    }
}
