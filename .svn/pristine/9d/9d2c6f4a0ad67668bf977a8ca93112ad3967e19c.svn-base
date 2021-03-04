/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author kobra
 */
public class Human extends Players{
    
    private boolean isBot = false;
    
     public Human(GUIConnector gui, String name, Field field, int id) {
       //this.human = true;
       super(gui, "Human" + name, field, id);
    }
     
    public Human(GUIConnector gui, String name,int x, int y, int id) {
        super(gui, "Human" +name , x ,y, id);
    }
        /**
     * assigns the name and the tile from the parent class.
     *
     * @param name
     * @param tile
     * @param field
     */
    public Human(String name, Tiles tile, Field field, int id) {
        super("Human" +name, tile, field, id);
    }

    @Override
    boolean isBot() {
        return this.isBot;    
    }
}
