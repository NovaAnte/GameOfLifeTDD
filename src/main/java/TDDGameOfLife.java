import java.lang.reflect.Array;
import java.util.ArrayList;

public class TDDGameOfLife {



    private boolean hasDisplayed = false;

    private String board[][];
    private int boardLength;
    private int boardHeight;

    public void initializeBoard(int y, int x){
        this.boardLength = x;
        this.boardHeight = y;
        this.board = new String [y][x];
    }

    public void displayBoard(){
        for (String[] x : this.board)
        {
            for (String y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        this.hasDisplayed = true;
    }

    public void populateBoard(){
        for (String[] x : this.board)
        {
            for(int i = 0; i < x.length; i++){
                x[i] = ".";
            }

        }
    }

    public void initializeStartingPoint(int y, int x){
        if (y < 0){
            y = 0;
        } else if (this.boardHeight <= y){
            y = this.boardHeight - 1;
        }

        if (x < 0){
            x = 0;
        } else if (this.boardLength <= x){
            x = this.boardLength - 1;
        }
        this.board[y][x] = "*";
    }

    public int decideIfCellLivesOrDies(int x, int y){
     //pos1 = new CellPosition(x-1, y-1);
     //pos2 = new CellPosition(x, y-1);
     //pos3 = new CellPosition(x+1, y-1);
     //pos4 = new CellPosition(x-1, y);
     //pos5 = new CellPosition(x+1, y);
     //pos6 = new CellPosition(x-1, y+1);
     //pos7 = new CellPosition(x, y+1);
     //pos8 = new CellPosition(x+1, y+1);

        return 0;
    }

    public String[][] getBoard() {
        return this.board;
    }

    public boolean isHasDisplayed() {
        return hasDisplayed;
    }

    public void setHasDisplayed(boolean hasDisplayed) {
        this.hasDisplayed = hasDisplayed;
    }



}
