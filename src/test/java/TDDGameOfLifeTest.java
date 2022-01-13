import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TDDGameOfLifeTest {

    private final TDDGameOfLife tddgameoflife;

    public TDDGameOfLifeTest() {
        this.tddgameoflife = new TDDGameOfLife();
    }

    @Test
    void initializeGridWithXisEightandYisFour(){
        // Arrange
        int x = 8;
        int y = 4;
        // Act
        this.tddgameoflife.initializeBoard(y, x);
        String board [] [] = this.tddgameoflife.getBoard();
        // Assert
        Assertions.assertEquals(4, board.length);
        Assertions.assertEquals(8, board[0].length);
    }

    @Test
    void displayBoard(){
        // Arrange
        this.tddgameoflife.initializeBoard(4, 8);
        this.tddgameoflife.setHasDisplayed(false);
        // Act
        this.tddgameoflife.displayBoard();
        // Assert
        Assertions.assertEquals(true, this.tddgameoflife.isHasDisplayed());
    }

    // Kontrollera att griden är 8 enheter bred och 4 enheter lång.

    @Test
    void populateBoard(){
        // Arrange
        this.tddgameoflife.initializeBoard(4, 8);
        // Act
        this.tddgameoflife.populateBoard();
        // Assert
        for (String[] x : this.tddgameoflife.getBoard())
        {
            for (String y : x)
            {
                Assertions.assertEquals(".", y);
            }

        }


    }

    @Test
    void initializeStartingPoint(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        int x = 4;
        int y = 4;
        // Act
        this.tddgameoflife.initializeStartingPoint(y, x);
        // Assert
        Assertions.assertEquals("*", this.tddgameoflife.getBoard()[y][x]); // Skapa en funktion som hämtar ut * och som kan validera positionen
    }

    @Test
    void decideIfCellLivesOrDies_WhenNoNeighbors_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.initializeStartingPoint(3,3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,3);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenLessThanTwoNeighbors_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.initializeStartingPoint(3, 3);
        this.tddgameoflife.initializeStartingPoint(3, 4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,3);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenMoreThanThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.initializeStartingPoint(3,3);
        this.tddgameoflife.initializeStartingPoint(3,2);
        this.tddgameoflife.initializeStartingPoint(3,4);
        this.tddgameoflife.initializeStartingPoint(2,3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,3);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellHasThreeLiveNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(2,3);
        this.tddgameoflife.initializeStartingPoint(3,2);
        this.tddgameoflife.initializeStartingPoint(3,4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,3);
        // Assert
        Assertions.assertEquals(4, result);
    }


    @ParameterizedTest
    @CsvSource({
            "0,0, 1,0, 0,1, 1,1, 3",
            "0,7, 1,7, 1,6, 0,6, 3",
            "4,0, 3,0, 3,1, 4,1, 3",
            "4,7, 3,7, 4,6, 3,6, 3"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithThreeNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        this.tddgameoflife.initializeStartingPoint(y4, x4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0, 1,0, 0,1, 3",
            "0,7, 1,7, 1,6, 3",
            "4,0, 3,0, 3,1, 3",
            "4,7, 3,7, 4,6, 3"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithTwoNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0, 1,0, 1",
            "0,7, 0,6, 1",
            "4,0, 3,0, 1",
            "4,7, 3,7, 1"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsInCornerWithOneNeighbor_ReturnOne(int y1, int x1, int y2, int x2, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,1, 1,1, 1,0, 0,0, 4",
            "1,7, 1,6, 0,6, 0,7, 4",
            "3,0, 3,1, 4,1, 4,0, 4",
            "3,7, 4,6, 3,6, 4,7, 4"
    })
    void decideIfCellLivesOrDies_WhenDeadCellIsInCornerWithThreeNeighbors_ReturnFour(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x4, y4);
        // Assert
        Assertions.assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,3, 0,2, 0,4, 1,2, 1,3, 1,4, 2",
            "2,7, 1,7, 3,7, 3,6, 2,6, 1,6, 2",
            "4,4, 4,5, 3,5, 3,4, 3,3, 4,3, 2",
            "2,0, 1,0, 1,1, 2,1, 3,1, 3,0, 2"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithFiveNeighbors_ReturnTwo(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int y5, int x5, int y6, int x6, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        this.tddgameoflife.initializeStartingPoint(y4, x4);
        this.tddgameoflife.initializeStartingPoint(y5, x5);
        this.tddgameoflife.initializeStartingPoint(y6, x6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,3, 0,2, 0,4, 1,2, 1,3, 2",
            "2,7, 1,7, 3,7, 3,6, 2,6, 2",
            "4,4, 4,3, 3,3, 3,4, 3,5, 2",
            "2,0, 1,0, 1,1, 2,1, 3,1, 2"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithFourNeighbors_ReturnTwo(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int y5, int x5, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        this.tddgameoflife.initializeStartingPoint(y4, x4);
        this.tddgameoflife.initializeStartingPoint(y5, x5);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,3, 0,2, 0,4, 1,2, 3",
            "2,7, 3,7, 1,7, 2,6, 3",
            "4,3, 4,4, 4,2, 3,3, 3",
            "2,0, 1,0, 3,0, 2,1, 3"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsEdgeCaseWithThreeNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int y4, int x4, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        this.tddgameoflife.initializeStartingPoint(y4, x4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0,3, 0,2, 0,4, 3",
            "2,7, 1,7, 3,7, 3",
            "4,4, 4,3, 4,5, 3",
            "2,0, 3,0, 1,0, 3"
    })
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithTwoNeighbors_ReturnThree(int y1, int x1, int y2, int x2, int y3, int x3, int expectedValue){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(y1, x1);
        this.tddgameoflife.initializeStartingPoint(y2, x2);
        this.tddgameoflife.initializeStartingPoint(y3, x3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(x1, y1);
        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,3);
        this.tddgameoflife.initializeStartingPoint(0,2);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,0);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsRightEdgeCaseWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(2,7);
        this.tddgameoflife.initializeStartingPoint(1,7);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,2);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellIsRightEdgeCaseWithThreeNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(1,7);
        this.tddgameoflife.initializeStartingPoint(3,7);
        this.tddgameoflife.initializeStartingPoint(3,6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,2);
        // Assert
        assertEquals(4, result);
    }

}
