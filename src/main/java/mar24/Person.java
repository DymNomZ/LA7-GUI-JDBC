package mar24;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Person implements Serializable {
    String name;
    int age;
    Date birthday;
    boolean gender;
    transient int crimes;
    Pet pet;

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        return name + " (" + age + ") " + birthday.toString() + " ICC Crimes: " + crimes + " along with " + pet.name;
    }
}
