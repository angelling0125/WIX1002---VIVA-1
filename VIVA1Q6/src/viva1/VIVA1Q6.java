package viva1;
import java.util.Scanner;
import java.util.Random;

public class VIVA1Q6 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        
        int[] cursed = new int[2];
        cursed[0] = random.nextInt(10) + 1;
        cursed[1] = random.nextInt(10) + 1;
        
        while (cursed[1] == cursed[0]) {
            cursed[1] = random.nextInt(10) + 1;
        }
        
        //System.out.println("cursed: " + cursed[0] + " " + cursed[1]);
            
        int[] egg = new int[3];
        egg[0] = random.nextInt(10) + 1;
        egg[1] = random.nextInt(10) + 1;
        egg[2] = random.nextInt(10) + 1;
        
        while (egg[1] == egg[0]) {
            egg[1] = random.nextInt(10) + 1;
        }
        
        while (egg[2] == egg[0] || egg[2] == egg[1]) {
            egg[2] = random.nextInt(10) + 1;
        }
        
        //System.out.println("eggs: " + egg[0] + " " + egg[1] + " " + egg[2]);
        
        System.out.println("Welcome to the Dragon Egg Quest!");
        System.out.println("There are 10 chests, 3 dragon eggs, and 2 cursed chests.");
        System.out.println("You have 10 attempts to find all dragon eggs.");
        
        int guess = 0;
        int attempt = 10;
        int eggFound = 0;
        
        boolean[] guessed = new boolean[10];
        boolean[] eggRemain = new boolean[3];
        for (int i = 0; i < 3; i++) {
            eggRemain[i] = true;
        }
       
        do {
            System.out.print("\nGuess a chest (1-10): ");
            guess = input.nextInt();
            
            while (guess < 1 || guess > 10) {
                System.out.println("Invalid! Please re-enter.");
                System.out.print("\nGuess a chest (1-10): ");
                guess = input.nextInt();
            }
            
            while (guessed[guess - 1]) {
                System.out.println("Repeated chess! Guess again. ");
                System.out.print("\nGuess a chest (1-10): ");
                guess = input.nextInt();
            }
            guessed[guess - 1] = true;
            
            boolean checkCursed = false;
            boolean checkEgg = false;
            for (int c : cursed) {
                if (guess == c) {
                    checkCursed = true;
                }
            }
            
            for (int e : egg) {
                if (guess == e) {
                    checkEgg = true;
                }
            }
                
            if (checkCursed && checkEgg) {
                System.out.println("This chest is cursed! Beware!");
                System.out.println("You found a dragon egg!");
                eggFound++;
                for (int i = 0; i < 3; i++) {
                    if (guess == egg[i]) {
                        eggRemain[i] = false;
                    }
                }
            }
            else if (checkEgg) {
                System.out.println("You found a dragon egg!");
                eggFound++;
                for (int i = 0; i < 3; i++) {
                    if (guess == egg[i]) {
                        eggRemain[i] = false;
                    }
                }
            }
            else { 
                if (checkCursed) {
                    System.out.println("This chest is cursed! Beware!");
                }
                
                Integer nearestEgg = null;
                for (int i = 0; i < 3; i++) {
                    if (!eggRemain[i]) {
                        continue;
                    }
                    int e = egg[i];
                    if (nearestEgg == null || Math.abs(guess - e) < Math.abs(guess - nearestEgg)) {
                        nearestEgg = e;
                    }
                }

                if (Math.abs(guess - nearestEgg) <= 3) {
                    System.out.println("Warm! You're very close to a dragon egg!");
                } else {
                    System.out.println("Cold! You're far from any dragon egg!");
                }
                
                if (guess < nearestEgg) {
                    System.out.println("Hint: Try a higher chest number");
                } else {
                    System.out.println("Hint: Try a lower chest number");
                }
            }
            
            if (!checkEgg && eggFound != 3) {
                System.out.println("No egg here, keep searching!");
                attempt -= checkCursed? 2 : 1;
                System.out.println("Attempts left: " + attempt);
            } else if (checkEgg && eggFound != 3) {
                attempt -= checkCursed? 2 : 1;
                System.out.println("Attempts left: " + attempt);
            }
                
        } while (attempt > 0 && eggFound < 3);

        if (eggFound == 3) {
            System.out.println("\nCongratulations! All dragon eggs are safe!");
        } else if (attempt == 0 && eggFound != 3) {
            System.out.println("\nGame Over! Some dragon eggs remain hidden!");
        }
        
    }     
}
    
