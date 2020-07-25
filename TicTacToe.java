import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        //set of print statements to explain how to play the game
        System.out.println("Welcome to TicTacToe");
        System.out.println("Each turn will be started with a print out of the current board state, which will look like this: ");
        ArrayList<Integer> exampleGameMoveList = new ArrayList<Integer>();
        exampleGameMoveList.add(0);
        exampleGameMoveList.add(2);
        exampleGameMoveList.add(4);
        exampleGameMoveList.add(8);
        printBoard(exampleGameMoveList);
        System.out.println("After this, each player will select a move to make using number keys 0-8, as shown below: ");
        printBoardExample();

        //actual game method start
        runGame();
    }

    public static void runGame() {
        //initialise game list, which is the record of every player's move
        ArrayList<Integer> gameList = new ArrayList<Integer>();
        
        for (int turns = 0; turns<9; turns++){
            if (turns % 2 == 0) {
                System.out.println("Player 1");
            }
            else {
                System.out.println("Player 2");
            }

        int playerMove = playerMove();
        boolean legalMove = checkMove(playerMove, gameList);
            while(!legalMove) {
                try {
                    System.out.println("That is not a legal move. Please pick a new move ");
                    playerMove = playerMove();
                    legalMove = checkMove(playerMove, gameList);
                }
                finally {
                gameList = updateGameList(playerMove, gameList);
                }
            }

            printBoard(gameList);
            if(turns > 4){
                checkWin(gameList);
            }
        }

        System.out.println("Draw: no one wins this game.");
    }

    public static void printBoard(ArrayList<Integer> gameMoveList) {
        String[] gameBoard = new String[9];
        int[] gameListArray = new int[gameMoveList.size()];
        gameListArray = arrayListToArray(gameMoveList);
        for(int i=0; i<9; i++) {
            gameBoard[i] = " ";
        }

        System.out.println(gameListArray[0]);

        for(int j=0; j<gameListArray.length; j++) {
            if(j % 2 == 0) {
                gameBoard[gameListArray[j]] = "X";
            }
            else {
                gameBoard[gameListArray[j]] = "0";
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

    public static void printBoardExample() {

        System.out.println("             ");
		System.out.println("| 0 | 1 | 2 |");
		System.out.println("|-----------|");
		System.out.println("| 3 | 4 | 5 |");
		System.out.println("|-----------|");
        System.out.println("| 6 | 7 | 8 |");
        System.out.println("             ");

    }

    public static int playerMove() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your move ");
        int move = input.nextInt();

        return move;
    }

    public static boolean checkMove(int move, ArrayList<Integer> gameList) {
        boolean availableMove = false;
        if (gameList.size() < 1) {
            gameList.add(9);
        }

        if (move >=0 && move <= 8){
        for (int i=0; i < gameList.size(); i++) {
            if (move != gameList.get(i)) {
                availableMove = true;
            }
            else {
                availableMove = false;
            }
        }
    }
        return availableMove;
    }

    public static ArrayList<Integer> updateGameList(int playerMove, ArrayList<Integer> gameList) {
        gameList.add(playerMove);
        return gameList;
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
        for (int i=0; i<8; i++){
            boolean win = false;

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
        ArrayList<Integer> player1MoveList = new ArrayList<Integer>();
        for (int i=0; i<gameList.size(); i++){
            if (i % 2 == 0) {
                player1MoveList.add(gameList.get(i));
            }
        }
        return player1MoveList;
    }

    public static ArrayList<Integer> getPlayer2MoveList(ArrayList<Integer> gameList) {
        ArrayList<Integer> player2MoveList = new ArrayList<Integer>();
        for (int i=0; i<gameList.size(); i++){
            if (i % 2 != 0) {
                player2MoveList.add(gameList.get(i));
            }
        }
        return player2MoveList;
    }

    public static int[] arrayListToArray(ArrayList<Integer> gameMoveList) {
        int[] gameList = new int[gameMoveList.size()];

        for (int i=0; i < gameList.length; i++) {
            gameList[i] = gameMoveList.get(i);
        }
        
        return gameList;
    }

    
}



