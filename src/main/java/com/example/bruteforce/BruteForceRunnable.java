package com.example.bruteforce;

public class BruteForceRunnable implements Runnable {

    int id;
    char vowel;
    int pos;
    String pass;

    public BruteForceRunnable(int id, char vowel, int pos, String pass) {
        this.id = id;
        this.vowel = vowel;
        this.pos = pos;
        this.pass = pass;
    }

    @Override
    public void run() {
        String brute = "a".repeat(pass.length()-1);
        String act = brute.substring(0, pos) + vowel + brute.substring(pos, pass.length()-1);
        while(!pass.equals(act)){
            act = brute.substring(0, pos) + vowel + brute.substring(pos, pass.length()-1);
            System.out.println("Thread " + id + ": " + act);
            int i = 0;
            for(i = brute.length()-1; i >= 0; i--){
                if(brute.charAt(i) != 'z'){
                    break;
                }
            }

            if(Thread.currentThread().isInterrupted()) {
                break;
            }

            String newbrute;
            newbrute = brute.substring(0, i);
            newbrute += (char) (brute.charAt(i)+1);
            newbrute += "a".repeat(brute.length()-1-i);
            brute = newbrute;
        }
    }
}
