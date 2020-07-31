import java.util.ArrayList;

public class ComputerLogic {
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
                move = UserInput.checkPlayerChoice("Enter your move", possibleMovesList);
            }  

            GameBoard.updateGameList(move, gameList);
            possibleMovesList = GameBoard.updatePossibleMovesList(move, possibleMovesList);
            GameBoard.printBoard(gameList);
            GameBoard.checkWin(gameList);

            fullBoard = GameBoard.checkFullBoard(gameList);

            if (player2 == "Gregor") {
                move = gregorMove(possibleMovesList);
            }
            else {
                move = UserInput.checkPlayerChoice("Enter your move", possibleMovesList);
            }
            gameList.add(move);
            possibleMovesList = GameBoard.updatePossibleMovesList(move, possibleMovesList);
            GameBoard.printBoard(gameList);
            GameBoard.checkWin(gameList);

            fullBoard = GameBoard.checkFullBoard(gameList);
        }
            
        System.out.println("Draw: no one wins this game.");
        System.exit(0);
    }

    private static int gregorMove(ArrayList<Integer> possibleMovesList) {
        int number = randomNumber(possibleMovesList.size());
        int move = possibleMovesList.get(number);
        return move;
    }

    public static void runMenerva() {
        System.out.println("Menerva is still being developed. Thank you for your patience");
        System.exit(0);
    }

    private static int randomNumber(int number) {
        return (int)(Math.random()*number);
    }

    public static ArrayList<Integer> makeArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0; i<array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }  
}