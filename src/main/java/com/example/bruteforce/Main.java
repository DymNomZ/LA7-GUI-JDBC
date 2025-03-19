package com.example.bruteforce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String pass = sc.next();

        List<Thread> threads = new ArrayList<>();
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int ctr = 1;
        for(char v : vowels){
            for(int j = pass.length()-1; j >= 0; j--){
                threads.add(new Thread(new BruteForceRunnable(ctr++, v, j, pass)));
            }
        }

        for(Thread t : threads) t.start();

        while(true){
            for(Thread t : threads){
                if(!t.isAlive()){
                    System.out.println("DONE");
                    for (Thread t1 : threads){
                        t1.interrupt();
                    }
                    return;
                }
            }
        }
    }
}
