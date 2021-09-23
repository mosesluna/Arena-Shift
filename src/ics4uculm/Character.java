/**
 * Class Name: Character
 * Programmer: Moses
 * Date: November 8, 2020
 * Description: This is the player's character
 **/
package ics4uculm;

//Imports
import java.util.Scanner;

public class Character {
    
    //Declaring and initializing global scanners
    public static Scanner scanN = new Scanner(System.in);
    
    //Fields
    private String name;
    private int health;
    private int mana;
    private int ATK;
    private int DEF;
    private int HEAL;
    private int STR;
    private int INT;
    private int DEX;
    
    //Arg Constructor
    public Character(String n, int s, int i, int d) {
        name = n;
        health = 100;
        mana = 25;
        ATK = (int)((Math.random() * 13) + 10);
        DEF = (int)((Math.random() * 15) + 5);
        HEAL = (int)((Math.random() * 12) + 10);
        STR = 1 + s;
        INT = 1 + i;
        DEX = 1 + d;
    }
    
    //Accessors
    public String getName() {
        return name;
    }
    
    public int getStrength() {
        return STR;
    }
    
    public int getIntelligence() {
        return INT;
    }
    
    public int getDexterity() {
        return DEX;
    }
    
    //Mutators  
    public void setName(String n) {
        name = n;
    }   
    
    public void setStrength(int s) {
        STR = s;
    }
    
    public void setIntelligence(int i) {
        INT = i;
    }
    
    public void setDexterity(int d) {
        DEX = d;
    }
    
    /**
     * Method Name: toString
     * Description: Creates a string representing object state  
     * @return state of the object
     **/
    public String toString() {
        //Declaring and initializing string
        String str = name + "\nHP: " + health + "\nMana: " + mana 
                + "\nATK: " + ATK + "\nDEF: " + DEF + "\nSTR: " + STR
                + "\nINT: " + INT + "\nDEX: " + DEX;
        
        //Returns string
        return str;
    }
    //End of toString
    
    /**
     * Method Name: saveData
     * Description: Creates a string to be printed into text file
     * @return data for character save
     **/
    public String saveData() {
        //Declaring and initializing string
        String str = name + " " + STR + " " + INT + " " + DEX;
        
        //Returns string
        return str;
    }
    //End of saveData
    
    /**
     * Method Name: sortedStats
     * Description: Creates a string to be printed into text file
     * @param player - user's character
     * @throws InterruptedException
     **/
    public static void sortedStats(Character player) 
            throws InterruptedException {
        //Declaring and initializing variables
        String[] statNames = {"HP", "MP", "ATK", "DEF", "STR", "INT", "DEX"};
        String statName;
        int minValue;
        int minIndex;
        int[] statNums = new int[7];
        statNums[0] = player.health;
        statNums[1] = player.mana;
        statNums[2] = player.ATK;
        statNums[3] = player.DEF;
        statNums[4] = player.STR;
        statNums[5] = player.INT;
        statNums[6] = player.DEX;
        
        Thread.sleep(300);
        System.out.println("\nYOUR STATS SORTED FROM LOWEST TO HIGHEST:");

         /* Uses selection sort with 2 parallel arrays to sort character stats 
        from least to greatest */
        for (int i = 0; i < statNames.length - 1; i++) {
            //Variable i becomes the starting point of the sort
            minValue = statNums[i];
            minIndex = i;
            statName = statNames[i];
            
            for (int j = i + 1; j < statNames.length; j++) {
                if(statNums[j] < minValue) {
                    //If array at j is less then minValue they swap
                    minValue = statNums[j];
                    minIndex = j;
                    statName = statNames[j];
                }
            }
            //Variables switch with index of lowest value
            statNums[minIndex] = statNums[i];
            statNums[i] = minValue;
            statNames[minIndex] = statNames[i];
            statNames[i] = statName;
        }
        
        //For loop to print out the sorted stats
        for (int i = 0; i < statNames.length; i++) {
            Thread.sleep(300);
            System.out.println(statNames[i] + ": " + statNums[i]);
        }
    }
    //End of sortedStats
    
    /**
     * Method Name: fight
     * Description: Automates the fight between the player and the enemy
     * @param player - user's character
     * @param enemy - enemy fighting user
     * @throws InterruptedException
     **/
    public static void fight(Character player, Character enemy) 
            throws InterruptedException {
        //Declaring and initializing variables
        int input;
        int playerHP = player.health;
        int playerMP = player.mana;
        int enemyHP = enemy.health;
        int enemyMP = enemy.mana;
        int yesNo;

        Thread.sleep(300);
        System.out.println("\nYou have engaged in battle,"
                + " prepare to fight.");
        Thread.sleep(300);
        System.out.println("You are fighting a(n) "
                + enemy.name + "\n");
        
           //Do while loop with error trap
           do {
               //Nested do while loop with error trap
               do{
                   Thread.sleep(300);
                   System.out.println("YOUR TURN.\n");

                   Thread.sleep(300);
                   System.out.println(player.name + "'s Stats");
                   System.out.println("HP: " + player.health + "/" + playerHP
                           + "\nMP: " + player.mana + "/" + playerMP + "\n");

                   Thread.sleep(300);
                   System.out.println(enemy.name + "'s Stats");
                   System.out.println("HP: " + enemy.health + "/" + enemyHP
                           + "\nMP: " + enemy.mana + "/" + enemyMP + "\n");

                   Thread.sleep(300);
                   System.out.println("ACTIONS");
                   Thread.sleep(300);
                   System.out.println("1) Attack\n2) Defend"
                           + "\n3) Heal (Cost: 5 MP)");
                   input = scanN.nextInt();

                   //Switch statement for character actions
                   switch (input) {
                       //Attack
                       case 1: {
                           Thread.sleep(300);
                           //Randomized player attack
                           player.ATK = (int) ((Math.random() * 40) + 20);
                           //Strength determines if player does more damage
                           player.ATK *= (player.STR / 50 + 1);
                           //If enemy defence is more than player attack
                           if (enemy.DEF > player.ATK) {
                               player.ATK = 0;
                               break;
                           } 
                           //When enemy defence is less than player attack
                           else{
                               //When player attack enemy
                               enemy.health -= player.ATK;
                           }
                           System.out.println("\n" + player.name + " has hit "
                                   + enemy.name + " for " + player.ATK
                                   + " damage.\n");
                           break;
                       }
                       //Defend
                       case 2:{
                           Thread.sleep(300);
                           //Randomized player defence
                           player.DEF = (int) ((Math.random() * 20) + 5);
                           //Dexterity determines if player takes less damage
                           player.DEF *= (player.DEX / 50 + 1);
                           System.out.println("\n" + player.name
                                   + " is defending for " + player.DEF
                                   + " damage.\n");
                           break;
                       }
                       //Heal
                       case 3:{
                           Thread.sleep(300);
                           //If player has less than five mana and full health
                           if (player.mana < 5 || player.health == 100){
                               //Player does not get healed 
                               System.out.println(player.name
                                       + " is unable to heal.\n");
                           } 
                            /*When player has more than five mana 
                            and less than full health*/ 
                            else{
                               //Randomized player heal
                               player.HEAL = (int) ((Math.random() * 15) + 10);
                               //Intelligence determines if player gets more health
                               player.HEAL *= (player.INT / 50 + 1);
                               System.out.println("\n" + player.name
                                       + " is healing for " + player.HEAL
                                       + " health.\n");
                               //Player health gets increased by heal
                               player.health += player.HEAL;
                               //Player mana gets taken away
                               player.mana -= 5;
                           }
                           break;
                       }
                       //Default error trap
                       default:{
                           Thread.sleep(300);
                           System.out.println("This is not an action,"
                                   + " please try again.\n");
                           break;
                       }
                   }

               } while(input < 1 || input > 3 ||
                       player.mana < 5 || player.health == 100 && input == 3);
               //End of nested do while loop
               
               //Calling enemyAttack character class method
               enemyAttack(player, enemy, input);

               //If player's health runs out
               if (player.health <= 0){
                   player.health = 0;
                   Thread.sleep(300);
                   System.out.println("Gladiator, " + player.name
                           + " has been slain");
                   Thread.sleep(300);
                   System.out.println("GAME OVER...");
                   System.exit(0);
               } 
               //When player slays enemy, they gain a skill point
               else if (enemy.health <= 0){
                   enemy.health = 0;
                   Thread.sleep(300);
                   System.out.println(player.name
                           + " has slain a(n) " + enemy.name);
                   //Calling statUp character class method
                   statUp(player);
               }

        } while(player.health != 0 && enemy.health != 0);
        //End of do while loop

        //Resets enemy and player health and mana
        enemy.health = 100;
        enemy.mana = 25;
        player.health = 100;
        player.mana = 25;
           
        //Do while loop with error trap
         do{
            Thread.sleep(300);
            System.out.println("Do you want to play a game"
                    + " for an extra stat point?");
            Thread.sleep(300);
            System.out.println("1) Yes\n2) No");
            yesNo = scanN.nextInt();

            /*Switch statement if player wants an extra 
            stat point by playing a minigame*/
            switch (yesNo){
                //Yes
                case 1:{
                    Thread.sleep(300);
                    
                    //Player guesses a number for a stat point
                    int guess;
                    
                    //Do while loop with error trap
                    do{
                        System.out.println("Guess a number between (1-100)");
                        guess = scanN.nextInt();

                        /*If player guessed more than one hundred 
                        or less than one*/
                        if (guess > 100 || guess < 1){
                            Thread.sleep(300);
                            System.out.println("Invalid guess, try again.\n");
                        }
                        
                    } while(guess > 100 || guess < 1);
                    //End of do while
                    
                    //If player gets the number right
                    if(statGame(player, guess)) {
                        Thread.sleep(300);
                        System.out.println("You have guessed right!");
                        statUp(player);
                    }
                    //If player gets the number wrong
                    else{
                        Thread.sleep(300);
                        System.out.println("You have guessed wrong.");
                    }
                    break;
                }
                //No
                case 2:{
                    Thread.sleep(300);
                    break;
                }
                //Default error trap
                default:{
                    Thread.sleep(300);
                    System.out.println("This is an invalid choice,"
                            + " please try again\n");
                }
            }
        } while(yesNo < 1 || yesNo > 2);
        //End of do while loop
        
    }
    //End of fight
    
    /**
     * Method Name: enemyAttack
     * Description: Automates the enemy attack against the player
     * @param player - user's character
     * @param enemy - enemy fighting user
     * @param input - player input
     * @throws InterruptedException
     **/
    public static void enemyAttack(Character player, Character enemy, int input) 
            throws InterruptedException {
        //Declaring and initializing variables
        int enemyChoice = (int)((Math.random() * 3) + 1);
        
        Thread.sleep(300);
        //Do while loop without error trap
        do{
            System.out.println("ENEMY TURN.\n");
            
            //Switch for the enemies attack 
            switch (enemyChoice) {
                //Attack
                case 1:{
                    Thread.sleep(300);
                    //Randomized enemy attack
                    enemy.ATK = (int) ((Math.random() * 19) + 10);
                    //Strength determines if enemy deals more damage
                    enemy.ATK *= (enemy.STR / 50 + 1);
                    
                    /*If player chooses to defend and their defence is less than
                    enemies attack*/
                    if (input == 2 && player.DEF < enemy.ATK){
                        enemy.ATK -= player.DEF;
                        player.health -= enemy.ATK;
                        player.health += player.DEF;
                        
                        System.out.println(enemy.name + " has hit " + player.name
                                + " for " + enemy.ATK + " damage.\n");
                    }
                    /*If player chooses to defend and their defence is more
                    than enemy's attack*/
                    else if(input == 2 && player.DEF >= enemy.ATK) {
                        enemy.ATK = 0;
                        
                        System.out.println(player.name + " has blocked " 
                                + enemy.name + "'s attack.\n");
                    }
                    //When player doesn't choose to defend
                    else {
                        player.health -= enemy.ATK;
                        
                        System.out.println(enemy.name + " has hit " + player.name
                                + " for " + enemy.ATK + " damage.\n");
                    }
                    break;
                }
                //Defence
                case 2:{
                    Thread.sleep(300);
                    //Randomized enemy defence
                    enemy.DEF = (int) ((Math.random() * 15) + 5);
                    //Dexterity determines if enemy takes less damage
                    enemy.DEF *= (enemy.DEX / 50 + 1);
                    
                    //If player chooses to attack then it triggers this statement
                    if(input == 1) {
                        /*If player attack is more than enemy defence,
                        it "defends"*/
                        if(player.ATK > enemy.DEF){
                            enemy.health += enemy.DEF;
                            
                            System.out.println(enemy.name + " is defending for "
                            + enemy.DEF + " damage.\n");
                        }
                        /*If the attack is less than or equal to player damage
                        then it gives zero damage to the enemy*/
                        else {
                            enemy.health += player.ATK;
                            
                            System.out.println(enemy.name + " has blocked " 
                                + player.name + "'s attack.\n");
                        }
                    }
                    break;
                }
                //Heal
                case 3:{
                    Thread.sleep(300);
                    //If enemy has less than five mana and full health
                    if (enemy.mana < 5 || enemy.health == 100) {
                        //enemy does not get healed 
                        System.out.println(enemy.name
                                + " is unable to heal.\n");
                    } 
                    /*When enemy has more than five mana 
                    and less than full health*/ 
                    else{
                        //Randomized enemy heal
                        enemy.HEAL = (int) ((Math.random() * 12) + 10);
                        //Intelligence determines if enemy heals more health
                        enemy.HEAL *= (enemy.INT / 50 + 1);
                        System.out.println(enemy.name
                                + " is healing for " + enemy.HEAL
                                + " health.\n");
                        //Enemy health gets increased by heal
                        enemy.health += enemy.HEAL;
                        enemy.mana -= 5;
                    }
                    break;
                }
                default:{
                    System.out.println(enemy.name + " did nothing.");
                    break;
                }
            }
        } while(enemy.mana < 5 || enemy.health == 100 && enemyChoice == 2);
        //End of do while loop
    }
    //End of enemyAttack
    
    /**
     * Method Name: statUp
     * Description: When player gains a stat point by fighting or minigame
     * @param player - user's character
     * @throws InterruptedException
     **/
    public static void statUp(Character player) 
            throws InterruptedException {
        //Declaring and initializing variables
        int input;
        
        //Do while loop with error trap
        do{
            Thread.sleep(300);
            System.out.println("You have gained one skill point!\n");
            Thread.sleep(300);
            System.out.println("Which stat would you like to increase?");
            Thread.sleep(300);
            System.out.println("1) Strength\n2) Intelligence\n3) Dexterity");
            input = scanN.nextInt();
            
            //Switch statement for player's stat allocation
            switch(input) {
                //One point for strength
                case 1:{
                    Thread.sleep(300);
                    System.out.println("Strength +1!\n");
                    player.STR += 1;
                    break;
                }
                //One point for intelligence
                case 2:{
                    Thread.sleep(300);
                    System.out.println("Intelligence +1!\n");
                    player.INT += 1;
                    break;
                }
                //One point for dexterity
                case 3:{
                    Thread.sleep(300);
                    System.out.println("Dexterity +1!\n");
                    player.DEX += 1;
                    break;
                }
                //Default error trap
                default:{
                    Thread.sleep(300);
                    System.out.println("This is not a stat, please try again."
                            + "\n");
                }
            }
            
        } while(input < 1 && input > 3); 
        //End of do while loop
    }
    //End of statUp
    
    /**
     * Method Name: statGame
     * Description: Minigame to gain one stat point
     * @param player - user's character
     * @param guess - player's guess
     * @return found - if guess is found
     * @throws InterruptedException
     **/
    public static boolean statGame(Character player, int guess) throws InterruptedException {
        //Declaring and initializing variables
        int[] numArray = new int[10];
        int first = 0;
        int last = numArray.length - 1;
        int middle;
        boolean found = false;
        
        //For loop to fill array with random numbers from one to one hundred
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = (int)((Math.random() * 100) + 1);
        }
        
            //If player does not guess within one to one hundred
            if(guess > 100 || guess < 1) {
                Thread.sleep(300);
                System.out.println("Invalid guess, try again.\n");
            }
            
            //Binary search to see if array contains the player's guess
            while(!found && first <= last) {
                middle = (first + last) / 2;
                
                if(numArray[middle] == guess) {
                    found = true;
                }
                
                else if(numArray[middle] > guess) {
                    last = middle - 1;
                }
                
                else if(numArray[middle] < guess) {
                    first = middle + 1;
                }
                
            }
        
	//Returns found whether it is found or not
        return found;
    }
    //End of statGame
}
//End of class