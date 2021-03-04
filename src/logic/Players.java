
package logic;

/**
 * creates the players with two type , human and bot. Each player has name, id,
 * tiles and field. there is abstract method isBot, if the player is bot then 
 * returns true.
 * 
 */
public abstract class Players{
    
     private final String name;
     private Tiles tile;
     private Field field;
     private final int id;
     private GUIConnector gui;
    /**
     * Creates a player with a given name.
     *
     * @param name name for the player
     * @param field
     */
     Players(GUIConnector gui, String name, Field field, int id) {
         
        this.gui = gui;
        this.name = name;
        this.id= id;
        this.field = new Field(this.gui, field.getCells());
    }
     
     /**
      * call when new game starts
      * @return 
      */
     public Field newStartField() {
        this.field = new Field(this.gui, field.getCols(), field.getRows());
        return this.field;
    }
    /**
     * constructor for using in the game class
     * @param gui
     * @param name
     * @param x
     * @param y
     * @param id 
     */
     Players(GUIConnector gui, String name, int x, int y, int id) {
        this.gui = gui;
        this.name = name;
        this.field = new Field(this.gui, x, y);
        this.id= id;
    }
    
    /**
     * Creates a player with a name and the given tile.
     *
     * @param name name for the player
     * @param tile  for the player
     */
    Players(String name, Tiles tile, Field field, int id) {
        this.tile = tile; 
        this.name = name;
        this.field = field;
        this.id = id;
    }
      /**
     * Returns the name of the player
     *
     * @return name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * return the is of player
     * @return id value of player
     */
    public int getId(){
        return id;
    }
    /**
     * return the chosen tile with current player
     * @return 
     */
    protected Tiles getTile(){
        return this.tile;
    }
    /**
     * return the field of player
     * @return 
     */
    protected Field getField(){
        return this.field;
    }
    /**
     * return true if the player is bot
     * @return 
     */
    abstract boolean isBot();
    
    @Override
    public String toString() { 
        return " "+getField().toString();
    }
    
}