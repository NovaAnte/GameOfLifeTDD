import org.apache.commons.lang3.tuple.ImmutablePair;

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
        ArrayList<ImmutablePair<Integer, Integer>> list = new ArrayList<>();
        if (x == 0 && y == 0){
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x+1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x+1, y+1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x, y+1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
        }

        if (x == this.boardLength -1 && y == 0){
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x-1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y+1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x-1, y+1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
        }

        if (x == 0 && y == this.boardHeight -1){
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y-1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x+1, y-1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x+1, y);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
        }

        //ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x-1, y-1);
        //ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y-1);
        //ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x+1, y-1);
        //ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x-1, y);
        //ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x+1, y);
        //ImmutablePair<Integer, Integer> pos6 = new ImmutablePair<>(x-1, y+1);
        //ImmutablePair<Integer, Integer> pos7 = new ImmutablePair<>(x, y+1);
        //ImmutablePair<Integer, Integer> pos8 = new ImmutablePair<>(x+1, y+1);
//
//
        //list.add(pos1);
        //list.add(pos2);
        //list.add(pos3);
        //list.add(pos4);
        //list.add(pos5);
        //list.add(pos6);
        //list.add(pos7);
        //list.add(pos8);
        int counter = 0;

        for (int i = 0; i < list.size(); i++) {
            String type = board[list.get(i).getRight()][list.get(i).getLeft()];
            if (type.equals("*")){
                counter++;
            }
        }

        String coordinateType = this.board[y][x];


        if (coordinateType.equals("*")){
            if (counter < 2){
                return 1;
            } else if (counter > 3){
                return 2;
            } else {
                return 3;
            }
        }

        if (coordinateType.equals(".")){
            if (counter == 3){
                return 4;
            }
        }



        // Vi ska hämta ut alla grannar
        // Identifiera vad punkten och grannarna är för typ
        // Beräkna hur många av de typerna som är levande celler
        // Om grannarna är färre än 2, så kommer vi returnera 1
        // Om grannarna är fler än 3, returnerna 2
        // Om grannarna är två eller tre, returnera 3
        // Om cellen är död, och grannarna är 3, returnera 4
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
