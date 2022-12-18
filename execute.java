import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class execute {
    public static void show(People[] peoplelist, FileWriter myWriter) throws IOException { //This function execute when thereis show() command
        String properties1=peoplelist[0].playersProperties;
        String properties2=peoplelist[1].playersProperties;
        if (properties1.length()>0){
            properties1=properties1.substring(0,properties1.length()-1);
        }
        if (properties2.length()>0){
            properties2=properties2.substring(0,properties2.length()-1);
        }
        myWriter.write("-------------------------------------------------------------------------------------------------------------------------" + "\n");
        myWriter.write("Player 1" + "\t" + peoplelist[0].money + "\t" + "have:" + "\t" + properties1 + "\n");
        myWriter.write("Player 2" + "\t" + peoplelist[1].money + "\t" + "have:" + "\t" + properties2+ "\n");
        myWriter.write("Banker" + "\t" + peoplelist[2].money + "\n");
        if (peoplelist[0].money > peoplelist[1].money) {
            myWriter.write("Winner Player 1" + "\n");
        } else if (peoplelist[0].money < peoplelist[1].money) {
            myWriter.write("Winner Player 2" + "\n");
        }
        myWriter.write("-------------------------------------------------------------------------------------------------------------------------" + "\n");
    }
    public static void showIfMoneyZero(People[] peoplelist, FileWriter myWriter,int player1money,int player2money, int bankermoney) throws IOException { //This function will execute if player goes bankrupt
        String properties1=peoplelist[0].playersProperties;
        String properties2=peoplelist[1].playersProperties;
        if (properties1.length()>0){
            properties1=properties1.substring(0,properties1.length()-1);
        }
        if (properties2.length()>0){
            properties2=properties2.substring(0,properties2.length()-1);
        }
        myWriter.write("-------------------------------------------------------------------------------------------------------------------------" + "\n");
        myWriter.write("Player 1" + "\t" + player1money + "\t" + "have:" + "\t" + properties1 + "\n");
        myWriter.write("Player 2" + "\t" + player2money + "\t" + "have:" + "\t" + properties2+ "\n");
        myWriter.write("Banker" + "\t" + peoplelist[2].money + "\n");
        if (peoplelist[0].money > peoplelist[1].money) {
            myWriter.write("Winner Player 1" + "\n");
        } else if (peoplelist[0].money < peoplelist[1].money) {
            myWriter.write("Winner Player 2" + "\n");
        }
        myWriter.write("-------------------------------------------------------------------------------------------------------------------------" + "\n");
    }
    //This function will check if player goes bankrupt
    public static void checkIfHasMoney(People[] peoplelist, FileWriter myWriter, String[] my_list, int a, int b, int player1money, int player2money, int bankermoney, Scanner scancommand) throws IOException {
        if ((peoplelist[a].money < 0) || (peoplelist[b].money < 0)) {
            if (peoplelist[a].money < 0) {
                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" +player1money + "\t"
                        + player2money+ "\t" + "Player " + (a+1) + " goes bankrupt " + "\n");
                while (scancommand.hasNext()) {
                    scancommand.nextLine();
                }
                execute.showIfMoneyZero(peoplelist, myWriter, player1money, player2money,bankermoney);
            }
            else if (peoplelist[b].money < 0) {
                myWriter.write("Player " + (b+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + player1money + "\t"
                        + player2money + "\t" + "Player " + (b+1) + " goes bankrupt " + "\n");
                while (scancommand.hasNext()) {
                    scancommand.nextLine();
                }
                execute.showIfMoneyZero(peoplelist, myWriter, player1money, player2money,bankermoney);
            }
        }
    }

    //Tihs function will read the commands and execute them
    public static void executeProgram(People[] peoplelist, Properties[] propertiesList, ArrayList<String> chanceList1, ArrayList<String> communityChestList1) throws IOException {
        File command = new File("command.txt");
        Scanner scancommand = new Scanner(command);
        File output = new File("output.txt"); //Creating output file
        FileWriter myWriter = new FileWriter(output);
        int c = 0;//If  any player goes bankrupt we will understand it if c ==1

        while (scancommand.hasNext()) {
            int player1money=peoplelist[0].money; //This is for remember the money if player goes bankrupt
            int player2money= peoplelist[1].money;//This is for remember the money if player goes bankrupt
            int bankermoney= peoplelist[2].money;//This is for remember the money if player goes bankrupt
            String commandline = scancommand.nextLine();
            if (c == 1) { //If  any player goes bankrupt we will understand it if c ==1
                break;
            }
            if (commandline.equals("show()")) {
                execute.show(peoplelist, myWriter);
            }
            String[] my_list = commandline.split(";");
            int a=0,b=0; //When command line stars with Player 1 a will be 0 and b 1 when command line starts with Player 2 a will be 1 and b 0
            if (my_list.length == 2) {
                if (my_list[0].equals("Player 1" )) {
                    b = 1;
                }
                else if (my_list[0].equals("Player 2")){
                    a = 1;
                }

                    if ((peoplelist[a].playersjailtime == 0) && (peoplelist[a].playerInFreeParking == 0)) { //This will check if player in jail or free parking
                        String playeraProperties = peoplelist[a].playersProperties;
                        String playerbProperties = peoplelist[b].playersProperties;
                        peoplelist[a].playersLocation += Integer.parseInt(my_list[1]);
                        if (peoplelist[a].playersLocation > 40) {
                            peoplelist[a].playersLocation -= 40;
                            peoplelist[a].money += 200;
                            peoplelist[2].money -= 200;
                        }
                        if (peoplelist[a].playersLocation==-1){
                            peoplelist[a].playersLocation=40;
                        }
                        int currentLocation = peoplelist[a].playersLocation;
                        //If player in a property following code will execute
                        if ((currentLocation == 2) || (currentLocation == 4) || (currentLocation == 7) || (currentLocation == 9) || (currentLocation == 10) ||
                                (currentLocation == 12) || (currentLocation == 14) || (currentLocation == 15) || (currentLocation == 17) || (currentLocation == 19) ||
                                (currentLocation == 20) || (currentLocation == 22) || (currentLocation == 24) || (currentLocation == 25) || (currentLocation == 27) ||
                                (currentLocation == 28) || (currentLocation == 30) || (currentLocation == 32) || (currentLocation == 33) || (currentLocation == 35) ||
                                (currentLocation == 38) || (currentLocation == 40) || (currentLocation == 6) || (currentLocation == 16) || (currentLocation == 26) ||
                                (currentLocation == 36) || (currentLocation == 13) || (currentLocation == 29)) {
                            for (int i = 0; i < 27; i++) {
                                if (propertiesList[i].id == currentLocation) {
                                    if ((!(playeraProperties.contains(propertiesList[i].name))) && (!(playerbProperties.contains(propertiesList[i].name)))) {
                                        if (peoplelist[a].money >= propertiesList[i].cost) {
                                            peoplelist[a].money = peoplelist[a].money - propertiesList[i].cost;
                                            peoplelist[2].money = peoplelist[2].money + propertiesList[i].cost;
                                            peoplelist[a].playersProperties += (propertiesList[i].name)+",";
                                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " bought " + propertiesList[i].name + "\n");
                                        } else if (peoplelist[a].money < propertiesList[i].cost) {
                                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " goes bankrupt " + "\n");
                                            while (scancommand.hasNext()) {
                                                scancommand.nextLine();
                                            }
                                            execute.showIfMoneyZero(peoplelist, myWriter, player1money, player2money,bankermoney);
                                            c++;
                                            break;

                                        }
                                    } else if (playerbProperties.contains(propertiesList[i].name)) {
                                        if ((currentLocation == 2) || (currentLocation == 4) || (currentLocation == 7) || (currentLocation == 9) || (currentLocation == 10) ||
                                                (currentLocation == 12) || (currentLocation == 14) || (currentLocation == 15) || (currentLocation == 17) || (currentLocation == 19) ||
                                                (currentLocation == 20) || (currentLocation == 22) || (currentLocation == 24) || (currentLocation == 25) || (currentLocation == 27) ||
                                                (currentLocation == 28) || (currentLocation == 30) || (currentLocation == 32) || (currentLocation == 33) || (currentLocation == 35) ||
                                                (currentLocation == 38) || (currentLocation == 40)) {
                                            if (propertiesList[i].cost < 2001) {
                                                peoplelist[a].money -= ((propertiesList[i].cost * 40) / 100);
                                                peoplelist[b].money += ((propertiesList[i].cost * 40) / 100);
                                            } else if ((propertiesList[i].cost < 3001) && (propertiesList[i].cost > 2000)) {
                                                peoplelist[a].money -= ((propertiesList[i].cost * 30) / 100);
                                                peoplelist[b].money += ((propertiesList[i].cost * 30) / 100);
                                            } else if ((propertiesList[i].cost < 4001) && (3000 < propertiesList[i].cost)) {
                                                peoplelist[a].money -= ((propertiesList[i].cost * 35) / 100);
                                                peoplelist[b].money += ((propertiesList[i].cost * 35) / 100);
                                            }
                                            if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " paid rent for " + propertiesList[i].name + "\n");
                                            }
                                            execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);
                                        } else if ((currentLocation == 6) || (currentLocation == 16) || (currentLocation == 26) || (currentLocation == 36)) {
                                            peoplelist[a].money -= Integer.parseInt(my_list[1]) * 4;
                                            peoplelist[b].money += Integer.parseInt(my_list[1]) * 4;
                                            if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " paid rent for " + propertiesList[i].name + "\n");
                                            }
                                            execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);
                                        } else if ((currentLocation == 13) || (currentLocation == 29)) {
                                            int numberOfRailRoadsOtherPlayerHas = 0;
                                            if (playerbProperties.contains("Electric Company")) {
                                                numberOfRailRoadsOtherPlayerHas++;
                                            }
                                            if (playerbProperties.contains("Water Works")) {
                                                numberOfRailRoadsOtherPlayerHas++;
                                            }
                                            peoplelist[a].money -= numberOfRailRoadsOtherPlayerHas * 25;
                                            peoplelist[b].money += numberOfRailRoadsOtherPlayerHas * 25;
                                            if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                                        + peoplelist[1].money + "\t" +"Player " + (a+1) + " paid rent for " + propertiesList[i].name + "\n");
                                            }
                                            execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);
                                        }
                                    } else if (playeraProperties.contains(propertiesList[i].name)) {
                                        myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                                + peoplelist[1].money + "\t" + "Player " + (a+1) + " has " + propertiesList[i].name + "\n");
                                    }
                                }
                            }
                            //If player in a chance following code will execute
                        } else if ((currentLocation == 8) || (currentLocation == 23) || (currentLocation == 37)) {
                            String command1 = chanceList1.get(0);
                            chanceList1.remove(0);
                            if (command1.equals("Advance to Go (Collect $200)")) {
                                peoplelist[a].money += 200;
                                peoplelist[2].money -= 200;
                                peoplelist[a].playersLocation = 1;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Advance to Go (Collect $200)" + "\n");

                            } else if (command1.equals("Advance to Leicester Square")) {
                                if (currentLocation==37){
                                    peoplelist[a].money+=200;
                                    peoplelist[2].money-=200;
                                }
                                peoplelist[a].playersLocation = 27;
                                if (playeraProperties.contains("Leicester Square")) {
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t" + peoplelist[1].money + "\t"
                                            +"Player " + (a+1) +" draw Advance to Leicester Square Player 1 has Leicester Square" + "\n");
                                } else if (playerbProperties.contains("Leicester Square")) {
                                    peoplelist[a].money -= ((propertiesList[14].cost * 30) / 100);
                                    peoplelist[b].money += ((propertiesList[14].cost * 30) / 100);
                                    if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                        myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t" + peoplelist[1].money + "\t"
                                                + "Player " + (a+1) + " draw Advance to Leicester Square Player 1 paid rent for Leicester Square" + "\n");
                                    }
                                    execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);

                                } else if ((!(playeraProperties.contains("Leicester Square"))) && (!(playerbProperties.contains("Leicester Square")))) {
                                    peoplelist[a].money -= propertiesList[14].cost;
                                    peoplelist[2].money += propertiesList[14].cost;
                                    peoplelist[a].playersProperties += "Leicester Square,";
                                    if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                        myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t" + peoplelist[1].money + "\t"
                                                +"Player " + (a+1) + " draw Advance to Leicester Square Player 1 has  bought Leicester Square" + "\n");
                                    }
                                    execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);
                                }

                            } else if (command1.equals("Go back 3 spaces")) {
                                peoplelist[a].playersLocation -= 3;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) +  " draw -Go back 3 spaces" + "\n");
                                if (peoplelist[a].playersLocation==5){
                                    peoplelist[a].money -= 100;
                                    peoplelist[2].money += 100;
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) +  " draw -Go back 3 spaces -Player" +(a+1)+ "paid tax" + "\n");
                                }



                            } else if (command1.equals("Pay poor tax of $15")) {
                                peoplelist[a].money -= 15;
                                peoplelist[2].money += 15;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) +" draw -Pay poor tax of $15" + "\n");

                            } else if (command1.equals("Your building loan matures - collect $150")) {
                                peoplelist[a].money += 150;
                                peoplelist[2].money -= 150;
                                if ((peoplelist[a].money>0) &&(peoplelist[b].money>0)){
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) +" draw -Pay poor tax of $15" + "\n");
                                }
                                execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);

                            } else if (command1.equals("You have won a crossword competition - collect $100 " + "\n")) {
                                peoplelist[a].money += 100;
                                peoplelist[2].money -= 100;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw -You have won a crossword competition" + "\n");

                            }

                            //If player in a jail following code will execute
                        } else if (currentLocation == 11) {
                            peoplelist[a].playersjailtime += 3;
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " went to jail" + "\n");
                            //If player in a tax square following code will execute
                        } else if ((currentLocation == 5) || (currentLocation == 39)) {
                            peoplelist[a].money -= 100;
                            peoplelist[2].money += 100;
                            if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " paid tax" + "\n");
                            }
                            execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);
                            //If player in a free parking following code will execute
                        } else if (currentLocation == 21) {
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " is in Free Parking" + "\n");
                            peoplelist[a].playerInFreeParking += 1;
                            //If player in a go to jail square following code will execute
                        } else if (currentLocation == 31) {
                            peoplelist[a].playersLocation = 11;
                            peoplelist[a].playersjailtime += 3;
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " went to jail" + "\n");
                            //If player in a community chance square following code will execute
                        } else if ((currentLocation == 3) || (currentLocation == 18) || (currentLocation == 34)) {
                            String command1 = communityChestList1.get(0);
                            communityChestList1.remove(0);
                            if (command1.equals("Advance to Go (Collect $200)")) {
                                peoplelist[a].money += 200;
                                peoplelist[a].playersLocation = 1;
                                peoplelist[2].money -= 200;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -advance to go" + "\n");
                            } else if (command1.equals("Bank error in your favor - collect $75")) {
                                peoplelist[a].money += 75;
                                peoplelist[2].money -= 75;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Bank error in your favor" + "\n");

                            } else if (command1.equals("Doctor's fees - Pay $50")) {
                                peoplelist[a].money -= 50;
                                peoplelist[2].money += 50;
                                if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Doctor's fees - Pay $50" + "\n");
                                }
                                execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);

                            } else if (command1.equals("It is your birthday Collect $10 from each player")) {
                                peoplelist[a].money += 10;
                                peoplelist[b].money += 10;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -It is your birthday Collect $10 from each player" + "\n");

                            } else if (command1.equals("Grand Opera Night - collect $50 from every player for opening night seats")) {
                                peoplelist[a].money += 50;
                                peoplelist[b].money += 50;
                                if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Grand Opera Night" + "\n");
                                }
                                execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);

                            } else if (command1.equals("Income Tax refund - collect $20")) {
                                peoplelist[a].money += 20;
                                peoplelist[2].money -= 20;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Income Tax refund" + "\n");

                            } else if (command1.equals("Life Insurance Matures - collect $100")) {
                                peoplelist[a].money += 100;
                                peoplelist[2].money = 100;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Life Insurance Matures" + "\n");

                            } else if (command1.equals("Pay Hospital Fees of $100")) {
                                peoplelist[a].money -= 100;
                                peoplelist[2].money += 100;
                                if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Pay Hospital Fees of $100" + "\n");
                                }
                                execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);


                            } else if (command1.equals("Pay School Fees of $50")) {
                                peoplelist[a].money -= 50;
                                peoplelist[2].money += 50;
                                if ((peoplelist[a].money>0)&&(peoplelist[b].money>0)){
                                    myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" +peoplelist[0].money + "\t"
                                            + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -Pay School Fees of $50" + "\n");
                                }
                                execute.checkIfHasMoney(peoplelist, myWriter, my_list, a, b, player1money, player2money, bankermoney, scancommand);


                            } else if (command1.equals("You inherit $100")) {
                                peoplelist[a].money += 100;
                                peoplelist[2].money -= 100;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -You inherit $100" + "\n");

                            } else if (command1.equals("From sale of stock you get $50")) {
                                peoplelist[a].money += 50;
                                peoplelist[2].money -= 50;
                                myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                        + peoplelist[1].money + "\t" + "Player " + (a+1) + " draw Community Chest -From sale of stock you get $50" + "\n");

                            }
                        }
                    }
                    //If player in a jail following code will execute
                    else if (!(peoplelist[a].playersjailtime==0)){
                        if (peoplelist[a].playersjailtime==3){
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " in jail (count=1)" + "\n");
                            peoplelist[a].playersjailtime-=1;
                        }
                        else if (peoplelist[a].playersjailtime==2){
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " in jail (count=2)" + "\n");
                            peoplelist[a].playersjailtime-=1;
                        }
                        else if (peoplelist[a].playersjailtime==1){
                            myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                    + peoplelist[1].money + "\t" + "Player " + (a+1) + " in jail (count=3)" + "\n");
                            peoplelist[a].playersjailtime-=1;
                        }

                    }
                    //If player in a free parking square following code will execute
                    else if (peoplelist[a].playerInFreeParking==1){
                        myWriter.write("Player " + (a+1) + "\t" + my_list[1] + "\t" + peoplelist[a].playersLocation + "\t" + peoplelist[0].money + "\t"
                                + peoplelist[1].money + "\t" + "Player " + (a+1) + " is in Free Parking" + "\n");
                        peoplelist[a].playerInFreeParking=0;
                    }
                }
            }
        if ((peoplelist[0].money>0)&&(peoplelist[1].money>0)){
            execute.show(peoplelist, myWriter);
        }
            myWriter.close();
        }
    }
