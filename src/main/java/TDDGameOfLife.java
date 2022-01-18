import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Scanner;

public class TDDGameOfLife {

    private boolean hasDisplayed;
    private ArrayList<String> liveOrDieList = new ArrayList<>();
    private String board[][];
    private int boardLength;
    private int boardHeight;
    private int generationCounter;
    Scanner sc = new Scanner(System.in);

    public void initializeBoard(int y, int x) {
        this.boardLength = x;
        this.boardHeight = y;
        this.board = new String[y][x];
    }

    public void displayBoard() {
        System.out.println("Generation " + generationCounter++);
        for (String[] xArray : this.board) {
            for (String cell : xArray) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        this.hasDisplayed = true;
    }

    public void populateBoard() {
        for (String[] xArray : this.board) {
            for (int i = 0; i < xArray.length; i++) {
                xArray[i] = ".";
            }
        }
    }

    public void spawnAliveCell(int y, int x) {
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
            addCornerNeighboursToList(list, pos1, pos2, pos3);

        } else if (x == this.boardLength - 1 && y == 0) { // Top right corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            addCornerNeighboursToList(list, pos1, pos2, pos3);

        } else if (x == 0 && y == this.boardHeight - 1) { // Bottom left corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x + 1, y);
            addCornerNeighboursToList(list, pos1, pos2, pos3);

        } else if (x == this.boardLength - 1 && y == this.boardHeight - 1) { // Bottom right corner
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y - 1);
            addCornerNeighboursToList(list, pos1, pos2, pos3);

        } else if (x < this.boardLength - 1 && y == 0) { // Top edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x + 1, y + 1);
            addEdgeNeighboursToList(list, pos1, pos2, pos3, pos4, pos5);

        } else if (x == this.boardLength - 1 && y < this.boardHeight - 1) { // Right edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x - 1, y + 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x - 1, y - 1);
            addEdgeNeighboursToList(list, pos1, pos2, pos3, pos4, pos5);

        } else if (x < this.boardLength - 1 && y == this.boardHeight - 1) { // Bottom edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x - 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x + 1, y);
            addEdgeNeighboursToList(list, pos1, pos2, pos3, pos4, pos5);

        } else if (x == 0 && y < this.boardHeight - 1) { // Left edge
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x + 1, y + 1);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x, y + 1);
            addEdgeNeighboursToList(list, pos1, pos2, pos3, pos4, pos5);

        } else {
            ImmutablePair<Integer, Integer> pos1 = new ImmutablePair<>(x - 1, y - 1);
            ImmutablePair<Integer, Integer> pos2 = new ImmutablePair<>(x, y - 1);
            ImmutablePair<Integer, Integer> pos3 = new ImmutablePair<>(x + 1, y - 1);
            ImmutablePair<Integer, Integer> pos4 = new ImmutablePair<>(x - 1, y);
            ImmutablePair<Integer, Integer> pos5 = new ImmutablePair<>(x + 1, y);
            ImmutablePair<Integer, Integer> pos6 = new ImmutablePair<>(x - 1, y + 1);
            ImmutablePair<Integer, Integer> pos7 = new ImmutablePair<>(x, y + 1);
            ImmutablePair<Integer, Integer> pos8 = new ImmutablePair<>(x + 1, y + 1);
            addStandardCaseNeighboursToList(list, pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8);
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

        return 0;
    }

    private void addStandardCaseNeighboursToList(ArrayList<ImmutablePair<Integer, Integer>> list, ImmutablePair<Integer, Integer> pos1, ImmutablePair<Integer, Integer> pos2, ImmutablePair<Integer, Integer> pos3, ImmutablePair<Integer, Integer> pos4, ImmutablePair<Integer, Integer> pos5, ImmutablePair<Integer, Integer> pos6, ImmutablePair<Integer, Integer> pos7, ImmutablePair<Integer, Integer> pos8) {
        list.add(pos1);
        list.add(pos2);
        list.add(pos3);
        list.add(pos4);
        list.add(pos5);
        list.add(pos6);
        list.add(pos7);
        list.add(pos8);
    }

    private void addEdgeNeighboursToList(ArrayList<ImmutablePair<Integer, Integer>> list, ImmutablePair<Integer, Integer> pos1, ImmutablePair<Integer, Integer> pos2, ImmutablePair<Integer, Integer> pos3, ImmutablePair<Integer, Integer> pos4, ImmutablePair<Integer, Integer> pos5) {
        list.add(pos1);
        list.add(pos2);
        list.add(pos3);
        list.add(pos4);
        list.add(pos5);
    }

    private void addCornerNeighboursToList(ArrayList<ImmutablePair<Integer, Integer>> list, ImmutablePair<Integer, Integer> pos1, ImmutablePair<Integer, Integer> pos2, ImmutablePair<Integer, Integer> pos3) {
        list.add(pos1);
        list.add(pos2);
        list.add(pos3);
    }

    public void killOrSpawnCell(int y, int x) {
        int condition = decideIfCellLivesOrDies(x, y);

        switch (condition) {
            case 1:
            case 2:
                liveOrDieList.add(y + ";" + x + ";" + ".");
                break;
            case 3:
            case 4:
                liveOrDieList.add(y + ";" + x + ";" + "*");
                break;
        }

    }

    public void modifyCellDeadOrAlive() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                killOrSpawnCell(i, j);
            }
        }

        for (String coordinate : liveOrDieList) {
            String yCoordinate = String.valueOf(coordinate.split(";")[0]);
            String xCoordinate = String.valueOf(coordinate.split(";")[1]);
            String listSplit = String.valueOf(coordinate.split(";")[2]);
            this.board[Integer.parseInt(yCoordinate)][Integer.parseInt(xCoordinate)] = listSplit;
        }
    }


    public void setupGame() {
        int height;
        int length;
        System.out.println("Welcome to the Game of Life");
        System.out.println("Select your board size (min. 2)");
        System.out.println("Board height: ");
        while (true) {
            height = Integer.valueOf(sc.nextLine());
            if (height < 2) {
                System.out.println("Invalid input, try again.");
            } else {
                break;
            }
        }
        System.out.println("Board length: ");
        while (true) {
            length = Integer.valueOf(sc.nextLine());
            if (length < 2) {
                System.out.println("Invalid input, try again.");
            } else {
                break;
            }
        }

        initializeBoard(height, length);
        populateBoard();

        System.out.println("Select live cells (maximum coordinates " + height + "," + length + ")");
        boolean validInput = false;
        boolean yLoop = true;
        boolean xLoop = true;
        int yCoordinate = 0;
        int xCoordinate = 0;


        while (!validInput) {
            while (yLoop) {
                System.out.println("Y coordinate: ");
                yCoordinate = Integer.valueOf(sc.nextLine());
                if (yCoordinate > height) {
                    System.out.println("Invalid input, try again.");
                } else {
                    yLoop = false;
                }
            }

            while (xLoop) {
                System.out.println("X coordinate: ");
                xCoordinate = Integer.valueOf(sc.nextLine());
                if (xCoordinate > length) {
                    System.out.println("Invalid input, try again.");
                } else {
                    xLoop = false;
                }
            }

            spawnAliveCell(yCoordinate - 1, xCoordinate - 1);

            System.out.println("Add another coordinate? (Y/N)");
            String choice = sc.nextLine().toLowerCase();

            if (choice.equals("y")) {
                validInput = false;
                yLoop = true;
                xLoop = true;
            } else if (choice.equals("n")) {
                validInput = true;
            } else {
                System.out.println("Invalid input, try again.");
            }

        }
        displayBoard();
    }

    public void runGame() {
        modifyCellDeadOrAlive();
        displayBoard();
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


    public ArrayList<String> getLiveOrDieList() {
        return liveOrDieList;
    }

}
