/**
 * Program Name: ICS4UCulm
 * Programmer: Moses
 * Date: November 8, 2020
 * Description: Program for culminating
 **/
package ics4uculm;

//Imports
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class ICS4UCulm {

    //Declaring and initializing global scanners
    public static Scanner scanN = new Scanner(System.in);
    public static Scanner scanS = new Scanner(System.in);
    
    public static void main(String[] args) 
            throws IOException, InterruptedException {
        //Declaring file route, and file reader for players
        File userData = new File("C:\\Users\\matth\\Documents\\"
                + "NetBeansTxtFiles\\GameData.txt");
        Scanner readUser = new Scanner(userData);
        
        //Declaring file route, and file reader for enemies
        File enemyData = new File("C:\\Users\\matth\\Documents\\"
                + "NetBeansTxtFiles\\EnemyData.txt");
        Scanner readEnemy = new Scanner(enemyData);
        
        //Declaring and initializing variables
        ArrayList<Character> characters = new ArrayList<>(5);
        ArrayList<Character> enemies = new ArrayList<>(5);
        Random randomNumbers = new Random();
        int input;
        int select = 0;
        int random = randomNumbers.nextInt(5);
        int fightAgain;
        
        //While loop that reads the text file and adds to the ArrayList
        while(readUser.hasNext()) {
            String user = readUser.nextLine();
            String[] saves = user.split(" ");
            characters.add(new Character(saves[0],
                    Integer.parseInt(saves[1]),
                    Integer.parseInt(saves[2]),
                    Integer.parseInt(saves[3])));
        }
        
        //While loop that reads the text file and adds to the ArrayList
        while(readEnemy.hasNext()) {
            String enemy = readEnemy.nextLine();
            String[] data = enemy.split(" ");
            enemies.add(new Character(data[0],
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3])));
        }
        
        //Printing out banner for game
        Thread.sleep(300);
        System.out.println(">->->->->->->->->->->->->->->->->->->->->->->->->");
        Thread.sleep(300);
        System.out.println(">----------------------------------------------->");
        Thread.sleep(300);
        System.out.println(">----------------------------------------------->");
        Thread.sleep(300);
        System.out.println(">----------- A R E N A    S H I F T ------------>");
        Thread.sleep(300);
        System.out.println(">----------------------------------------------->");
        Thread.sleep(300);
        System.out.println(">----------------------------------------------->");
        Thread.sleep(300);
        System.out.println(">->->->->->->->->->->->->->->->->->->->->->->->->\n");
        
        //Print out for story
        Thread.sleep(300);
        System.out.println("You are a gladiator in the arena.");
        Thread.sleep(300);
        System.out.println("You will never be able to get out.");
        Thread.sleep(300);
        System.out.println("Unless you fight!\n");
        
        //Do while loop with error trap
        do{
            System.out.println("Welcome to Arena Shift, Gladiator!");
            System.out.println("MENU\n1) Create Character\n2) Select Character\n"
                    + "3) Exit");
            input = scanN.nextInt();

            //If the user has no characters
            if (input == 2 && characters.isEmpty()) {
                Thread.sleep(300);
                System.out.println("Please create a character.\n");
            }

            //Switch for character creation and selection
            switch (input) {
                //Create character
                case 1:{
                    Thread.sleep(300);
                    //If user has less than five characters
                    if (characters.size() < 5) {
                        characters.add(getData());
                        select = (characters.size() - 1);
                    }
                    //When user has more than five characters
                    else{
                        System.out.println("You have too many characters,"
                                + " please wipe GameData.txt.\n");
                    }
                    break;
                }
                //Select character
                case 2:{
                    //If user has no characters
                    if (characters.isEmpty()) {
                        break;
                    } 
                    //When user has characters
                    else{
                        Thread.sleep(300);
                        select = selectChar();
                    }
                    break;
                }
                //Exit
                case 3:{
                    Thread.sleep(300);
                    System.out.println("\nUntil next time, Gladiator.");
                    System.exit(0);
                }
                //Default error trap
                default:{
                    Thread.sleep(300);
                    System.out.println("This input is invalid, please try again."
                            + "\n");
                    break;
                }
            }
        
        } while(input < 1 || input > 3 || input == 2 && characters.isEmpty()
                || characters.size() == 5 && input == 1);
        //End of do while loop
        
        /*Declares and initializes new Character class object to be parametered
        in the Character class*/
        Character player = characters.get(select);
        
        //Character stat sorter
        Character.sortedStats(player);
        
        //Do while loop with error trap
        do{
            /*Random number generator chooses the enemy to be put into a 
            new character class object to be parametered in the 
            character class */
            random = randomNumbers.nextInt(5);
            Character enemy = enemies.get(random);

            //Calling fight method of Character class
            Character.fight(player, enemy);

            Thread.sleep(300);
            System.out.println("\nDo you want to fight again?");
            System.out.println("1) Yes\n2) No");
            fightAgain = scanN.nextInt();

            //If user inputs something invalid
            if(fightAgain > 2 && fightAgain < 1) {
                Thread.sleep(300);
                System.out.println("This is an invalid choice,"
                        + " please try again.");
            }
            
        } while(fightAgain != 2 || fightAgain > 2 && fightAgain < 1);
        //End of do while loop
        
        //Overwriting text file with save and print writer after user plays
        FileWriter saveWriter = new FileWriter("C:\\Users\\matth\\Documents\\"
                + "NetBeansTxtFiles\\GameData.txt", false);
        PrintWriter saveGame = new PrintWriter(saveWriter);
        
        //For loop to overwrite the text file with the arraylist
        for (int i = 0; i < characters.size(); i++) {
        saveGame.print(characters.get(i).saveData());
        }
        
        //Closes text file
        saveGame.close();
        
        Thread.sleep(300);
        System.out.println("\nSee you soon, Gladiator...");
    }
    //End of main
    
    /**
     * Method Name: getData
     * Method Description: Lets user make new character slot
     * @return new Character - returns character with name 
       and stats for arraylist
     * @throws IOException 
     * @throws InterruptedException
     **/
    public static Character getData() 
            throws IOException, InterruptedException {
        //Declaring and initializing variables
        int sp = 10;
        int input;
        String nameSet;
        int STRAdd = 0;
        int INTAdd = 0;
        int DEXAdd = 0;
        
        Thread.sleep(300);
        System.out.println("\nGladiator, what is thy name?");
        nameSet = scanS.nextLine();
        
        //Do while loop containing error trap
        do{
            Thread.sleep(300);
            System.out.println("\nWelcome, " + nameSet + ". You have "
                    + sp + " stat points!");
            Thread.sleep(300);
            System.out.println("How would you like to use them?");
            Thread.sleep(300);  
            System.out.println("1) Strength\n2) Intelligence\n3) Dexterity\n");
            input = scanN.nextInt();

            //If user has no stat points
            if (sp == 0) {
                Thread.sleep(300);
                System.out.println("You are out of stat points.");
                break;
            }
        
            //Switch statement for stat allocation
            switch (input) {
                //Adding points to strength
                case 1:{
                    //Do while loop containing error trap
                    do{
                        Thread.sleep(300);
                        System.out.println("How many stat points would"
                                + " you like to put" + " into strength?"
                                + " (Remaining: " + sp + ")");
                        STRAdd = scanN.nextInt();
                        sp -= STRAdd;

                        //If user doesnt have enough stat points
                        if (STRAdd > sp && STRAdd < 1) {
                            Thread.sleep(300);
                            System.out.println("You do not have enough"
                                    + " stat points.");
                        }

                    } while(STRAdd > sp && STRAdd < 1);
                    //End of do while loop
                    break;
                }
                //Adding points to intelligence
                case 2:{
                    //Do while loop containing error trap
                    do{
                        Thread.sleep(300);
                        System.out.println("How many stat points would"
                                + " you like to put" + " into intelligence?" 
                                + " (Remaining: " + sp + ")");
                        INTAdd = scanN.nextInt();
                        sp -= INTAdd;

                        //If user doesnt have enough stat points
                        if (INTAdd > sp && INTAdd < 1) {
                            Thread.sleep(300);
                            System.out.println("You do not have enough"
                                    + " stat points.");
                        }

                    } while(INTAdd > sp && INTAdd < 1);
                    //End of do while loop
                    break;
                }
                //Adding points to dexterity
                case 3:{
                    //Do while loop containing error trap
                    do {
                        Thread.sleep(300);
                        System.out.println("How many stat points would"
                                + " you like to put" + " into dexterity?" 
                                + " (Remaining: " + sp + ")");
                        DEXAdd = scanN.nextInt();
                        sp -= DEXAdd;

                        //If user doesnt have enough stat points
                        if (DEXAdd > sp && DEXAdd < 1) {
                            Thread.sleep(300);
                            System.out.println("You do not have enough"
                                    + " stat points.");
                        }

                    } while (DEXAdd > sp && DEXAdd < 1);
                    //End of do while loop
                    break;
                }
                //Default error trap
                default:{
                    Thread.sleep(300);
                    System.out.println("This input is invalid, please"
                            + " try again.");
                }
            }

        } while (input > 3 && input < 1 || sp != 0);
        //End of do while loop
        
        //Declaring file writer route and print writer
        FileWriter newWriter = new FileWriter("C:\\Users\\matth\\Documents\\"
                + "NetBeansTxtFiles\\GameData.txt", true);
        PrintWriter newFile = new PrintWriter(newWriter);
        
        //Prints out character information into text file 
        newFile.print(nameSet + " " + STRAdd + " " + INTAdd + " "
                + DEXAdd + "\n");
        
        //Closing text file
        newFile.close();
        
        //Returns new character object
        return new Character(nameSet, STRAdd, INTAdd, DEXAdd);
    }
    //End of getData
    
    /**
     * Method Name: selectChar
     * Method Description: Lets user choose character slots already made
     * @return select - Selected character
     * @throws IOException 
     * @throws InterruptedException
     **/
    public static int selectChar() throws IOException, InterruptedException {
        //Declaring and initializing variables
        int select;
        int count = 0;
        
        //Do while loop with error trap
        do {
            Thread.sleep(300);
            System.out.println("\nChoose your player:");

            //File route and file reader for character slots
            File myFile = new File("C:\\Users\\matth\\Documents\\"
                    + "NetBeansTxtFiles\\GameData.txt");
            Scanner readFile = new Scanner(myFile);

            //While loop that reads text file and prints it out for user selection
            while (readFile.hasNext()) {
                String line = readFile.nextLine();
                String[] saves = line.split(" ");
                Thread.sleep(300);
                System.out.println(count + ") " + saves[0]);
                count++;
            }
            select = scanN.nextInt();
            
            //If user selects a user slots that doesnt exist
            if(select < 0 && select > count) {
                System.out.println("Invalid character choice,"
                        + " please try again");
            }
            
        } while(select < 0 && select > count);
        //End of do while loop
        
        //Returns selected character
        return select;
    }
    //End of selectChar
}
//End of program