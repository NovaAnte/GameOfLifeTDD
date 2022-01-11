import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TDDGameOfLifeTest {

    @Test
    void initializeGridWithXisEightandYisFour(){
        // Arrange
        int x = 8;
        int y = 4;
        // Act
        String spelplan [] [] = initializeGrid(x, y);
        // Assert
        Assertions.assertEquals(8, spelplan.length);
    }

    // Kontrollera att griden är 8 enheter bred och 4 enheter lång.

}
