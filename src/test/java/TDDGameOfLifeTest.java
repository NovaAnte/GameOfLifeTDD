import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TDDGameOfLifeTest {

    private final TDDGameOfLife tddgameoflife;

    public TDDGameOfLifeTest() {
        this.tddgameoflife = new TDDGameOfLife();
    }

    @BeforeEach
    void setupBoardBeforeEachTest() {
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
    }

    @Test
    void initializeGridWithXisEightandYisFour() {
        // Arrange
        int x = 8;
        int y = 4;
        // Act
        this.tddgameoflife.initializeBoard(y, x);
        String board[][] = this.tddgameoflife.getBoard();
        // Assert
        Assertions.assertEquals(4, board.length);
        Assertions.assertEquals(8, board[0].length);
    }

    @Test
    void displayBoard() {
        // Arrange
        this.tddgameoflife.initializeBoard(4, 8);
        this.tddgameoflife.setHasDisplayed(false);
        // Act
        this.tddgameoflife.displayBoard();
        // Assert
        Assertions.assertEquals(true, this.tddgameoflife.isHasDisplayed());
    }

    @Test
    void populateBoard() {
        // Arrange
        this.tddgameoflife.initializeBoard(4, 8);
        // Act
        this.tddgameoflife.populateBoard();
        // Assert
        for (String[] x : this.tddgameoflife.getBoard()) {
            for (String y : x) {
                Assertions.assertEquals(".", y);
            }
        }
    }

    @Test
    void initializeStartingPoint() {
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        int x = 4;
        int y = 4;
        // Act
        this.tddgameoflife.spawnAliveCell(y, x);
        // Assert
        Assertions.assertEquals("*", this.tddgameoflife.getBoard()[y][x]); // Skapa en funktion som hämtar ut * och som kan validera positionen
    }

    @Test
    void decideIfCellLivesOrDies_WhenNoNeighbors_ReturnOne() {
        // Arrange
        this.tddgameoflife.spawnAliveCell(3, 3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3, 3);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenLessThanTwoNeighbors_ReturnOne() {
        // Arrange
        this.tddgameoflife.spawnAliveCell(3, 3);
        this.tddgameoflife.spawnAliveCell(3, 4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3, 3);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenMoreThanThreeNeighbors_ReturnThree() {
        // Arrange
        this.tddgameoflife.spawnAliveCell(3, 3);
        this.tddgameoflife.spawnAliveCell(3, 2);
        this.tddgameoflife.spawnAliveCell(3, 4);
        this.tddgameoflife.spawnAliveCell(2, 3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3, 3);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellHasThreeLiveNeighbors_ReturnFour() {
        // Arrange
        this.tddgameoflife.spawnAliveCell(2, 3);
        this.tddgameoflife.spawnAliveCell(3, 2);
        this.tddgameoflife.spawnAliveCell(3, 4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3, 3);
        // Assert
        Assertions.assertEquals(4, result);
    }


    @ParameterizedTest
    @CsvSource({"0,0, 1,0, 0,1, 1,1, 3", "0,7, 1,7, 1,6, 0,6, 3", "4,0, 3,0, 3,1, 4,1, 3", "4,7, 3,7, 4,6, 3,6, 3"})
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithThreeNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,0, 1,0, 0,1, 3", "0,7, 1,7, 1,6, 3", "4,0, 3,0, 3,1, 3", "4,7, 3,7, 4,6, 3"})
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithTwoNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @ParameterizedTest
    @CsvSource({"0,0, 1,0, 1", "0,7, 0,6, 1", "4,0, 3,0, 1", "4,7, 3,7, 1"})
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithOneNeighbor_ReturnOne(int y1, int x1, int y2, int x2, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,1, 1,1, 1,0, 0,0, 4", "1,7, 1,6, 0,6, 0,7, 4", "3,0, 3,1, 4,1, 4,0, 4", "3,7, 4,6, 3,6, 4,7, 4"})
    void decideIfCellLivesOrDies_WhenDeadCellIsInCornerWithThreeNeighbors_ReturnFour(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x4, y4);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 1,3, 1,4, 2", "2,7, 1,7, 3,7, 3,6, 2,6, 1,6, 2", "4,4, 4,5, 3,5, 3,4, 3,3, 4,3, 2", "2,0, 1,0, 1,1, 2,1, 3,1, 3,0, 2"})
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithFiveNeighbors_ReturnTwo(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int y5, int x5, int y6, int x6, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        this.tddgameoflife.spawnAliveCell(y5, x5);
        this.tddgameoflife.spawnAliveCell(y6, x6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 1,3, 2", "2,7, 1,7, 3,7, 3,6, 2,6, 2", "4,4, 4,3, 3,3, 3,4, 3,5, 2", "2,0, 1,0, 1,1, 2,1, 3,1, 2"})
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithFourNeighbors_ReturnTwo(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int y5, int x5, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        this.tddgameoflife.spawnAliveCell(y5, x5);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 3", "2,7, 3,7, 1,7, 2,6, 3", "4,3, 4,4, 4,2, 3,3, 3", "2,0, 1,0, 3,0, 2,1, 3"})
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithThreeNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 3", "2,7, 1,7, 3,7, 3", "4,4, 4,3, 4,5, 3", "2,0, 3,0, 1,0, 3"})
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithTwoNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 1", "2,7, 1,7, 1", "4,3, 4,4, 1", "2,0, 2,1, 1"})
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithOneNeighbor_ReturnOne(int y1, int x1, int y2, int x2, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 4", "2,7, 3,7, 1,7, 2,6, 4", "4,3, 4,4, 4,2, 3,3, 4", "2,0, 1,0, 3,0, 2,1, 4"})
    void decideIfCellLivesOrDies_WhenDeadCellIsEdgeCaseWithThreeNeighbors_ReturnFour(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0;3;., 0;2;.", "2,7, 1,7, 2;7;., 1;7;.", "4,3, 4,4, 4;3;., 4;4;.", "2,0, 2,1, 2;0;., 2;1;."})
    void killOrSpawnCell_WhenReceiveConditionOne_KillCell(int y1, int x1, int y2, int x2, String expectedValue, String expectedValue2) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        // Act
        this.tddgameoflife.killOrSpawnCell(y1, x1);
        this.tddgameoflife.killOrSpawnCell(y2, x2);
        // Assert
        assertEquals(expectedValue, this.tddgameoflife.getLiveOrDieList().get(0));
        assertEquals(expectedValue2, this.tddgameoflife.getLiveOrDieList().get(1));
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 1,3, 0;3;.", "2,7, 1,7, 3,7, 3,6, 2,6, 2;7;.", "4,4, 4,3, 3,3, 3,4, 3,5, 4;4;.", "2,0, 1,0, 1,1, 2,1, 3,1, 2;0;."})
    void killOrSpawnCell_WhenReceiveConditionTwo_KillCell(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int y5, int x5, String expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        this.tddgameoflife.spawnAliveCell(y5, x5);
        // Act
        this.tddgameoflife.killOrSpawnCell(y1, x1);
        // Assert
        assertEquals(expectedValue, this.tddgameoflife.getLiveOrDieList().get(0));
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 0;3;*", "2,7, 1,7, 3,7, 2;7;*", "4,4, 4,3, 4,5, 4;4;*", "2,0, 3,0, 1,0, 2;0;*"})
    void killOrSpawnCell_WhenReceiveConditionThree_KeepCellALive(int y1, int x1, int y2, int x2, int y3, int x3, String expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        // Act
        this.tddgameoflife.killOrSpawnCell(y1, x1);
        // Arrange
        assertEquals(expectedValue, this.tddgameoflife.getLiveOrDieList().get(0));
    }

    @ParameterizedTest
    @CsvSource({"0,3, 0,2, 0,4, 1,2, 0;3;*", "2,7, 3,7, 1,7, 2,6, 2;7;*", "4,3, 4,4, 4,2, 3,3, 4;3;*", "2,0, 1,0, 3,0, 2,1, 2;0;*"})
    void killOrSpawnCell_WhenReceiveConditionFour_DeadCellLives(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, String expectedValue) {
        // Arrange
        this.tddgameoflife.spawnAliveCell(y1, x1);
        this.tddgameoflife.spawnAliveCell(y2, x2);
        this.tddgameoflife.spawnAliveCell(y3, x3);
        this.tddgameoflife.spawnAliveCell(y4, x4);
        // Act
        this.tddgameoflife.killOrSpawnCell(y1, x1);
        // Assert
        assertEquals(expectedValue, this.tddgameoflife.getLiveOrDieList().get(0));
    }

    @Test
    void modifyCellDeadOrAlive_ReturnExpectedBoard() {
        // Arrange
        this.tddgameoflife.spawnAliveCell(2, 2);
        this.tddgameoflife.spawnAliveCell(2, 3);
        this.tddgameoflife.spawnAliveCell(3, 4);
        this.tddgameoflife.spawnAliveCell(0, 0);
        this.tddgameoflife.spawnAliveCell(3, 3);
        this.tddgameoflife.spawnAliveCell(4, 7);
        // Act
        this.tddgameoflife.modifyCellDeadOrAlive();
        this.tddgameoflife.displayBoard();
        // Assert
        assertEquals(".", this.tddgameoflife.getBoard()[0][0]);
        assertEquals(".", this.tddgameoflife.getBoard()[4][7]);
        assertEquals("*", this.tddgameoflife.getBoard()[2][2]);
        assertEquals("*", this.tddgameoflife.getBoard()[2][3]);
        assertEquals("*", this.tddgameoflife.getBoard()[3][3]);
        assertEquals("*", this.tddgameoflife.getBoard()[3][4]);
    }
}
