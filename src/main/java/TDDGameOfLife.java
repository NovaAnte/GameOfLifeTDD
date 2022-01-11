public class TDDGameOfLife {

    private boolean hasDisplayed = false;

    private String board[][];

    public void initializeGrid(int y, int x){
        board = new String [y][x];
    }

    public void displayBoard(){

    }

    public String[][] getBoard() {
        return board;
    }

}
