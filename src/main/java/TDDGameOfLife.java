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
