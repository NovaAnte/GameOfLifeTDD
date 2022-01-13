import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;

public class TDDGameOfLife {

    private boolean hasDisplayed = false;

    private ArrayList<String> liveOrDieList = new ArrayList<>();
    private String board[][];
    private int boardLength;
    private int boardHeight;

    public void initializeBoard(int y, int x) {
        this.boardLength = x;
        this.boardHeight = y;
        this.board = new String[y][x];
    }

    public void displayBoard() {
        for (String[] x : this.board) {
            for (String y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        this.hasDisplayed = true;
    }

    public void populateBoard() {
        for (String[] x : this.board) {
            for (int i = 0; i < x.length; i++) {
                x[i] = ".";
            }

        }
    }

    public void initializeStartingPoint(int y, int x) {
        if (y < 0) {
            y = 0;
        } else if (this.boardHeight <= y) {
            y = this.boardHeight - 1;
        }

        if (x < 0) {
            x = 0;
        } else if (this.boardLength <= x) {
            x = this.boardLength - 1;
        }
        this.board[y][x] = "*";
    }

    public int decideIfCellLivesOrDies(int x, int y) {
        ArrayList<ImmutablePair<Integer, Integer>> list = new ArrayList<>();
        if (x == 0 && y == 0) { // Top left corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y + 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x, y + 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);

        } else if (x == this.boardLength - 1 && y == 0) { // Top right corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);

        } else if (x == 0 && y == this.boardHeight - 1) { // Bottom left corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x + 1, y);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);

        } else if (x == this.boardLength - 1 && y == this.boardHeight - 1) { // Bottom right corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y - 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);

        } else if (x < this.boardLength - 1 && y == 0) { // Top edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x + 1, y + 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
            list.add(pos4);
            list.add(pos5);

        } else if (x == this.boardLength - 1 && y < this.boardHeight - 1) { // Right edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x - 1, y - 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
            list.add(pos4);
            list.add(pos5);

        } else if (x < this.boardLength - 1 && y == this.boardHeight - 1) { // Bottom edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x - 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x + 1, y);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
            list.add(pos4);
            list.add(pos5);

        } else if (x == 0 && y < this.boardHeight - 1) { // Left edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x + 1, y + 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x, y + 1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
            list.add(pos4);
            list.add(pos5);
        } else {
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x-1, y-1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y-1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x+1, y-1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x-1, y);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x+1, y);
            ImmutablePair<Integer, Integer> pos6 = new ImmutablePair<>(x-1, y+1);
            ImmutablePair<Integer, Integer> pos7 = new ImmutablePair<>(x, y+1);
            ImmutablePair<Integer, Integer> pos8 = new ImmutablePair<>(x+1, y+1);
            list.add(pos1);
            list.add(pos2);
            list.add(pos3);
            list.add(pos4);
            list.add(pos5);
            list.add(pos6);
            list.add(pos7);
            list.add(pos8);
        }



        int counter = 0;

        for (int i = 0; i < list.size(); i++) {
            String type = board[list.get(i).getRight()][list.get(i).getLeft()];
            if (type.equals("*")) {
                counter++;
            }
        }

        String coordinateType = this.board[y][x];


        if (coordinateType.equals("*")) {
            if (counter < 2) {
                return 1;
            } else if (counter > 3) {
                return 2;
            } else {
                return 3;
            }
        }

        if (coordinateType.equals(".")) {
            if (counter == 3) {
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

    public void killOrSpawnCell(int y, int x){
        int condition = decideIfCellLivesOrDies(x, y);

        switch (condition){
            case 1:
            case 2:
                liveOrDieList.add(y + ";" + x + ";" + ".");
                break;
            case 3:
                break;
            case 4:
                liveOrDieList.add(y + ";" + x + ";" + "*");
                break;
        }

    }




    // spara alla koordinater i en lista
    // gå igenom listan

    public String[][] getBoard() {
        return this.board;
    }

    public boolean isHasDisplayed() {
        return hasDisplayed;
    }

    public void setHasDisplayed(boolean hasDisplayed) {
        this.hasDisplayed = hasDisplayed;
    }


    public ArrayList<String> getLiveOrDieList() {
        return liveOrDieList;
    }

    public void setLiveOrDieList(ArrayList<String> liveOrDieList) {
        this.liveOrDieList = liveOrDieList;
    }

}
