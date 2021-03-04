
package logic;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kobra
 */
public class GameTest {
    
  
     @Test
    public void testGetDomFromStack() {
        Game game = new Game(new FakeGUI(), 3, 4,2,1);
        List<Domino> stack = new LinkedList<>();
        assertNull(game.getDomFromStack(stack));
        
        stack.add(new Domino(Tiles.A0_A0_7));
        stack.add(new Domino(Tiles.A0_A0_9));
        game.getDomFromStack(stack);
        assertEquals(1, stack.size());
    }  
    @Test
    public void testCheckSortedNextBox() {
        Game game = new Game(new FakeGUI(), 3, 4,2,2);
         for (Domino nextBox : game.getNextBox()) {
             System.out.println("this is next box" + nextBox);
        }
       
    } 

    
    @Test
    public void testGetDomFromStack1() {
        Game game = new Game(new FakeGUI(), 3, 4 ,2,1);
        List<Domino> stack = new LinkedList<>();
        assertNull(game.getDomFromStack(stack));
        
        stack.add(new Domino(Tiles.A0_A0_7));
        assertEquals(new Domino(Tiles.A0_A0_7), game.getDomFromStack(stack));
        assertEquals(0, stack.size());
    }
    
    
}
