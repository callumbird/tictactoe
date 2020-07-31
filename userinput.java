import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInput {

    public static boolean checkInput(String message, ArrayList<Integer> availableMoves) {
        int input = playerInput(message);
        boolean possibleChoice = checkLegalMove(input, availableMoves);
        return possibleChoice;
    }

    public static boolean checkLegalMove(int move, ArrayList<Integer> availableMoves) {
        boolean possibleChoice = false;
        if(availableMoves.contains(move)) {
            possibleChoice = true;
        }

        return possibleChoice;
    }

    public static int playerInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextInt();
    }

    public static int checkPlayerChoice(String message, ArrayList<Integer> availableMoves) {
        boolean possibleChoice = false;
        String errorMessage = "Enter a valid number, please.";
        int input = 0;
    
        while (!possibleChoice) {
            try {
                input = playerInput(message);
                message = errorMessage;
                possibleChoice = checkLegalMove(input, availableMoves);
            }
            catch (InputMismatchException ex) {
                System.out.println("You entered something that wasn't a number.");
                possibleChoice = false;
            }
        }
        return input;
    }
}