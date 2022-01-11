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

}
