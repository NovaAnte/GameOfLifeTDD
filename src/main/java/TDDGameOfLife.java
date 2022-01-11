public class TDDGameOfLife {



    private boolean hasDisplayed = false;

    private String board[][];

    public void initializeBoard(int y, int x){
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
