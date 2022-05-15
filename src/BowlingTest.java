import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stev.bowling.Game;
import stev.bowling.LastFrame;
import stev.bowling.NormalFrame;

import static org.junit.Assert.assertEquals;

/**
 * Project: Tp1_Bowling
 * Package: PACKAGE_NAME
 */
public class BowlingTest {

    private Game game;
    private Game completeGame;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        this.completeGame = new Game();

        this.completeGame.addFrame(new NormalFrame(1).setPinsDown(1, 3).setPinsDown(2, 6));
        this.completeGame.addFrame(new NormalFrame(2).setPinsDown(1, 10));
        this.completeGame.addFrame(new NormalFrame(3).setPinsDown(1, 5).setPinsDown(2, 0));
        this.completeGame.addFrame(new NormalFrame(4).setPinsDown(1, 1).setPinsDown(2, 9));
        this.completeGame.addFrame(new NormalFrame(5).setPinsDown(1, 10));
        this.completeGame.addFrame(new NormalFrame(6).setPinsDown(1, 0).setPinsDown(2, 0));
        this.completeGame.addFrame(new NormalFrame(7).setPinsDown(1, 0).setPinsDown(2, 6));
        this.completeGame.addFrame(new NormalFrame(8).setPinsDown(1, 10));
        this.completeGame.addFrame(new NormalFrame(9).setPinsDown(1, 2).setPinsDown(2, 8));
        this.completeGame.addFrame(new LastFrame(10).setPinsDown(1, 1).setPinsDown(2, 9).setPinsDown(3, 3));
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
    @Test
    public void setPinDown_FirstParameter_2Before1_Exception() {
        this.game.addFrame(new NormalFrame(2).setPinsDown(2, 3).setPinsDown(1, 6));
    }

    /**
     * Create a valid account.
     * @result Account will be persisted without any errors,
     *         and Account.getId() will no longer be <code>null</code>
     */
    @Test
    public void reset_ClearedFrame_ScoresAfterResetMatchScoresBeforeInit() {
        String scoreBeforeInit = this.completeGame.toString().substring(104, 155);

        for (int i = 0; i < this.completeGame.m_frames.size(); i++) {
            this.completeGame.m_frames.get(i).reset();
        }
        String scoreAfterReset = this.completeGame.toString().substring(104, 155);
        assertEquals("resetScore", scoreBeforeInit, scoreAfterReset);
    }
}
