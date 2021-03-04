/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//make more test case for different type of fits and countpoint
package logic;

import static logic.Cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kobra
 */
public class FieldTest {

//    @Test
//    public void testBoard_int() {
//        Field field = new Field( new FakeGUI()), 7, 12);
//        assertEquals(Cards.E, field.getCell(0, 0));
//        assertEquals(Cards.E, field.getCell(6, 11));
//    }
    @Test
    public void testBoard_string_Empty() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, E, E, E},
        {E, E, E, E},
        {E, E, CC, E},
        {E, E, E, E}});
        assertEquals(4, field.getCols());
        assertEquals(4, field.getRows());
        assertEquals(E, field.getCell(3, 2));
        assertEquals(CC, field.getCell(2, 2));
        assertTrue(field.isEmpty(new Position(1, 0)));
    }

    @Test
    public void testIsValidPos() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, E, A1, E},
        {E, I0, CC, H0},
        {E, E, E, P0}});
        assertEquals(3, field.getCols());
        assertEquals(4, field.getRows());
        assertFalse(field.isValidPos(new Position(0, 4)));
        assertTrue(field.isValidPos(new Position(1, 1)));
        //assertTrue(field.isEmpty(new Position(1, 0)));  
    }

    @Test
    public void testIsEmpty() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, E, A1, E},
        {E, A0, E, E},
        {P0, E, CC, P1},
        {E, E, E, H0},
        {E, E, E, H0}});
        assertEquals(5, field.getCols());
        assertEquals(4, field.getRows());
        assertEquals(E, field.getCell(3, 2));
        assertFalse(field.isEmpty(new Position(1, 1)));
        assertTrue(field.isEmpty(new Position(1, 0)));
        assertEquals(A1, field.getCell(0, 2));
    }

    @Test
    public void testGetCell_Position() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, A1, E, E, E},
        {P1, A0, E, E, E},
        {P0, E, CC, H1, O1},
        {E, E, E, H0, E},
        {E, E, E, H0, E}});
        assertEquals(5, field.getCols());
        assertEquals(5, field.getRows());
        assertEquals(A0, field.getCell(1, 1));
        assertEquals(E, field.getCell(0, 2));
        assertEquals(H0, field.getCell(3, 3));
    }
//-------------------fits----------------------------

    @Test
    public void testFits_true() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A1, E, E, E},//P1 is in (0,2) position
            {P1, A0, E, E, E},
            {P0, P0, CC, H1, O1},
            {E, E, E, H0, E},
            {E, E, E, H0, E}});
        Position pos = new Position(0, 2);
        assertEquals(A0, field.getCell(1, 1));
        assertEquals(E, field.getCell(0, 2));
        System.out.println("pos" + field.occupiedSides(pos));
        assertEquals(1, field.occupiedSides(new Position(0, 2)));//P1 in first line
        assertEquals(2, field.occupiedSides(new Position(2, 0)));
        assertEquals(3, field.occupiedSides(new Position(1, 1)));
        assertTrue(field.fits(new Domino(Tiles.P0_P0_1), new Position(3, 0)));
        assertTrue(field.fits(new Domino(Tiles.A0_A0_7), new Position(0, 2)));
    }
    ///////  

    @Test
    public void testFits_trueCase() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, E, E},
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertTrue(field.fits(new Domino(Tiles.H0_H0_5, 0), new Position(0, 0)));
    }

    @Test
    public void testFits_trueCase2() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {A0, H1, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, O0, S1},
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertTrue(field.fits(new Domino(Tiles.S0_O1_39, 0), new Position(3, 3)));
    }

    @Test
    public void testFits_validPosition() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {A0, H1, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, O0, S1},
            {E, P1, S0, E, E},
            {A1, E, E, E, E}
        });
        //  assertFalse(field.fits(new Domino(Tiles.A0_A0_7, 0), new Position(3, 3)));
        assertTrue(field.isValidPos(new Position(2, 1)));
        assertTrue(field.isEmpty(new Position(0, 4)));// 0 yani row , 4 yani columns
        assertTrue(field.isEmpty(new Position(1, 2)));
    }

    @Test
    public void testFits_falseCase() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, CC, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E}
        });
        //assertFalse(field.fits(new Domino(Tiles.A0_A0_7, 0), new Position(3, 3)));
        assertTrue(field.isValidPos(new Position(1, 2)));
        Domino dom = new Domino(Tiles.S0_O1_39, 1);
        Position fst = new Position(1, 2);
        Position pos = dom.getSnd(fst);
        System.out.println(pos.x() + " , " + pos.y());
        assertTrue(field.fits(dom, new Position(1, 2)));
    }

    @Test
    public void testFits_FstSame() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, O0, P1, E},
            {E, E, CC, E, E},
            {E, E, E, A0, E},
            {E, E, E, E, E}
        });
        assertTrue(field.fits(new Domino(Tiles.O0_I2_47, 0), new Position(1, 1)));
        assertFalse(field.fits(new Domino(Tiles.A1_P0_31, 1), new Position(3, 2)));
    }

    @Test
    public void testFits_caseNormal() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, O0, P1, E},
            {E, E, CC, E, E},
            {E, E, E, A0, E},
            {E, E, E, E, E}
        });
        assertTrue(field.fits(new Domino(Tiles.O0_I2_47, 0), new Position(1, 1)));
        assertFalse(field.fits(new Domino(Tiles.A1_P0_31, 3), new Position(3, 2)));
    }

    @Test
    public void testFits_sideBySide() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, CC, P0, E},
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        assertFalse(field.fits(new Domino(Tiles.A1_P0_31, 1), new Position(2, 4)));

    }

    @Test
    public void testFits_DominoFst() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, S0, E},
            {E, E, E, E, E},
            {E, E, CC, P0, E},
            {E, E, E, H0, E},
            {E, E, E, E, E}
        });
        assertFalse(field.fits(new Domino(Tiles.H1_P0_24, 1), new Position(2, 4)));
        assertFalse(field.fits(new Domino(Tiles.H1_P0_24, 0), new Position(1, 3)));
        assertTrue(field.fits(new Domino(Tiles.H1_P0_24, 1), new Position(1, 2)));
        //assertTrue(field.fits(new Domino(Tiles.H1_P0_24, 3), new Position(4,3)));

    }

/////
    @Test
    public void testFits_false() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, A1, E, E, E},
        {P1, A0, E, E, E},
        {P0, P0, CC, H1, O1},
        {E, E, E, H0, E},
        {E, E, E, H0, E}});
        Position pos = new Position(0, 2);
        assertEquals(5, field.getCols());
        assertEquals(5, field.getRows());
        assertEquals(A0, field.getCell(1, 1));
        // System.out.println("pos" + field.occupiedSides(pos));
        assertEquals(1, field.occupiedSides(new Position(0, 2)));
        assertEquals(3, field.occupiedSides(new Position(1, 1)));
        assertFalse(field.fits(new Domino(Tiles.P0_S0_15), new Position(0, 0)));
        assertFalse(field.fits(new Domino(Tiles.P0_S0_15), new Position(0, 1))); // p0 s0
        assertFalse(field.fits(new Domino(Tiles.A0_A0_9), new Position(0, 3)));
        //assertTrue(field.fits(new Domino(Tiles.P0_S0_15), new Position(0, 2)));
    }

    @Test
    public void testLay() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A1, I0, E, E},
            {P1, A0, E, E, E},
            {E, E, CC, H1, O1},
            {E, E, E, H0, E},
            {E, E, E, H0, E}});
        assertEquals(5, field.getCols());
        assertEquals(5, field.getRows());
        Position pos = new Position(0, 3);
        field.lay(new Domino(Tiles.I1_P0_40), pos, 0); // p0, I3
        assertEquals(A0, field.getCell(1, 1));
        assertEquals(I1, field.getCell(0, 3));
        assertEquals(P0, field.getCell(1, 3));
        assertEquals(H0, field.getCell(3, 3));
    }

    @Test
    public void testFindPosFor() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A1, O1, E, E},
            {P1, A0, E, E, E},
            {E, E, CC, H1, O1},//(2,0) first cell in this row
            {E, E, E, H0, E},
            {E, E, E, H0, E}
        });
        Domino dom = new Domino(Tiles.I2_P0_45);
        Position pos = field.findPosFor(dom);
        assertEquals(new Position(2, 0), pos);
        field.lay(dom, pos, 0); // p0, I3
        //assertEquals(A0, field.getCell(1, 1));
        assertEquals(P0, field.getCell(2, 0));
        assertEquals(I2, field.getCell(3, 0));
        assertEquals(H0, field.getCell(3, 3));
    }

    @Test
    public void testFindPosFor_rightSideHighPoint() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A1, O1, E, E},
            {P1, A0, I1, E, E},
            {E, E, CC, H1, O1},
            {E, E, E, H0, E},
            {E, E, E, H0, E}});
        Domino dom = new Domino(Tiles.I2_P0_45);
        Position pos = field.findPosFor(dom);
        assertEquals(new Position(0, 3), pos);
        field.lay(dom, pos, 0); // p0, I3
        assertEquals(O1, field.getCell(0, 2));
        assertEquals(E, field.getCell(2, 0));
        assertEquals(P0, field.getCell(0, 3));
        assertEquals(I2, field.getCell(1, 3));
        assertEquals(H0, field.getCell(3, 3));
    }

    @Test
    public void testFindPosFor_emptyField() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, CC, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E}});

        Domino dom = new Domino(Tiles.P0_P0_1);
        Position pos = field.findPosFor(dom);
        assertEquals(new Position(0, 2), pos);
        // field.lay(dom, pos, 0); // p0, I3
        // assertEquals(P0, field.getCell(2, 0));
        //assertEquals(I2, field.getCell(3, 0));
        //assertEquals(H0, field.getCell(3, 3));
    }

    @Test
    public void testFindPosFor_EmptyCell() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, CC, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E}});
        Domino dom = new Domino(Tiles.I2_P0_45);
        Position pos = field.findPosFor(dom);
        assertEquals(new Position(0, 2), pos);
        field.lay(dom, pos, 0);
        assertEquals(I2, field.getCell(0, 2));
        assertEquals(P0, field.getCell(1, 2));
    }

//-------------------count posint----------------------------
    @Test
    public void testcountPoints_bigParkArea() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, E, E, E},
            {P1, P2, E, E, E},
            {P0, P2, CC, H1, P1},
            {E, E, A0, H0, E},
            {E, E, A1, H0, E}});
        assertEquals(5, field.getCols());
        System.out.println(field.getCells()[1][1]);
        assertEquals(2, field.getCells()[1][1].getSymbol());
        //assertEquals(7, field.countPoints());
        assertEquals(4 * 5 + 3 * 1 + 2 * 1 + 1, field.countPoints());
    }

    @Test
    public void testcountPoints_distribited() {
        Field field = new Field(new FakeGUI(), new Cards[][]{{E, E, E, E, E},
        {P1, A1, E, E, E},
        {P0, A0, CC, H0, E},
        {E, E, E, H0, I0},
        {E, E, E, E, E}});
        assertEquals(5, field.getCols());
        assertEquals(0, field.prestige(A0));
        assertEquals(1, field.prestige(P1));
        assertEquals(1, field.prestige(A1));
        assertEquals(4, field.countPoints());
    }
    @Test
    public void testcountPoints_muliDisctributedSameArea() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {P0,P0, E,  P0, P0},
            {E, P2, P0, P0, E},
            {E, P2, CC, H1, P1},
            {E, E,  E, H0, E},
            {E, E,  E, H0, E}});
        assertEquals(5, field.getCols());
        System.out.println(field.getCells()[1][1]);
        assertEquals(2, field.getCells()[1][1].getSymbol());
        //assertEquals(7, field.countPoints());
        assertEquals(36, field.countPoints());
    }

    @Test
    public void testcountPoints_muliDisctributed_vertical() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {P0,E,  E, E, E},
            {P0,P1, E, E, E},
            {E, P1, CC,H0,E},
            {E, P0, P0,H0,E},
            {E, P1, P1, H0, E}});
        assertEquals(5, field.getCols());
        System.out.println(field.getCells()[1][1]);
        //assertEquals(2, field.getCells()[1][1].getSymbol());
        //assertEquals(7, field.countPoints());
        assertEquals(32, field.countPoints());
    }


    @Test
    public void testcountPoints_diffSymboles() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A1, H2, E, E},
            {S1, A0, CC, E, E},
            {E, E, H0, I2, E},
            {E, E, S3, H1, E}});
        assertEquals(4, field.getCols());
        assertEquals(2 + 2 + 1 + 2 + 3 + 1, field.countPoints());
    }

    @Test
    public void testcountPoints_diff() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, E, S0, S0, H1},
            {E, S0, S0, E, P0},
            {A1, P0, CC, I1, P0},
            {H1, P0, H1, A0, E},
            {H1, P0, H0, H0, E}});
        assertEquals(5, field.getCols());
        assertEquals(10, field.countPoints());
    }

    @Test
    public void testcountPoints_sameSymboles() {
        Field field = new Field(new FakeGUI(), new Cards[][]{
            {E, A0, H0, E, E},
            {S0, A0, CC, E, E},
            {E, E, H0, I0, E},
            {O0, S0, S0, H0, E}});
        assertEquals(4, field.getCols());
        assertEquals(0, field.countPoints());
    }

    @Test
    public void testcountPoints_withoutSymbol() {

        Field field = new Field(new FakeGUI(), new Cards[][]{{E, E, E, E, E},
        {P0, S0, E, E, E},
        {P0, H0, CC, E, E},
        {E, H0, E, E, E},
        {E, H0, E, E, E}});
        assertEquals(5, field.getCols());
        assertEquals(0, field.countPoints());
    }

    @Test
    public void calcPoints_oneRegion() {
        Field board = new Field(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, E, E},
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        assertEquals(4, board.countPoints());
    }

//-------------------find position for----------------------------
    @Test
    public void testFindPos1() {
        Field board = new Field(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, E, E},
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Position p = board.findPosFor(new Domino(Tiles.H0_H0_4));
        assertTrue(board.findPosFor(new Domino(Tiles.H0_H0_4)).equals(p));
    }

    @Test //(expected = AssertionError.class)
    public void testFindPos2() {
        Field board = new Field(new FakeGUI(), new Cards[][]{
            {E, E, H0, S0, E},
            {E, H0, E, E, E},
            {E, P1, CC, E, E},
            {E, P1, S0, E, E},
            {E, E, E, E, E}
        });
        Position p = board.findPosFor(new Domino(Tiles.S0_S0_11, 1));
        assertTrue(board.findPosFor(new Domino(Tiles.S0_S0_11, 1)).equals(p));
    }

    @Test
    public void testSmallFieldStringType() {
        String s = "-- A0 -- P0 H1";

        Field board = new Field(s);
        System.out.println(board.toString());
    }

    @Test
    public void testFieldStringType() {
        String s = "-- -- -- -- --\n"
                + "-- S0 S0 -- --\n"
                + "P0 I3 CC -- --\n"
                + "-- -- -- -- --\n"
                + "-- -- -- -- --\n";

        Field board = new Field(s);
        System.out.println("fiels to string \n"+ board);
    }

}
