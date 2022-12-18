
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> chanceList1 = new ArrayList<String>();
        ArrayList<String> communityChestList1 = new ArrayList<String>();
        Properties[] propertieslist = new Properties[28];
        for (int i=0;i<28;i++){
            propertieslist[i] = new Properties();
        }
        PropertyJsonReader.PropertyJsonReader1(propertieslist);
        ListJsonReader.ListJsonReader1(chanceList1,communityChestList1);
        Squares.addOtherSquares();
        People[] peoplelist= new People[3];
        peoplelist[0] = new People();
        peoplelist[1] = new People();
        peoplelist[2] = new People();
        People.addPeople(peoplelist);
        execute.executeProgram(peoplelist,propertieslist,chanceList1,communityChestList1);
        }
    }

