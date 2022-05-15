import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stev.bowling.BowlingException;
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
    @Test(expected = BowlingException.class)
    public void setPinDown_FirstParameter_2Before1_Exception() {
        this.game.addFrame(new NormalFrame(2).setPinsDown(2, 3).setPinsDown(1, 6));
    }

    @Test
    public void getCumulativeScore_OpenHit_get7(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 5).setPinsDown(2 ,2));
        int score = this.game.getCumulativeScore(1);
        Assert.assertEquals("The score should be ",7,score);

    }

    /**
     * Reset the scores of each frame.
     * @result The scores will be reset and thus be identical to the score
     * chart at the beginning of the game. The assertion is thus true.
     */
    @Test
    public void reset_ClearedFrame_ScoresAfterResetMatchScoresBeforeInit() {
        String scoreBeforeInit = this.game.toString().substring(104, 155);
        game = completeGame;
        for (int i = 0; i < this.game.m_frames.size(); i++) {
            this.game.m_frames.get(i).reset();
        }
        String scoreAfterReset = this.game.toString().substring(104, 155);
        assertEquals("resetScore", scoreBeforeInit, scoreAfterReset);
    }

    /**
     * Verifiy that the scores of the frames can be set after a reset.
     * @result The scores are added to the chart.
     */
    @Test
    public void setPinDown_AfterReset_setFrame1() {
        //String scoreBeforeInit = this.game.toString().substring(104, 155);
        game = completeGame;
        for (int i = 0; i < this.game.m_frames.size(); i++) {
            this.game.m_frames.get(i).reset();
        }

        this.game.m_frames.get(1).setPinsDown(1, 3).setPinsDown(2, 6);
    }

    /**
     * Verifiy that the score of the second roll cannot be added after the score of the first roll.
     * @result A BowlingException is raised : "You must first enter the score for roll 1".
     */
    @Test
    public void setPinDown_AfterReset_1Before2_Exception() {
        game = completeGame;
        for (int i = 0; i < this.game.m_frames.size(); i++) {
            this.game.m_frames.get(i).reset();
        }

        this.game.m_frames.get(1).setPinsDown(2, 3).setPinsDown(1, 6);
    }
}
