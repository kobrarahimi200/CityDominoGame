package logic;

import java.util.Arrays;
import java.util.List;

/**
 * A game bag for all players contains 48 playing cards in the size of two
 * cells, each of which displays one (possibly the same) district type on its
 * two halves. The district types differ in image and background color. Each
 * playing card has a defined value.
 *
 * @author kobra
 */
public enum Tiles {
    P0_P0_1(Cards.P0, Cards.P0), P0_P0_2(Cards.P0, Cards.P0), H0_H0_3(Cards.H0, Cards.H0), H0_H0_4(Cards.H0, Cards.H0),
    H0_H0_5(Cards.H0, Cards.H0), H0_H0_6(Cards.H0, Cards.H0), A0_A0_7(Cards.A0, Cards.A0), A0_A0_8(Cards.A0, Cards.A0),
    A0_A0_9(Cards.A0, Cards.A0), S0_S0_10(Cards.S0, Cards.S0), S0_S0_11(Cards.S0, Cards.S0), O0_O0_12(Cards.O0, Cards.O0),
    P0_H0_13(Cards.P0, Cards.H0), P0_A0_14(Cards.P0, Cards.A0), P0_S0_15(Cards.P0, Cards.S0), P0_O0_16(Cards.P0, Cards.O0),
    H0_A0_17(Cards.H0, Cards.A0), H0_S0_18(Cards.H0, Cards.S0), P1_H0_19(Cards.P1, Cards.H0), P1_A0_20(Cards.P1, Cards.A0),
    P1_S0_21(Cards.P1, Cards.S0), P1_O0_22(Cards.P1, Cards.O0), P1_I0_23(Cards.P1, Cards.I0), H1_P0_24(Cards.H1, Cards.P0),
    H1_P0_25(Cards.H1, Cards.P0), H1_P0_26(Cards.H1, Cards.P0), H1_P0_27(Cards.H1, Cards.P0), H1_A0_28(Cards.H1, Cards.A0),
    H1_S0_29(Cards.H1, Cards.S0), A1_P0_30(Cards.A1, Cards.P0), A1_P0_31(Cards.A1, Cards.P0), A1_H0_32(Cards.A1, Cards.H0),
    A1_H0_33(Cards.A1, Cards.H0), A1_H0_34(Cards.A1, Cards.H0), A1_H0_35(Cards.A1, Cards.H0), P0_S1_36(Cards.P0, Cards.S1),
    A0_S1_37(Cards.A0, Cards.S1), P0_O1_38(Cards.P0, Cards.O1), S0_O1_39(Cards.S0, Cards.O1), I1_P0_40(Cards.I1, Cards.P0),
    P0_S2_41(Cards.P0, Cards.S2), A0_S2_42(Cards.A0, Cards.S2), P0_O2_43(Cards.P0, Cards.O2), S0_O2_44(Cards.S0, Cards.O2),
    I2_P0_45(Cards.I2, Cards.P0), O0_I2_46(Cards.O0, Cards.I2), O0_I2_47(Cards.O0, Cards.I2), P0_I3_48(Cards.P0, Cards.I3);

    public static final int HIGHEST_VALUE = 48;

    private final Cards fst;// first parts of tile
    private final Cards snd;// second part of tile
   

    /**
     * default constructor
     *
     * @param fst is the first part of tile
     * @param snd is the second part of tile
     */
    Tiles(Cards fst, Cards snd) {
        this.fst = fst;
        this.snd = snd;
    }

    /**
     * return the first part of tile
     *
     * @return
     */
    public Cards getFst() {
        return fst;
    }

    /**
     * return the second parts
     *
     * @return
     */
    public Cards getSnd() {
        return snd;
    }

    @Override
    public String toString() {
        //return tile.toString() + rotation;
        return fst + "" + snd;
    }
    
    /**
     * gets all values of Tiles in a list. All existing dominos in list have to
     * be removed so each domino is single in list.
     *
     * @param tiles
     * @return same list with all dominos once each
     */
    protected static List<Tiles> fill(List<Tiles> tiles) {
        //tiles.clear();
        tiles.addAll(Arrays.asList(Tiles.values()));
        return tiles;
    }
 /**
  * checks if the given string contain in the bag or not. if contains the tile of that returns
  * @param bag includes all tiles
  * @param s is given string to checks
  * @return a tile of given string
  */   
 protected static Tiles getTiles(List<Tiles> bag, String s){
     Tiles tile = null;
        switch (s) {
            case "P0P0":
                if(bag.contains(P0_P0_1)){
                    tile = P0_P0_1;
                }
                else{
                    tile = P0_P0_2;
                }      break;
            case "H0H0":
                if(bag.contains(H0_H0_3)){
                    tile = H0_H0_3;
                }
                else if(bag.contains(H0_H0_4)){
                    tile = H0_H0_4;
                }
                else if(bag.contains(H0_H0_5)){
                    tile = H0_H0_5;
                }
                else{
                    tile = H0_H0_6;
                }      break;
            case "A0A0":
                if(bag.contains(A0_A0_7)){
                    tile = A0_A0_7;
                }
                else if(bag.contains(A0_A0_8)){
                    tile = A0_A0_8;
                }
                else{
                    tile = A0_A0_9;
                }      break;
            case "S0S0":
                if(bag.contains(S0_S0_10)){
                    tile = S0_S0_10;
                }
                else{
                    tile = S0_S0_11;
                }      break;
            case "O0O0":
                tile = O0_O0_12;
                break;
            case "P0H0":
                tile = P0_H0_13;
                break;
            case "P0A0":
                tile =P0_A0_14 ;
                break;
            case "P0S0":
                tile =P0_S0_15 ;
                break;
            case "P0O0":
                tile =P0_O0_16 ;
                break;
            case "H0A0":
                tile = H0_A0_17;
                break;
            case "H0S0":
                tile =H0_S0_18;
                break;
            case "P1H0":
                tile =P1_H0_19 ;
                break;
            case "P1A0":
                tile =P1_A0_20 ;
                break;
            case "P1S0":
                tile =P1_S0_21 ;
                break;
            case "P1O0":
                tile =P1_O0_22 ;
                break;
            case "P1I0":
                tile =P1_I0_23 ;
                break;
            case "H1P0":
                if(bag.contains(H1_P0_24)){
                    tile = H1_P0_24;
                }
                else if(bag.contains(H1_P0_25)){
                    tile = H1_P0_25;
                }else if(bag.contains(H1_P0_26)){
                    tile = H1_P0_26;
                }else{
                    tile = H1_P0_27;
                }      break;
            case "H1A0":
                tile =H1_A0_28 ;
                break;
            case "H1S0":
                tile =H1_S0_29 ;
                break;
            case "A1P0":
                if(bag.contains(A1_P0_30)){
                    tile =A1_P0_30 ;
                }else{
                    tile =A1_P0_31 ;
                }      break;
            case "A1H0":
                if(bag.contains(A1_H0_32)){
                    tile =A1_H0_32 ;
                }else if(bag.contains(A1_H0_33)){
                    tile =A1_H0_33 ;
                }else if(bag.contains(A1_H0_34)){
                    tile =A1_H0_34 ;
                }
                else{
                    tile = A1_H0_35;
                }     break;
            case "P0S1":
                tile =P0_S1_36 ;
                break;
            case "A0S1":
                tile =A0_S1_37 ;
                break;
            case "P0O1":
                tile =P0_O1_38 ;
                break;
            case "S0O1":
                tile =S0_O1_39 ;
                break;
            case "I1P0":
                tile =I1_P0_40 ;
                break;
            case "P0S2":
                tile =P0_S2_41 ;
                break;
            case "A0S2":
                tile =A0_S2_42 ;
                break;
            case "P0O2":
                tile =P0_O2_43 ;
                break;
            case "S0O2":
                tile =S0_O2_44 ;     
                break;
            case "I2P0":
                tile =I2_P0_45 ;
                break;
            case "O0I2":
                if(bag.contains(O0_I2_46)){
                    tile =O0_I2_46 ;}
                else{
                    tile = O0_I2_47;
                }  break;
            case "P0I3":
                tile =P0_I3_48 ;
                break;
            default:
                break;
        }
         return tile;
    }
}
