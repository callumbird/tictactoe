import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        //set of print statements to explain how to play the game
        introduction();

        //starts either a human vs human game or a computer vs human game
        gameSet();
    }

    public static void introduction() {
        System.out.println("Welcome to TicTacToe");
        System.out.println("Each turn will be started with a print out of the current board state, which will look like this: ");
        int[] exampleGameMoveArray = {0,2,4,8};
        ArrayList<Integer> exampleGameMoveList = makeArrayList(exampleGameMoveArray);
        printBoard(exampleGameMoveList);
        System.out.println("After this, each player will select a move to make using number keys 0-8, as shown below: ");
        printBoardExample();
    }

    public static void gameSet() {
        int[] choices = {1,2};
        ArrayList<Integer> availableMoves = makeArrayList(choices);
        
        int input = checkPlayerChoice("Enter a number to select a game: \n1. Human vs Human \n2. Computer vs Human", availableMoves);

        if (input == 1) {
            runHumanGame();
        }
        else {
            runComputerGame();
        }
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

    public static boolean checkInput (String message, ArrayList<Integer> availableMoves) {
        int input = playerInput(message);
        boolean possibleChoice = checkLegalMove(input, availableMoves);
        return possibleChoice;
    }

    public static ArrayList<Integer> makeArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0; i<array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    public static boolean checkLegalMove(int move, ArrayList<Integer> availableMoves) {
        boolean possibleChoice = false;

        if(availableMoves.contains(move)) {
            possibleChoice = true;
        }

        return possibleChoice;
    }

    public static ArrayList<Integer> updateGameList(int playerMove, ArrayList<Integer> gameList) {
        gameList.add(playerMove);
        return gameList;
    }

    public static void runHumanGame() {
        
        int[] possibleMoves = {0,1,2,3,4,5,6,7,8};
        ArrayList<Integer> possibleMovesList = makeArrayList(possibleMoves);
        ArrayList<Integer> gameList = new ArrayList<>();
        
        for (int turns = 0; turns<9; turns++){
            if (turns % 2 == 0) {
                System.out.println("Player 1");
            }
            else {
                System.out.println("Player 2");
            }
        
            int playerMove = checkPlayerChoice("Enter your move", possibleMovesList);
            
            gameList.add(playerMove);
            possibleMovesList = updatePossibleMovesList(playerMove, possibleMovesList);

            printBoard(gameList);
            if(turns > 3){
                checkWin(gameList);
            }
        }
        System.out.println("Draw: no one wins this game.");
    }

    public static void runComputerGame() {
        
        int[] choices = {1,2};
        ArrayList<Integer> availableMoves = makeArrayList(choices);
        int opponentChoice = checkPlayerChoice("Pick an opponent to play against: \n1. Gregor\n2. Menerva", availableMoves);

        if (opponentChoice == 1) {
            runGregor();
        }
        else {
            runMenerva();
        }
    }

    public static void runGregor() {
        int[] possibleMoves = {0,1,2,3,4,5,6,7,8};
        ArrayList<Integer> possibleMovesList = makeArrayList(possibleMoves);
        ArrayList<Integer> gameList = new ArrayList<>();
        String player1 = "Player 1";
        String player2 = "Player 2";
        boolean fullBoard = false;
        int move = 0;

        int coinFlip = randomNumber(2);
        if (coinFlip == 1) {
            System.out.println("Gregor will go first");
            player1 = "Gregor";
        }
        else {
            System.out.println("Gregor will go second");
            player2 = "Gregor";
        }

        while (!fullBoard) {
            if (player1 == "Gregor") {
                move = gregorMove(possibleMovesList);
            }
            else {
                move = checkPlayerChoice("Enter your move", possibleMovesList);
            }  
            gameList.add(move);
            possibleMovesList = updatePossibleMovesList(move, possibleMovesList);
            printBoard(gameList);
            checkWin(gameList);

            fullBoard = checkFullBoard(gameList);

            if (player2 == "Gregor") {
                move = gregorMove(possibleMovesList);
            }
            else {
                move = checkPlayerChoice("Enter your move", possibleMovesList);
            }
            gameList.add(move);
            possibleMovesList = updatePossibleMovesList(move, possibleMovesList);
            printBoard(gameList);
            checkWin(gameList);

            fullBoard = checkFullBoard(gameList);
        }
            
        System.out.println("Draw: no one wins this game.");
        System.exit(0);
    }
    
    public static int gregorMove(ArrayList<Integer> possibleMovesList) {
        int number = randomNumber(possibleMovesList.size());
        int move = possibleMovesList.get(number);
        return move;
    }

    public static boolean checkFullBoard(ArrayList<Integer> gameList) {
        boolean fullBoard = false;
        if (gameList.size() == 9) {
            fullBoard = true;
        }
        return fullBoard;
    }

    public static void runMenerva() {
        System.out.println("Menerva is still being developed. Thank you for your patience");
        System.exit(0);
    }

    public static ArrayList<Integer> updatePossibleMovesList(int playerMove, ArrayList<Integer> possibleMovesList) {
        int indexPosition = possibleMovesList.indexOf(playerMove);
        possibleMovesList.remove(indexPosition);

        return possibleMovesList;
    }

    public static int playerInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextInt();
    }

    public static void printBoard(ArrayList<Integer> gameMoveList) {
        String[] gameBoard = initialiseGameBoard();

        for(int i=0; i<gameMoveList.size(); i++) {
            if(i % 2 == 0) {
                gameBoard[gameMoveList.get(i)] = "X";
            }
            else {
                gameBoard[gameMoveList.get(i)] = "0";
            }
        }
        System.out.println("             ");
		System.out.println("| " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " |");
		System.out.println("|-----------|");
		System.out.println("| " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " |");
		System.out.println("|-----------|");
        System.out.println("| " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " |");
        System.out.println("             ");
    }

    public static String[] initialiseGameBoard() {
        String[] gameBoard = new String[9];
        for (int i=0; i<9; i++) {
            gameBoard[i] = " ";
        }

        return gameBoard;
    }

    public static void printBoardExample() {

        System.out.println("             ");
		System.out.println("| 0 | 1 | 2 |");
		System.out.println("|-----------|");
		System.out.println("| 3 | 4 | 5 |");
		System.out.println("|-----------|");
        System.out.println("| 6 | 7 | 8 |");
        System.out.println("             ");

    }

    public static void checkWin(ArrayList<Integer> gameList){
        ArrayList<Integer> player1MoveList = getPlayer1MoveList(gameList);
        ArrayList<Integer> player2MoveList = getPlayer2MoveList(gameList);

        boolean player1Win = checkAllCases(player1MoveList);
        boolean player2Win = checkAllCases(player2MoveList);
        
        if(player1Win) {
            System.out.println("Player 1 wins!");
            System.exit(0);
        }
        else if(player2Win) {
            System.out.println("Player 2 wins!");
            System.exit(0);
        }
    }

    public static boolean checkAllCases(ArrayList<Integer> playerMoveList){
        boolean win = false;
        for (int i=0; i<8; i++){
            switch(i) {
                case 0:
                        if(playerMoveList.contains(0) && playerMoveList.contains(1) && playerMoveList.contains(2)){
                            win = true;
                        }
                        break;
                case 1:
                        if(playerMoveList.contains(0) && playerMoveList.contains(4) && playerMoveList.contains(8)){
                            win = true;
                        }
                        break;
                case 2:
                        if(playerMoveList.contains(0) && playerMoveList.contains(3) && playerMoveList.contains(6)){
                            win = true;
                        }
                        break;
                case 3:
                        if(playerMoveList.contains(3) && playerMoveList.contains(4) && playerMoveList.contains(5)){
                            win = true;
                        }
                        break;
                case 4:
                        if(playerMoveList.contains(6) && playerMoveList.contains(7) && playerMoveList.contains(8)){
                            win = true;
                        }
                        break;
                case 5:
                        if(playerMoveList.contains(1) && playerMoveList.contains(4) && playerMoveList.contains(7)){
                            win = true;
                        }
                        break;
                case 6:
                        if(playerMoveList.contains(2) && playerMoveList.contains(5) && playerMoveList.contains(8)){
                            win = true;
                        }
                        break;
                case 7:
                        if(playerMoveList.contains(2) && playerMoveList.contains(4) && playerMoveList.contains(6)){
                            win = true;
                        }
                        break;
            }
        }
        return win;
    }

    public static ArrayList<Integer> getPlayer1MoveList(ArrayList<Integer> gameList) {
        ArrayList<Integer> player1MoveList = new ArrayList<>();
        for (int i=0; i<gameList.size(); i++){
            if (i % 2 == 0) {
                player1MoveList.add(gameList.get(i));
            }
        }
        return player1MoveList;
    }

    public static ArrayList<Integer> getPlayer2MoveList(ArrayList<Integer> gameList) {
        ArrayList<Integer> player2MoveList = new ArrayList<>();
        for (int i=0; i<gameList.size(); i++){
            if (i % 2 != 0) {
                player2MoveList.add(gameList.get(i));
            }
        }
        return player2MoveList;
    }

    public static int randomNumber(int number) {
        return (int)(Math.random()*number);
    }
}