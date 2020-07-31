import java.util.ArrayList;

public class TicTacToe {
    public static void main(String[] args) {

        //set of print statements to explain how to play the game
        introduction();

        //starts either a human vs human game or a computer vs human game
        gameSet();
    }

    private static void introduction() {
        System.out.println("Welcome to TicTacToe");
        System.out.println("Each turn will be started with a print out of the current board state, which will look like this: ");
        int[] exampleGameMoveArray = {0,2,4,8};
        ArrayList<Integer> exampleGameMoveList = ComputerLogic.makeArrayList(exampleGameMoveArray);
        GameBoard.printBoard(exampleGameMoveList);
        System.out.println("After this, each player will select a move to make using number keys 0-8, as shown below: ");
        GameBoard.printBoardExample();
    }

    private static void gameSet() {
        int[] choices = {1,2};
        ArrayList<Integer> availableMoves = ComputerLogic.makeArrayList(choices);
        
        int input = UserInput.checkPlayerChoice("Enter a number to select a game: \n1. Human vs Human \n2. Computer vs Human", availableMoves);

        if (input == 1) {
            runHumanGame();
        }
        else {
            runComputerGame();
        }
    }

    private static void runHumanGame() {
        
        int[] possibleMoves = {0,1,2,3,4,5,6,7,8};
        ArrayList<Integer> possibleMovesList = ComputerLogic.makeArrayList(possibleMoves);
        ArrayList<Integer> gameList = new ArrayList<>();
        
        //turn for loop for entire game
        for (int turns = 0; turns<9; turns++){
            if (turns % 2 == 0) {
                System.out.println("Player 1");
            }
            else {
                System.out.println("Player 2");
            }
        
            int playerMove = UserInput.checkPlayerChoice("Enter your move", possibleMovesList);
            
            GameBoard.updateGameList(playerMove, gameList);
            possibleMovesList = GameBoard.updatePossibleMovesList(playerMove, possibleMovesList);

            GameBoard.printBoard(gameList);
            if(turns > 3){
                GameBoard.checkWin(gameList);
            }
        }
        System.out.println("Draw: no one wins this game.");
    }

    private static void runComputerGame() {
        
        int[] choices = {1,2};
        ArrayList<Integer> availableMoves = ComputerLogic.makeArrayList(choices);
        int opponentChoice = UserInput.checkPlayerChoice("Pick an opponent to play against: \n1. Gregor\n2. Menerva", availableMoves);

        if (opponentChoice == 1) {
            ComputerLogic.runGregor();
        }
        else {
            ComputerLogic.runMenerva();
        }
    } 
}