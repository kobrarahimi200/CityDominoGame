package logic;

/**
 * This class returns the chosen domino by players and current player who chosen
 * the domino.
 *
 * @author kobra
 */
public class Choose {

    private final Domino domino;
    private Players currPlayer;

    /**
     * default constructor
     *
     * @param domino
     * @param player
     */
    public Choose(Domino domino, Players player) {

        this.domino = domino;
        this.currPlayer = player;
    }

    /**
     * constructor with just one parameter
     *
     * @param domino
     */
    public Choose(Domino domino) {
        this.domino = domino;
    }

    /**
     * returns the player
     *
     * @return
     */
    Players getPlayer() {
        return this.currPlayer;
    }

    /**
     * returns the domino
     *
     * @return
     */
    public Domino getDomino() {
        return this.domino;
    }

    @Override
    public String toString() {
        return " " + domino;
    }

}
