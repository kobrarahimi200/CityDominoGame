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
public class PlayersTest {
    
    public PlayersTest() {
    }

    @Test
    public void isBot() {
        
         Players p1 = new Human(new FakeGUI(), "Human", 5, 5, 0);
        Players p2 = new Bot(new FakeGUI(), "Bot", 5, 5, 1);
        assertTrue(!p1.isBot());
        //assertFalse(p1.isBot());

    }
    
}
