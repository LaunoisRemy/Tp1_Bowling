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
// =============== Test sur la classe NormalFrame =====================================

    /**
     * Test if the frame creation work in order
     */
    @Test(expected = BowlingException.class)
    public void newNormalFrame_Order_Exception() {
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
    }

    /**
     * Test si la creation d'une nouvelle frame peut se faire avec des chiffres negatifs
     */
    @Test(expected = BowlingException.class)
    public void newNormalFrame_OutOfRangeNegative_Exception() {
        this.game.addFrame(new NormalFrame(-1).setPinsDown(1, 10));
    }

    /**
     * Test if the normal frame can have the number 10, must raise an exception
     */
    @Test(expected = BowlingException.class)
    public void newNormalFrame_OutOfRange_Exception() {
        this.game.addFrame(new NormalFrame(10).setPinsDown(1, 10));
    }

// =============== Test sur la classe LastFrame =====================================

    /**
     * Test if the last frame can have another number than 10, should throw a BowlingException
     */
    @Test(expected = BowlingException.class)
    public void lastFrame_OutOfRange_BowlingException() {
        this.game.addFrame(new LastFrame(2).setPinsDown(1, 10));
    }

    /**
     * Test if the last frame give the possibilyt to have 3 hits with 3 strikes
     */
    @Test
    public void lastFrame_3Strike_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 10).setPinsDown(2, 10).setPinsDown(10, 10));
        assertEquals(10,completeGame.getCumulativeScore(10));
    }
    /**
     * Test if the last frame give the possibilyt to have 3 hits with strike at first
     */
    @Test
    public void lastFrame_3hitWithStrikeFirst_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 10).setPinsDown(2, 5).setPinsDown(3, 2));
    }
    /**
     * Test if the last frame give the possibilyt to have 3 hits with strike at first
     */
    @Test
    public void lastFrame_WithSpare_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 5).setPinsDown(2, 5).setPinsDown(3, 2));
    }

    /**
     * Method permit to create a game with 9 frame
     */
    private void createGameWithoutLast() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(3).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(5).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(6).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(7).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(8).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(9).setPinsDown(1, 10));
    }

// =============== Test sur la classe Frame ===========================================
    /**
     * Test if setPinDown works if hit 1 and before 2
     */
    @Test
    public void setPinDown_FirstParameter_1Before2() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 3).setPinsDown(2, 6));
    }

    /**
     * Test exception if the 2 is before the 1
     */
    @Test(expected = BowlingException.class)
    public void setPinDown_FirstParameter_2Before1_Exception() {
        this.game.addFrame(new NormalFrame(2).setPinsDown(2, 3).setPinsDown(1, 6));
    }

    // =============== Test sur la classe Game ===========================================

    /**
     *  Test if getCumulativeScore gets the right score if it is an open move
     */
    @Test
    public void getCumulativeScore_OpenHit_Get7(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 5).setPinsDown(2 ,2));
        int score = this.game.getCumulativeScore(1);
        Assert.assertEquals("The score should be ",7,score);
    }

    /**
     * Test if getCumulativeScore retrieves the correct score if it's a three strike in a row
     */
    @Test
    public void getCumulativeScore_3Strike_Get30(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(3).setPinsDown(1, 10));

        int score = this.game.getCumulativeScore(1);

        Assert.assertEquals("The score should be ",30,score);
    }

    /**
     * Test if getCumulativeScore retrieves the correct score if it's a strike and then an open move
     */
    @Test
    public void getCumulativeScore_1Strike_Get17(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 5).setPinsDown(2 ,2));
        int score = this.game.getCumulativeScore(1);
        Assert.assertEquals("The score should be ",17,score);
    }

    /**
     * Test if getCumulativeScore gets the right score if it's spare
     */
    @Test
    public void getCumulativeScore_Spare_CumulPoints(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 8).setPinsDown(2 ,2));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 5).setPinsDown(2,5));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 10));

        int scoreFrame1 = this.game.getCumulativeScore(1);
        int scoreFrame3 = this.game.getCumulativeScore(2);

        Assert.assertEquals("The score should be ",15,scoreFrame1);
        Assert.assertEquals("The score should be ",35,scoreFrame3);
    }

    /**
     * Test if getCumulativeScore sends an error if the frame does not exist
     */
    @Test(expected = BowlingException.class)
    public void getCumulativeScore_Parameters_BowlingException() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 8).setPinsDown(2 ,2));
        this.game.getCumulativeScore(2);
    }

    /**
     * Reset the scores of each frame.
     * @result The scores will be reset and thus be identical to the score
     * chart at the beginning of the game. The assertion is thus true.
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
