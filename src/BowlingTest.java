import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stev.bowling.*;

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
     * Test if the frame creation works in order
     */
    @Test(expected = BowlingException.class)
    public void newNormalFrame_Order_Exception() {
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 10));
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 10));
    }

    /**
     * Test if the creation of a new frame can be done with negative numbers
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

    /**
     * Test if the normal frame can have the number 0, must raise an exception
     */
    @Test(expected = BowlingException.class)
    public void newNormalFrame_ZeroFrame_Exception() {
        this.game.addFrame(new NormalFrame(0).setPinsDown(1, 10));
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
     * Test if the last frame give the possibility to have 3 hits with 3 strikes
     */
    @Test
    public void lastFrame_3Strike_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 10).setPinsDown(2, 10).setPinsDown(3, 10));
        assertEquals(10,completeGame.getCumulativeScore(10));
    }
    /**
     * Test if the last frame give the possibility to have 3 hits with strike at first
     */
    @Test
    public void lastFrame_3hitWithStrikeFirst_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 10).setPinsDown(2, 5).setPinsDown(3, 2));
    }
    /**
     * Test if the last frame give the possibility to have 3 hits with strike at first
     */
    @Test
    public void lastFrame_WithSpare_Void() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 5).setPinsDown(2, 5).setPinsDown(3, 2));
    }

    /**
     * Test if the last frame block the possibility to have 3 hits with open hit
     */
    @Test(expected = BowlingException.class)
    public void lastFrame_WithOpenHit_BowlingException() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 5).setPinsDown(2, 2).setPinsDown(3, 2));
    }

    /**
     * Test if the last frame must have tree roll if there is a strike at roll 1 or 2
     */
    @Test(expected = BowlingException.class)
    public void lastFrame_WithStrike_BowlingExceptionIfNoThirdRoll() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 10).setPinsDown(2, 2));
    }

    /**
     * Test if the last frame must have tree roll if there is a spare at roll 2
     */
    @Test(expected = BowlingException.class)
    public void lastFrame_WithSpare_BowlingExceptionIfNoThirdRoll() {
        createGameWithoutLast();
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 2).setPinsDown(2, 8));
    }

    /**
     * Create a game with only  9 frames
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

// =============== Test sur la classe Game ===========================================

    /**
     * Test get frame with 0 frame
     */
    @Test(expected = BowlingException.class)
    public void getFrame_noFrameExisting_BowlingException() {
        this.game.getFrame(1);
    }

    /**
     * Test get frame with index out of bound (0)
     */
    @Test(expected = BowlingException.class)
    public void getFrame_FrameZero_BowlingException() {
        createGameWithoutLast();
        this.game.getFrame(0);
    }
    /**
     * Test get frame with index out of bound (negative)
     */
    @Test(expected = BowlingException.class)
    public void getFrame_Negative_BowlingException() {
        createGameWithoutLast();
        this.game.getFrame(-1);
    }
    /**
     * Test get frame with index out of bound (superior at ten)
     */
    @Test(expected = BowlingException.class)
    public void getFrame_SuperiorAtTen_BowlingException() {
        this.completeGame.getFrame(11);
    }

    /**
     * Test get frame with index equal 1
     */
    @Test()
    public void getFrame_First_BowlingException() {
        Frame shouldBe = new NormalFrame(1).setPinsDown(1, 1).setPinsDown(2, 9);
        this.game.addFrame(shouldBe);

        Frame result = this.game.getFrame(1);

        Assert.assertEquals("The frame should be",shouldBe,result);
    }

    /**
     * Test get frame with index equal 10
     */
    @Test()
    public void getFrame_Ten_BowlingException() {
        createGameWithoutLast();
        Frame shouldBe = new LastFrame(10).setPinsDown(1, 1).setPinsDown(2, 9).setPinsDown(3, 3);
        this.game.addFrame(shouldBe);

        Frame result = this.game.getFrame(10);

        Assert.assertEquals("The frame should be",shouldBe,result);
    }
// =============== Test sur la classe Frame ===========================================

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
     * Test if this is the tenth frame and strike or spare, it displays 3 character
     */
    @Test
    public void toString_FrameTenWithStrikeOrSpare_GetThreeCharacters(){
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
     * Test if this is the tenth frame and no strike and no spare, it displays 3 character
     */
    @Test(expected = BowlingException.class)
    public void toString_FrameTenWithoutStrikeOrSpare_GetThreeCharacters(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(2).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(3).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(4).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(5).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(6).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(7).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(8).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new NormalFrame(9).setPinsDown(1, 2).setPinsDown(2,3));
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 3).setPinsDown(2,3));

        String display = this.game.toString();
        Assert.assertEquals("The score should be ","|#1  |#2  |#3  |#4  |#5  |#6  |#7  |#8  |#9  |#10 |\n" +
                "|----+----+----+----+----+----+----+----+----+----+\n" +
                "|  8/|  81|  81|  81|  81|  81|  81|  81|  81| 81|\n" +
                "|18  |27  |36  |45  |54  |63  |72  |81  |90  |99  |",display);

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
     * Test if an Exception is caught when a Negative score is put on the first roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYNegativeOnFirstRoll_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, -1).setPinsDown(2, 3));
    }

    /**
     * Test if an Exception is caught when a Negative score is put on the second roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYNegativeOnSecondRoll_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 2).setPinsDown(2, -1));
    }

    /**
     * Test if an Exception is caught when a Negative score is put on the third roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYNegativeOnThirdRoll_BowlingException(){
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 1).setPinsDown(2, 9).setPinsDown(3,-1));
    }

    /**
     * Test if an Exception is caught when a score more than 10 is put on the first roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYMoreThanTenOnFirstRoll_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 11).setPinsDown(2, 3));
    }

    /**
     * Test if an Exception is caught when a score more than 10 is put on the second roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYMoreThanTenSecondRoll_BowlingException(){
        this.game.addFrame(new NormalFrame(1).setPinsDown(1, 3).setPinsDown(2, 11));
    }

    /**
     * Test if an Exception is caught when a score more than 10 is put in the third roll
     */
    @Test(expected = BowlingException.class)
    public void setPinsDown_ScoreYMoreThanTenOnthirdRoll_BowlingException(){
        this.game.addFrame(new LastFrame(10).setPinsDown(1, 5).setPinsDown(2, 5).setPinsDown(3,11));
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
        assertEquals("resetScore :", scoreBeforeInit, scoreAfterReset);
    }

    /**
     * Verify that the scores of the frames can be set after a reset.
     * @result The scores are added to the chart.
     */
    @Test
    public void setPinDown_AfterReset_setFrame1() {
        game = completeGame;
        for (int i = 0; i < this.game.m_frames.size(); i++) {
            this.game.m_frames.get(i).reset();
        }

        this.game.m_frames.get(1).setPinsDown(1, 3).setPinsDown(2, 6);
    }

    /**
     * Verify that the score of the second roll cannot be added after the score of the first roll.
     * @result A BowlingException is raised : "You must first enter the score for roll 1".
     */
    @Test(expected = BowlingException.class)
    public void setPinDown_AfterReset_1Before2_Exception() {
        game = completeGame;
        for (int i = 0; i < this.game.m_frames.size(); i++) {
            this.game.m_frames.get(i).reset();
        }

        this.game.m_frames.get(1).setPinsDown(2, 3).setPinsDown(1, 6);
    }

    /**
     * Verify that number of rolls counted byt the method correspond to the number of rolls set by frame.
     * @result The assertions are true.
     */
    @Test
    public void countRolls_returnExactNumberOfRollsByFrame() {
        game = completeGame;

        Assert.assertEquals("countRollsByFrame : ",2,this.game.m_frames.get(0).countRolls());
        Assert.assertEquals("countRollsByFrame : ",1,this.game.m_frames.get(1).countRolls());
        Assert.assertEquals("countRollsByFrame : ",3,this.game.m_frames.get(9).countRolls());

        this.game.m_frames.get(9).reset();
        this.game.m_frames.get(9).setPinsDown(1, 4).setPinsDown(2, 5);

        Assert.assertEquals("countRollsByFrame : ",2,this.game.m_frames.get(9).countRolls());
    }

    /**
     * Verify that number of pins down counted by the method correspond to the total sum of the score of each roll set by frame.
     * @result The assertions are true except for the last one. When there are only two rolls in the last
     * frame the score isn't exact : The assertion expects 9 (4 + 5) and the method tested return 8.
     */
    @Test
    public void countPinsDown_returnExactNumberOfPinsByFrame() {
        game = completeGame;

        Assert.assertEquals("countPinsDownByFrame : ",9,this.game.m_frames.get(0).countPinsDown());
        Assert.assertEquals("countPinsDownByFrame : ",10,this.game.m_frames.get(1).countPinsDown());
        Assert.assertEquals("countPinsDownByFrame : ",0,this.game.m_frames.get(5).countPinsDown());
        Assert.assertEquals("countPinsDownByFrame : ",13,this.game.m_frames.get(9).countPinsDown());

        this.game.m_frames.get(9).reset();
        this.game.m_frames.get(9).setPinsDown(1, 4).setPinsDown(2, 5);

        Assert.assertEquals("countPinsDownByFrame : ",9,this.game.m_frames.get(9).countPinsDown());
    }

    /**
     * Verify that number of pins down by roll counted by the method correspond to the score set by roll.
     * @result The assertions are true except for the last one. If a roll did not happen, the method should
     * return -1 as the number of pins down for the roll however it returns 0.
     */
    @Test
    public void getPinsDown_returnExactNumberOfPinsDownByRoll() {
        game = completeGame;

        Assert.assertEquals("getPinsDownByRoll : ",10,this.game.m_frames.get(1).getPinsDown(1));
        Assert.assertEquals("getPinsDownByRoll : ",0,this.game.m_frames.get(2).getPinsDown(2));
        Assert.assertEquals("getPinsDownByRoll : ",1,this.game.m_frames.get(9).getPinsDown(1));
        Assert.assertEquals("getPinsDownByRoll : ",9,this.game.m_frames.get(9).getPinsDown(2));
        Assert.assertEquals("getPinsDownByRoll : ",3,this.game.m_frames.get(9).getPinsDown(3));
        Assert.assertEquals("getPinsDownByRoll : ",-1,this.game.m_frames.get(1).getPinsDown(2));
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

}
