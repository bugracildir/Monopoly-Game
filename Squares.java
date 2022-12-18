public class Squares {
    public int id;
    public String name;
    public static void addOtherSquares(){
        Squares[] otherSquaresList = new Squares[12];
        for (int i=0; i<12;i++) otherSquaresList[i] = new Squares();
        otherSquaresList[0].id=1;
        otherSquaresList[0].name="Go";
        otherSquaresList[1].id=3;
        otherSquaresList[1].name="Community Chest";
        otherSquaresList[2].id=5;
        otherSquaresList[2].name="Income Tax";
        otherSquaresList[3].id=8;
        otherSquaresList[3].name="Chance";
        otherSquaresList[4].id=11;
        otherSquaresList[4].name="Jail";
        otherSquaresList[5].id=18;
        otherSquaresList[5].name="Community Chest";
        otherSquaresList[6].id=21;
        otherSquaresList[6].name="Free Parking";
        otherSquaresList[7].id=23;
        otherSquaresList[7].name="Chance";
        otherSquaresList[0].id=31;
        otherSquaresList[0].name="Go to Jail";
        otherSquaresList[0].id=34;
        otherSquaresList[0].name="Community Chest";
        otherSquaresList[0].id=37;
        otherSquaresList[0].name="Chance";
        otherSquaresList[0].id=39;
        otherSquaresList[0].name="SuperTax";
    }
}
