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
        String spelplan [] [] = tddgameoflife.initializeGrid(y, x);
        // Assert
        Assertions.assertEquals(4, spelplan.length);
        Assertions.assertEquals(8, spelplan[0].length);
    }

    @Test
    void displayBoard(){
        // Arrange
        this.tddgameoflife.setHasDisplayed(false);
        // Act
        this.tddgameoflife.displayBoard();
        // Assert
        Assertions.assertEquals(true, this.tddgameoflife.getHasDisplayed());
    }

    // Kontrollera att griden är 8 enheter bred och 4 enheter lång.

}
