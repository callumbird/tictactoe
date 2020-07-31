import java.util.ArrayList;

public class GameBoard {
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

    public static boolean checkFullBoard(ArrayList<Integer> gameList) {
        boolean fullBoard = false;
        if (gameList.size() == 9) {
            fullBoard = true;
        }
        return fullBoard;
    }

    public static ArrayList<Integer> updatePossibleMovesList(int playerMove, ArrayList<Integer> possibleMovesList) {
        int indexPosition = possibleMovesList.indexOf(playerMove);
        possibleMovesList.remove(indexPosition);

        return possibleMovesList;
    }

    public static ArrayList<Integer> updateGameList(int playerMove, ArrayList<Integer> gameList) {
        gameList.add(playerMove);
        return gameList;
    }
}