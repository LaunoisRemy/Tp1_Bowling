import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stev.bowling.BowlingException;
import stev.bowling.Game;
import stev.bowling.NormalFrame;

/**
 * Project: Tp1_Bowling
 * Package: PACKAGE_NAME
 */
public class BowlingTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test
     */
    @Test
    public void setPinDown_FirstParameter_1Before2() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 3).setPinsDown(2, 6));
    }
    @Test(expected = BowlingException.class)
    public void setPinDown_FirstParameter_2Before1_Exception() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(2, 3).setPinsDown(1, 6));
    }


    @Test
    public void getCumulativeScore_OpenHit_get7(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 5).setPinsDown(2 ,2));
        int score = this.game.getCumulativeScore(1);
        Assert.assertEquals("The score should be ",7,score);

    }
}
