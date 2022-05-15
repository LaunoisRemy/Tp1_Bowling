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



    @Test
    public void getCumulativeScore_OpenHit_Get7(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 5).setPinsDown(2 ,2));
        int score = this.game.getCumulativeScore(1);
        Assert.assertEquals("The score should be ",7,score);

    }

    /**
     * Test if when there is a strike, it displays "X" and a space
     */
    @Test
    public void toString_Strike_GetXAndSpace(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  X |    |    |    |    |    |    |    |    |    |\n" +
                "|    |    |    |    |    |    |    |    |    |    |",display);

    }

    /**
     * Test if when there is a spare, it displays "score/"
     */
    @Test
    public void toString_Spare_GetScoreSlash(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 9).setPinsDown(2,1));
        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  9/|    |    |    |    |    |    |    |    |    |\n" +
                "|    |    |    |    |    |    |    |    |    |    |",display);

    }

    /**
     * Test if when there is no pin touched, it displays "--"
     */
    @Test
    public void toString_NoPinsTouched_GetVoid(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 0).setPinsDown(2,0));
        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  --|    |    |    |    |    |    |    |    |    |\n" +
                "|0   |    |    |    |    |    |    |    |    |    |",display);

    }

    /**
     * Test if when there is an open hit (example 2 and 3), it displays "23" so two character
     */
    @Test
    public void toString_OpenHit_GetScoreWithTwoCharacter(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 2).setPinsDown(2,3));
        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  23|    |    |    |    |    |    |    |    |    |\n" +
                "|5   |    |    |    |    |    |    |    |    |    |",display);

    }

    /**
     * Test if this is the tenth frame, it displays 3 character
     */
    @Test
    public void toString_FrameTen_GetThreeCharacters(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(3).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(5).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(6).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(7).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(8).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(9).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 3).setPinsDown(2,7).setPinsDown(3,3));

        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  23|  23|  23|  23|  23|  23|  23|  23|  23| 3/3|\n" +
                "|5   |10  |15  |20  |25  |30  |35  |40  |45  |58  |",display);

    }

    /**
     * Test if an Exception is caught when an impossible roll is made so it's no allowed to exceed 10
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ImpossibleRoll_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 8).setPinsDown(2, 3));
    }

    /**
     * Test if when there is a strike, We can't do a second roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_SecondRollNotAllowedWhenStrike_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10).setPinsDown(2, 3));
    }

    /**
     * Test if an Exception is caught when a Negative roll is put
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_RollXNegative_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(-1, 10).setPinsDown(2, 3));
    }

    /**
     * Test if an Exception is caught when a roll more than 2 is put
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_RollXMoreThanTwo_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(3, 10).setPinsDown(2, 3));
    }

    /**
     * Test if an Exception is caught when a Negative score is put
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYNegative_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, -1).setPinsDown(2, 3));
    }
    /**
     * Test if an Exception is caught when a score more than 10 is put
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYMoreThanTen_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 11).setPinsDown(2, 3));
    }

    /**
     * Test if you put the rolls in the right order it works
     */
    @Test
    public void setPinDown_FirstParameter_1Before2() {
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 3).setPinsDown(2, 6));

    }

    /**
     * Test if you don't put the rolls in the right order it doesn't work
     */
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
