package mar24;

import java.io.*;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Person> people = new ArrayList<>();

        try {
            for(int i = 0; true; i++){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people-"+i+".txt"));
                people.add((Person) ois.readObject());
                ois.close();
            }


        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getClass());
//            throw new RuntimeException(e);
        }

        for(int i = 0; i < 2; i++){

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Age: ");
            int age = sc.nextInt();

            Person p = new Person(name, age, Date.from(Instant.now()));
            p.crimes = (int) (Math.random() * 10);
            System.out.print("Pet Name: ");
            p.pet = new Pet(sc.next());
            people.add(p);

        }

        for(Person p : people){

            System.out.println(p);

            int i = 0;

            try {

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people-"+people.indexOf(p)+".txt"));
                oos.writeObject(p);
                oos.close();

            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }

    }
}
