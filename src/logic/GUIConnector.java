
package logic;

/**
 * method the logic needs to display current game status on gui. Defined methods
 * in here trigger with gui.
 * @author kobra
 */
public interface GUIConnector {
     /**
     * sets a domino to the next box at given index. Empties it if domino is null.
     * @param index
     * @param domino 
     */
    void setToNextBox(int index, Domino domino);
    /**
     * sets the given domino with given id in current box
     * @param index
     * @param domino
     * @param playerID 
     */
    void setToCurrentBox(int index, Domino domino , int playerID);
    /**
     * shows the domino in the choose-box. Clears it if dominoRotated is null.
     * @param dominoRotated
     */
    void showInChooseBox(Domino dominoRotated);
    
     /**
     * shows the images corresponding to the two values on grid.
     * @param fstPos
     * @param value1
     * @param sndPos 
     * @param value2 
     * @param playerId 
     */
    void showOnGrid(Position fstPos, Cards value1, Position sndPos, Cards value2, int playerId);
      /**
     * updates the grids cells.
     * @param board is the given board
     * @param x is given index
     */
    void updateGrid(Field board, int x);
    /**
     * show the city center in the middle of every field
     * @param pos
     * @param value
     * @param palyerID 
     */
    public void showCityCenter(Position pos, Cards value, int palyerID);
    
    /**
     * show result in new window, like a alert message
     * @param result 
     */
    public void showResult(String result, String name, int point);
    
}
