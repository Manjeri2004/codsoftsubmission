import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("🎮 Welcome to Guess the Number Game!");
        System.out.println("You need to guess a number between 1 and 100.");

        while (playAgain) {
            roundsPlayed++;
            int numberToGuess = rand.nextInt(100) + 1; // random number 1–100
            int attemptsLeft = 7; // attempt limit
            boolean guessedCorrectly = false;

            System.out.println("\n🔹 Round " + roundsPlayed + " starts! You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("✅ Correct! You guessed the number.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("⬆️ Too high! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("⬇️ Too low! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.println("📊 Score: " + roundsWon + "/" + roundsPlayed + " rounds won.");

            // Ask to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String choice = sc.next();
            playAgain = choice.equalsIgnoreCase("yes");
        }

        System.out.println("\n🏆 Final Score: " + roundsWon + " out of " + roundsPlayed + " rounds won.");
        System.out.println("Thanks for playing! 🎉");
        sc.close();
    }
}
