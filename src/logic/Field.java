package logic;

import java.util.ArrayList;
import logic.Cards.Type;//ask it

/**
 * a field to place the domino on. Usually it is set to size 5*5. Each cell
 * contains a value of a half domino or a value meaning it is empty. The first
 * domino is city center which is set to the middle of the field.
 *
 * @author kobra
 */
public class Field {

    /**
     * value of an empty cell
     */
    public final static Cards EMPTY = Cards.E;
    /**
     * value of city center
     */
    public final static Cards TOWNHALL = Cards.CC;
    /**
     * position of city center or townhall
     */
    private Position townHallPos;
    /**
     * the board to play on
     */
    private final Cards[][] cells;
    private GUIConnector gui;
    /**
     * for returning the every disctrict type
     */
    private Type type;
    /**
     * storing the every district points
     */
    private int eachDisPoint;

    /**
     * constructs the board given by string. The rows are seperated by "\n".
     *
     * @param card
     */
    Field(GUIConnector gui, Cards[][] card) {
        this.cells = card;//refrence to one array
        this.gui = gui;
    }

    /**
     * another constructor which gets cards
     *
     * @param card
     */
    Field(Cards[][] card) {
        this.cells = card;//refrence to one array

    }

    /**
     * constructs the board given by string. The rows are seperated by "\n".
     * Every char other than 0..48 is set as empty cell. All columns have the
     * same length, all rows have the same length.
     *
     * @param s
     */
    Field(String s) {
        String[] lines = s.split("\n");

        int sizeX = lines[0].length();
        int k = (sizeX + 1) / 3;
        int sizeY = lines.length;
        this.cells = new Cards[k][sizeY];
        int counter = 0;
        for (int y = 0; y < sizeY; y++) {
            assert lines[y].length() == sizeX;//same length columns
            for (int j = 0; j < (k); j++) {
                String ss = String.valueOf(lines[y].charAt(counter));
                String sss = String.valueOf(lines[y].charAt(counter + 1));
                cells[j][y] = changeToCards(ss + "" + sss);
                if (cells[j][y].ordinal() < 0 || cells[j][y].ordinal() > Tiles.HIGHEST_VALUE) {
                    cells[j][y] = EMPTY;
                }
                counter = counter + 3;
            }
            counter = 0;
        }
    }

    /**
     * creates a board of the given size.
     *
     * @param gui
     * @param sizeX x-size of the board
     * @param sizeY y-size of the board
     */
    public Field(GUIConnector gui, int sizeX, int sizeY) {
        this.gui = gui;
        cells = new Cards[sizeX][sizeY];
        clear();
        setStarter();
    }

    /**
     * gets cells with their values
     *
     * @return the cells
     */
    Cards[][] getCells() {
        return cells;
    }

    /**
     * returns the symbol of cards.
     *
     * @return * int getSymbole() { return Cards.E.getSymbol(); }
     *
     * Cards getCard(Position pos) { return cells[pos.x()][pos.y()]; }
     *
     * Type getType() { return this.type; }
     */
    /**
     * return the points of each district
     *
     * @return
     */
    int getEachDisPoint() {
        return this.eachDisPoint;
    }

    /**
     * gets the count of columns
     *
     * @return the count of columns
     */
    public int getCols() {
        return cells.length;
    }

    /**
     * gets the count of rows.
     *
     * @return count or rows
     */
    public int getRows() {
        return cells[0].length;
    }

    /**
     * clears the board(from last semester assignment)
     */
    private void clear() {
        for (int i = 0; i < getCols(); i++) {
            for (int r = 0; r < getRows(); r++) {
                cells[i][r] = EMPTY;
            }
        }
    }

    /**
     * checks if x is a valid column on board.
     *
     * @param x to check
     * @return true if x is a valid column on board
     */
    boolean xIsValid(int x) {
        boolean setTrue = false;
        if (x >= 0 && x < cells.length) {
            setTrue = true;
        }
        return setTrue;
    }

    /**
     * checks if y is a valid row on board.
     *
     * @param y to check
     * @return true if y is a valid row on board
     */
    boolean yIsValid(int y) {
        boolean setTrue = false;
        if (y >= 0 && y < cells[0].length) {
            setTrue = true;
        }
        return setTrue;
    }

    /**
     * checks if position is a valid position on board.
     *
     * @param pos to check
     * @return true if pos is a valid position on board
     */
    boolean isValidPos(Position pos) {
        boolean setTrue = false;
        if (xIsValid(pos.x()) && yIsValid(pos.y())) {
            setTrue = true;
        }
        return setTrue;
    }

    /**
     * checks if this position is empty
     *
     * @param p to check
     * @return true if the cell at this position is empty
     */
    boolean isEmpty(Position p) {
        return cells[p.x()][p.y()] == EMPTY;
    }

    /**
     * returns value of a cell.
     *
     * @param x is the x position
     * @param y is the y position
     * @return value of the cell
     * @pre x and y have valid values
     */
    Cards getCell(int x, int y) {
        assert xIsValid(x);
        assert yIsValid(y);
        return cells[x][y];
    }

    /**
     * gets the value of the cell.
     *
     * @param pos position to look at
     * @return
     */
    public Cards getCell(Position pos) {
        return getCell(pos.x(), pos.y());
    }

    /**
     * sets the first domino to the middle of the board. calculate the exact
     * position of the town hall.
     *
     */
    private void setStarter() {

        int x = (getCols() / 2);
        int y = (getRows() / 2);
        townHallPos = new Position(x, y);
        cells[townHallPos.x()][townHallPos.y()] = TOWNHALL;
    }

    /**
     * Counts the sides already occupied.
     *
     * @param pos position to check
     * @return the sides already occupied(from last semester assignment)
     */
    public int occupiedSides(Position pos) { // make it public just for testing
        assert (pos != null);
        assert (isValidPos(pos));
        int sum = 0;
        Position[] neighbors = pos.getNeighbours();
        for (int i = 0; i < neighbors.length; i++) {
            if (isValidPos(neighbors[i]) && !isEmpty(neighbors[i])) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * checks if domino fits at the given position. One of the dominos values
     * must be side by side to city center or have the same type with their
     * neighbors.
     *
     * @param domino domino to lay
     * @param posFst position to lay firstHalf at
     * @return true, if the domino fits
     *
     *
     */
    public boolean fits(Domino domino, Position posFst) {
        assert (domino != null);
        assert (posFst != null);
        Position posSnd = domino.getSnd(posFst);
        boolean isFit = false;
        if (isValidPos(posFst) && isValidPos(posSnd) && isEmpty(posFst) && isEmpty(posSnd)) {
            Position[] neighborsFst = posFst.getNeighbours();
            for (int i = 0; i < neighborsFst.length && !isFit; i++) {
                if ((isValidPos(neighborsFst[i]) && (getCell(neighborsFst[i]).getType() == (domino.getFst().getType())
                        || getCell(neighborsFst[i]) == TOWNHALL)) && !isFit) {//neighbor fst it is town hall or not
                    isFit = true;

                }
            }
            Position[] neighborsSnd = posSnd.getNeighbours();
            for (int i = 0; i < neighborsSnd.length && !isFit; i++) {
                if ((isValidPos(neighborsSnd[i]) && (this.getCell(neighborsSnd[i]).getType() == (domino.getSnd().getType())
                        || this.getCell(neighborsSnd[i]) == TOWNHALL)) && !isFit) {

                    isFit = true;
                }

            }
        }
        return isFit;
    }

    /**
     * lays one domino on the board and displays it on gui.<br>
     * First half stays on posFst. The values of the domino are put in the two
     * cells.
     *
     * @param domino domino to lay
     * @param posFst top left postion
     * @param playerID
     * @pre openEnds must have been set before
     */
    public void lay(Domino domino, Position posFst, int playerID) {
        Position posSnd = domino.getSnd(posFst);
        cells[posFst.x()][posFst.y()] = domino.getFst();
        cells[posSnd.x()][posSnd.y()] = domino.getSnd();
        this.gui.showOnGrid(posFst,
                cells[posFst.x()][posFst.y()], posSnd, cells[posSnd.x()][posSnd.y()], playerID);
    }

    /**
     * try to put the domino on the board by setting it to each cell and
     * rotating it clockwise.it also checks if the current position has high
     * points. The board is proceeded line per line.
     *
     * @param domino domino to find valid position for
     * @return the position the domino fits; null if no position fits(form last
     * semester)
     */
    public Position findPosFor(Domino domino) {
        boolean isFound = false;
        Position pos = null;
        Position tempPos = null;
        int tempPoints = 0;
        int counter = 0;
        int rotation = domino.getRotation();
        int points = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                pos = new Position(i, j);
                while (counter < 4) {
                    isFound = fits(domino, pos);
                    if (isFound) {
                        points = bestPos(domino, pos);
                        if (points > tempPoints) {
                            tempPoints = points;
                            tempPos = new Position(pos.x(), pos.y());
                            rotation = domino.getRotation();
                        } else {
                            if (tempPos == null && tempPoints == points) {
                                tempPos = new Position(pos.x(), pos.y());
                                rotation = domino.getRotation();
                            }
                        }
                    }
                    domino.incRotation();

                    counter++;
                }
                counter = 0;
            }
        }
        for (int i = 0; i < 4 && rotation != domino.getRotation(); i++) {
            domino.incRotation();
        }
        return tempPos;
    }

    /**
     * compare every cell point with other cells and return highest point of
     * cell
     *
     * @param domino is the given domino
     * @param pos is given position
     * @return point of cells with highest point
     */
    private int bestPos(Domino domino, Position pos) {
        Cards[][] tempCells;
        tempCells = new Cards[cells.length][];
        for (int i = 0; i < cells.length; i++) {
            tempCells[i] = cells[i].clone();
        }
        Position sndPos = domino.getSnd(pos);
        tempCells[pos.x()][pos.y()] = domino.getFst();
        tempCells[sndPos.x()][sndPos.y()] = domino.getSnd();
        Field tempField = new Field(tempCells);
        int x = tempField.countPoints();
        return x;
    }

    /**
     * this method at first create a null array with type of position for
     * storing the the position of every district then, if cells is not empty
     * and it is not CC its added to this array. checks another cells if its the
     * same type of array and also its neighbors of that its added to the array
     * and increase the cells of array. for every district the length of array
     * is increased,
     *
     * @return
     */
    public ArrayList<ArrayList<Position>> DistrictCollect() {

        ArrayList<ArrayList<Position>> districtPos = new ArrayList<>();
        boolean isAdded = false;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != Cards.E && cells[i][j] != Cards.CC) {
                    isAdded = false;
                    if (districtPos.isEmpty()) {
                        Position pos = new Position(i, j);
                        ArrayList<Position> temp = new ArrayList<>();//initialize arraylist inside the arraylist
                        temp.add(pos);
                        districtPos.add(temp);
                        isAdded = true;

                    } else {
                        Position pos = new Position(i, j);//reset position with
                        for (int k = 0; k < districtPos.size(); k++) {//go through first dimension arraylist
                            ArrayList<Position> temp = districtPos.get(k); //arraylist inside of arraylist for every element
                            for (int s = 0; s < temp.size(); s++) {
                                if (cells[i][j].getType() == cells[temp.get(s).x()][temp.get(s).y()].getType()
                                        && pos.isNextTo(temp.get(s)) && !isAdded) {//is same type and neighbor
                                    temp.add(pos);//add it to second dimention
                                    isAdded = true;
                                }
                            }
                        }
                        if (!isAdded) {
                            ArrayList<Position> temp = new ArrayList<>();//add in first dimension and create new arraylist
                            temp.add(pos);
                            districtPos.add(temp);
                        }

                    }
                }
            }

        }

        for (int i = 0; i < districtPos.size(); i++) {
            for (int j = 0; j < districtPos.get(i).size(); j++) {
            }
        }
        districtPos = collectsameArea(districtPos);
        for (int i = 0; i < districtPos.size(); i++) {
            for (int j = 0; j < districtPos.get(i).size(); j++) {
            }
        }
        return districtPos;
    }

    /**
     * collect the area that has the same type. if the area has the same type
     * and also is the neighbor with other elements of the list , then it is
     * added to the current list . return the all positions.
     *
     * @param posDisctrict
     * @return an array list of all positions
     */
    private ArrayList<ArrayList<Position>> collectsameArea(ArrayList<ArrayList<Position>> posDisctrict) {
        ArrayList<ArrayList<Position>> districtPos = posDisctrict;
        for (int k = 0; k < districtPos.size() - 1; k++) {
            ArrayList<Position> temp = districtPos.get(k);
            for (int s = k + 1; s < districtPos.size(); s++) {
                ArrayList<Position> tempNext = districtPos.get(s);
                if (cells[temp.get(0).x()][temp.get(0).y()].getType() == cells[tempNext.get(0).x()][tempNext.get(0).y()].getType()) {
                    districtPos = comparepos(posDisctrict, temp, tempNext, k, s);
                }

            }
        }
        return districtPos;
    }

    /**
     * compare the array list temp with tempNext if they are next and also has
     * the same type. then tempnext is added to temp array.
     *
     * @param posDisctrict is main array contain positions
     * @param temp is given temp array
     * @param tempNext is next temp array
     * @param idxFst is the is of first
     * @param idx
     * @return
     */
    private ArrayList<ArrayList<Position>> comparepos(ArrayList<ArrayList<Position>> posDisctrict,
            ArrayList<Position> temp, ArrayList<Position> tempNext, int idxFst, int idx) {
        boolean isAdded = false;
        for (int i = 0; i < temp.size() && !isAdded; i++) {
            for (int j = 0; j < tempNext.size() && !isAdded; j++) {
                if (temp.get(i).isNextTo(tempNext.get(j))) {
                    posDisctrict.get(idxFst).addAll(tempNext);
                    isAdded = true;
                    if (idx < posDisctrict.size()) {
                        posDisctrict.remove(idx);
                    }

                }

            }
        }
        return posDisctrict;
    }

    /**
     *
     * counting the points according to every disctrict
     *
     * @return the points which is counted
     */
    protected int countPoints() {
        ArrayList<ArrayList<Position>> tempPoint = DistrictCollect();
        int sumSymbole = 0;

        int sum = 0;
        for (int i = 0; i < tempPoint.size(); i++) {
            sumSymbole = 0;
            type = cells[tempPoint.get(i).get(0).x()][tempPoint.get(i).get(0).y()].getType();
            for (int j = 0; j < tempPoint.get(i).size(); j++) {
                Position pos = tempPoint.get(i).get(j);
                sumSymbole = sumSymbole + cells[pos.x()][pos.y()].getSymbol();
            }

            sum = sum + sumSymbole * tempPoint.get(i).size();
        }
        return sum;
    }

    /**
     * convert the count points to string
     *
     * @return string of count points
     */
    protected String countPointsString() {
        ArrayList<ArrayList<Position>> tempPoint = DistrictCollect();
        int sumSymbole = 0;
        String sum = "";
        for (int i = 0; i < tempPoint.size(); i++) {
            sumSymbole = 0;
            sum = sum + "Area " + i + " : " + cells[tempPoint.get(i).get(0).x()][tempPoint.get(i).get(0).y()].getType() + " ";
            for (int j = 0; j < tempPoint.get(i).size(); j++) {
                Position pos = tempPoint.get(i).get(j);
                sumSymbole = sumSymbole + cells[pos.x()][pos.y()].getSymbol();
            }

            sum = sum + (sumSymbole * tempPoint.get(i).size()) + "\n";
        }
        return sum;
    }

    /**
     * add pos1 to the array of positions
     *
     * @param pos is the given array position
     * @param pos1 the position that should be added.
     * @return an array of positions
     */
    private Position[] addPosition(Position pos[], Position pos1) {
        Position[] test = new Position[pos.length + 1];
        for (int i = 0; i < pos.length; i++) {
            test[i] = pos[i];
        }
        test[pos.length + 1] = pos1;
        return test;
    }

    /**
     * calculate the prestige for the given cards
     *
     * @param card
     * @return the symbol of the given card
     */
    public int prestige(Cards card) {
        return card.getSymbol();
    }

    /**
     * the board with the values of the cell
     *
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<field>");
        for (int y = 0; y < cells[0].length; y++) {
            for (Cards[] cell : cells) {
                Cards value = cell[y];
                String sign = value == EMPTY ? "--" : String.valueOf(value) + "";
                if(value != null){
                    sign = sign + " ";
                }
                sb.append(sign);
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    /**
     * returns the card type of given string.
     *
     * @param x is given string
     * @return cards type of x
     */
    private Cards changeToCards(String x) {
        Cards card = null;
        switch (x) {
            case "A0":
                card = Cards.A0;
                break;
            case "A1":
                card = Cards.A1;
                break;
            case "A2":
                card = Cards.A2;
                break;
            case "A3":
                card = Cards.A3;
                break;
            case "H0":
                card = Cards.H0;
                break;
            case "H1":
                card = Cards.H1;
                break;
            case "H2":
                card = Cards.H2;
                break;
            case "H3":
                card = Cards.H3;
                break;
            case "I0":
                card = Cards.I0;
                break;
            case "I1":
                card = Cards.I1;
                break;
            case "I2":
                card = Cards.I2;
                break;
            case "I3":
                card = Cards.I3;
                break;
            case "O0":
                card = Cards.O0;
                break;
            case "O1":
                card = Cards.O1;
                break;
            case "O2":
                card = Cards.O2;
                break;
            case "O3":
                card = Cards.O3;
                break;
            case "P0":
                card = Cards.P0;
                break;
            case "P1":
                card = Cards.P1;
                break;
            case "P2":
                card = Cards.P2;
                break;
            case "P3":
                card = Cards.P3;
                break;
            case "S0":
                card = Cards.S0;
                break;
            case "S1":
                card = Cards.S1;
                break;
            case "S2":
                card = Cards.S2;
                break;
            case "S3":
                card = Cards.S3;
                break;
            case "CC":
                card = Cards.CC;
                break;
            case "--":
                card = Cards.E;
                break;
            default:
                card = Cards.E;
                break;
        }
        return card;
    }

    /**
     * move city center to up side
     */
    public void moveTownHallUp() {
        boolean isEmpty = false;
        if (cells[0][0] == EMPTY && cells[1][0] == EMPTY && cells[2][0] == EMPTY
                && cells[3][0] == EMPTY && cells[4][0] == EMPTY) {
            isEmpty = true;
        }
        if (isEmpty) {
            for (Cards[] cell : cells) {
                for (int j = 0; j < cell.length - 1; j++) {
                    cell[j] = cell[j + 1];
                    cell[j + 1] = EMPTY;
                }
            }
        } else {
        }
        this.gui.updateGrid(this, 0);
    }

    /**
     * movie the city center to down side
     */
    public void moveTownHallDown() {
        boolean isEmpty = false;
        if (cells[0][4] == EMPTY && cells[1][4] == EMPTY && cells[2][4] == EMPTY
                && cells[3][4] == EMPTY && cells[4][4] == EMPTY) {
            isEmpty = true;
        }
        if (isEmpty) {
            for (Cards[] cell : cells) {
                for (int j = cell.length - 1; j > 0; j--) {
                    cell[j] = cell[j - 1];
                    cell[j - 1] = EMPTY;
                }
            }
        } else {
        }
        this.gui.updateGrid(this, 0);
    }

    /**
     * move city center to left side
     */
    public void moveTownHallLeft() {
        boolean isEmpty = false;
        if (cells[0][0] == EMPTY && cells[0][1] == EMPTY && cells[0][2] == EMPTY
                && cells[0][3] == EMPTY && cells[0][4] == EMPTY) {
            isEmpty = true;
        }
        if (isEmpty) {
            for (int i = 0; i < cells.length - 1; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    cells[i][j] = cells[i + 1][j];
                    cells[i + 1][j] = EMPTY;
                }
            }
        } else {
        }
        this.gui.updateGrid(this, 0);
    }

    /**
     * mover city center to right side
     */
    public void moveTownHallRight() {
        boolean isEmpty = false;
        if (cells[4][0] == EMPTY && cells[4][1] == EMPTY && cells[4][2] == EMPTY
                && cells[4][3] == EMPTY && cells[4][4] == EMPTY) {
            isEmpty = true;
        }
        if (isEmpty) {
            for (int i = cells.length - 1; i > 0; i--) {
                for (int j = 0; j < cells[i].length; j++) {
                    cells[i][j] = cells[i - 1][j];
                    cells[i - 1][j] = EMPTY;
                }
            }
        } else {
        }
        this.gui.updateGrid(this, 0);
    }
}
