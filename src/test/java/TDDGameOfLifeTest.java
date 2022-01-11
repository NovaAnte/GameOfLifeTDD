import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
