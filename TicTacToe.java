import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe");
        //int[] gameMoveList = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        //printBoard(gameMoveList);
        ArrayList<Integer> gameList = new ArrayList<Integer>();
        gameCreation();
        playerTurns(gameList);
    }

    public static void gameCreation() {
        System.out.println("Each turn will be started with a print out of the current board state, which will look like this: ");
        int[] exampleGameMoveList = {1,7,4};
        printBoard(exampleGameMoveList);
        System.out.println("After this, each player will select a move to make using number keys 0-8, as shown below: ");
        printBoardExample();
    }

    public static void printBoard(int[] gameMoveList) {
        String[] gameBoard = new String[9];
        for(int i=0; i<9; i++) {
            gameBoard[i] = " ";

            for(int j=0; j<gameMoveList.length; j++)
            if(j % 2 == 0) {
               gameBoard[gameMoveList[j]] = "X";
            }
            else {
                gameBoard[gameMoveList[j]] = "0";
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

        input.close();
        return move;
    }

    public static boolean checkMove(int move, ArrayList<Integer> gameList) {
        boolean usedMove = false;
        for (int i=0; i < gameList.length(); i++) {
            if (move = gameList.get(i)) {
                usedMove = true;
            }
        }

        return usedMove;
    }

    public static ArrayList<Integer> playerTurns(ArrayList<Integer> gameList) {
        
        for (int turncount=0; turncount < 9; turncount++) {
        int playerMove = playerMove();

        boolean usedMove = checkMove(playerMove, gameList);

        while (usedMove){
            try{
                playerMove = playerMove();
                usedMove = checkMove(playerMove, gameList);
                }
                finally {}
            }
            
            gameList = updateGameList(playerMove, gameList);
        }
   }

    public static ArrayList<Integer> updateGameList(int playerMove, ArrayList<Integer> gameList) {
        gameList.add(playerMove);
        return gameList;
    }

    
}



