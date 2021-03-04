package logic;

/**
 * this class is responsible for type. We have two enum first one create enum with two parameter,
 * symbol and type of cards. the second enum just stores the type of cards. We have eight types of cards.
 * 
 * @author kobra
 */
public enum Cards {

   
    A0(0, Type.Amusment),   A1(1, Type.Amusment),   A2(2, Type.Amusment),   A3(3, Type.Amusment),
    H0(0, Type.Home),       H1(1, Type.Home),       H2(2, Type.Home),       H3(3, Type.Home),
    I0(0, Type.Industry),   I1(1, Type.Industry),   I2(2, Type.Industry),   I3(3, Type.Industry),
    O0(0, Type.Office),     O1(1, Type.Office),     O2(2, Type.Office),     O3(3, Type.Office),
    P0(0, Type.Park),       P1(1, Type.Park),       P2(2, Type.Park),       P3(3, Type.Park),
    S0(0, Type.Shopping),   S1(1, Type.Shopping),   S2(2, Type.Shopping),   S3(3, Type.Shopping),
    E(0, Type.Empty),       CC(0, Type.CityCenter);

    enum Type {
        Park, Home, Amusment, Shopping, Office, Industry, CityCenter, Empty;
    }

    private final int symboles;
    private final Type districtType;

    Cards(int symol, Type type) {
        this.symboles = symol;
        this.districtType = type;
        
    }

    /**
     * returns the symbol of every card.
     * @return 
     */
    public int getSymbol() {
        return this.symboles;
    }

    /**
     * return the type of cards
     * @return 
     */
    Type getType() {
        return this.districtType;
    }

}
