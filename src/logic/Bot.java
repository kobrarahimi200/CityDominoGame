
package logic;

/**
 *
 * @author kobra
 */
public class Bot extends Players{
       private boolean isBot = true;
    
    /**
  Ï   * default constructor
     * @param gui
     * @param name 
     * @param field 
     * @param id 
     */
     public Bot(GUIConnector gui, String name, Field field, int id) {
        super(gui, "Bot" +name, field, id);
    }
     /**
      * constructor with four parameters
      * @param gui
      * @param name is the name of player
      * @param x is size of field
      * @param y is y size of the field
      * @param id  of player
      */
    public Bot(GUIConnector gui, String name, int x, int y, int id) {
        super(gui, "Bot" +name, x, y, id);
    }
    /**
     * assigns the name and the tile from the parent class.
     *
     * @param name
     * @param tile
     * @param field
     * @param id
     */
    public Bot(String name, Tiles tile, Field field, int id) {
        super("Bot" +name, tile, field, id);
    }
    
    @Override
    public boolean isBot(){
        return this.isBot;
    }
}
