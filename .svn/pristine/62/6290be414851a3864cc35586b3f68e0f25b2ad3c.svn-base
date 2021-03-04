/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kobra
 */
public class DominoTest {
    
    public DominoTest() {
    }
     
    @Test
    public void testPositions_rot0() {
        Domino dom = new Domino(Tiles.P0_I3_48, 0);
        assertEquals(0, dom.getRotation());
        assertEquals(Cards.P0, dom.getFst());
        assertEquals(Cards.I3, dom.getSnd());
        assertEquals(new Position(2,1), dom.getSnd(new Position(1,1)));
    }

    @Test
    public void testPositions_rot1() {
        Domino dom = new Domino(Tiles.P0_I3_48, 1);
        assertEquals(1, dom.getRotation());
        assertEquals(Cards.P0, dom.getFst());
        assertEquals(Cards.I3, dom.getSnd());
        assertEquals(new Position(1,2), dom.getSnd(new Position(1,1)));
    }
    
    @Test
    public void testPositions_rot2() {
        Domino dom = new Domino(Tiles.P0_I3_48, 2);
        assertEquals(2, dom.getRotation());
        assertEquals(Cards.I3, dom.getFst());
        assertEquals(Cards.P0, dom.getSnd());
        assertEquals(new Position(1,1), dom.getSnd(new Position(0,1)));
        assertEquals(3, dom.getFst().getSymbol());
        assertEquals(0, dom.getSnd().getSymbol());
    }
    
    @Test
    public void testPositions_rot3() {
        Domino dom = new Domino(Tiles.P0_I3_48, 3);
        assertEquals(3, dom.getRotation());
        assertEquals(Cards.I3, dom.getFst());
        assertEquals(Cards.P0, dom.getSnd());
        assertEquals(new Position(1,2), dom.getSnd(new Position(1,1)));
    }
    
}
