public class People {
    public int money;
    public int playersLocation = 1;
    public int playersjailtime=0;
    public int playerInFreeParking=0;
    public String playersProperties="";
    public static void addPeople(People[] peoplelist) {
        peoplelist[0].money=15000; //This is first player
        peoplelist[1].money=15000; //This is second player
        peoplelist[2].money=100000; //This is banker
    }
}
