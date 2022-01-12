import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopLeftCornerWithThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,0);
        this.tddgameoflife.initializeStartingPoint(1,0);
        this.tddgameoflife.initializeStartingPoint(0,1);
        this.tddgameoflife.initializeStartingPoint(1,1);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(0,0);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopLeftCornerWithTwoNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,0);
        this.tddgameoflife.initializeStartingPoint(1,0);
        this.tddgameoflife.initializeStartingPoint(0,1);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(0,0);
        // Assert
        Assertions.assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopLeftCornerWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,0);
        this.tddgameoflife.initializeStartingPoint(1, 0);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(0,0);
        // Assert
        Assertions.assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellIsTopLeftCornerWithThreeNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0, 1);
        this.tddgameoflife.initializeStartingPoint(1, 1);
        this.tddgameoflife.initializeStartingPoint(1, 0);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(0,0);
        // Assert
        Assertions.assertEquals(4, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopRightCornerWithThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0, 7);
        this.tddgameoflife.initializeStartingPoint(1, 7);
        this.tddgameoflife.initializeStartingPoint(1, 6);
        this.tddgameoflife.initializeStartingPoint(0, 6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,0);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopRightCornerWithTwoNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0, 7);
        this.tddgameoflife.initializeStartingPoint(1, 7);
        this.tddgameoflife.initializeStartingPoint(1, 6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,0);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopRightCornerWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0, 7);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,0);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellIsTopRightCornerWithThreeNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(1, 7);
        this.tddgameoflife.initializeStartingPoint(1, 6);
        this.tddgameoflife.initializeStartingPoint(0, 6);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(7,0);
        // Assert
        Assertions.assertEquals(4, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomLeftCornerWithThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4, 0);
        this.tddgameoflife.initializeStartingPoint(3, 0);
        this.tddgameoflife.initializeStartingPoint(3, 1);
        this.tddgameoflife.initializeStartingPoint(4, 1);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(0,4);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomLeftCornerWithTwoNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4, 0);
        this.tddgameoflife.initializeStartingPoint(3, 0);
        this.tddgameoflife.initializeStartingPoint(3, 1);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(0,4);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomLeftCornerWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4, 0);
        this.tddgameoflife.initializeStartingPoint(3, 0);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(0,4);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellIsBottomLeftCornerWithThreeNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(3, 0);
        this.tddgameoflife.initializeStartingPoint(3, 1);
        this.tddgameoflife.initializeStartingPoint(4, 1);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(0,4);
        // Assert
        assertEquals(4, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomRightCornerWithThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4,7);
        this.tddgameoflife.initializeStartingPoint(3,7);
        this.tddgameoflife.initializeStartingPoint(4,6);
        this.tddgameoflife.initializeStartingPoint(3,6);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(7,4);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomRightCornerWithTwoNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4,7);
        this.tddgameoflife.initializeStartingPoint(3,7);
        this.tddgameoflife.initializeStartingPoint(4,6);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(7,4);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsBottomRightCornerWithOneNeighbor_ReturnOne(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(4,7);
        this.tddgameoflife.initializeStartingPoint(3,7);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(7,4);
        // Assert
        assertEquals(1, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenDeadCellIsBottomRightCornerWithThreeNeighbors_ReturnFour(){
        // Arrange
        this.tddgameoflife.initializeBoard(5,8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(3,7);
        this.tddgameoflife.initializeStartingPoint(4,6);
        this.tddgameoflife.initializeStartingPoint(3,6);
        // Act
        int result = tddgameoflife.decideIfCellLivesOrDies(7,4);
        // Assert
        assertEquals(4, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithFiveNeighbors_ReturnTwo(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,3);
        this.tddgameoflife.initializeStartingPoint(0,2);
        this.tddgameoflife.initializeStartingPoint(0,4);
        this.tddgameoflife.initializeStartingPoint(1,2);
        this.tddgameoflife.initializeStartingPoint(1,3);
        this.tddgameoflife.initializeStartingPoint(1,4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,0);
        // Assert
        assertEquals(2, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithFourNeighbors_ReturnTwo(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,3);
        this.tddgameoflife.initializeStartingPoint(0,2);
        this.tddgameoflife.initializeStartingPoint(0,4);
        this.tddgameoflife.initializeStartingPoint(1,2);
        this.tddgameoflife.initializeStartingPoint(1,3);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,0);
        // Assert
        assertEquals(2, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithThreeNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,3);
        this.tddgameoflife.initializeStartingPoint(0,2);
        this.tddgameoflife.initializeStartingPoint(0,4);
        this.tddgameoflife.initializeStartingPoint(1,2);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,0);
        // Assert
        assertEquals(3, result);
    }

    @Test
    void decideIfCellLivesOrDies_WhenAliveCellIsTopEdgeCaseWithTwoNeighbors_ReturnThree(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(0,3);
        this.tddgameoflife.initializeStartingPoint(0,2);
        this.tddgameoflife.initializeStartingPoint(0,4);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(3,0);
        // Assert
        assertEquals(3, result);
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
    void decideIfCellLivesOrDies_WhenAliveCellIsRightEdgeCaseWithFiveNeighbors_ReturnTwo(){
        // Arrange
        this.tddgameoflife.initializeBoard(5, 8);
        this.tddgameoflife.populateBoard();
        this.tddgameoflife.initializeStartingPoint(2,7);
        this.tddgameoflife.initializeStartingPoint(1,7);
        this.tddgameoflife.initializeStartingPoint(3,7);
        this.tddgameoflife.initializeStartingPoint(3,6);
        this.tddgameoflife.initializeStartingPoint(2,6);
        this.tddgameoflife.initializeStartingPoint(1,6);
        // Act
        int result = this.tddgameoflife.decideIfCellLivesOrDies(7,2);
        // Assert
        assertEquals(2, result);
    }

}
