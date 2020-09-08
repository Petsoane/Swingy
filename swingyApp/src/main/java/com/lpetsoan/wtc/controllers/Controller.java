package com.lpetsoan.wtc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lpetsoan.wtc.models.characters.Hero;



public class Controller {
    private static Controller controller = null;
    private Hero player;
    private int boardSize;

    private Controller(){
        this.boardSize = 0;
    }

    public static void startGame(){
        if (controller == null) controller = new Controller();

        // create or Select.
        controller.initGame();
    }

    public void initGame(){
        System.out.println("Board Of Horrors......");

        // Load or Create
        this.initPlayer();

        int level = 1;
        
        this.boardSize = (level -1) * 5 + 10 - (level % 2);
        
        System.out.println(this.player.getName() + " Playing grid " + this.boardSize);
    }
    
    /**
     * This method should not change since its just coordinating the other stuff....
     * 
     */
    private void initPlayer(){
        // load previous characters if any
        int answer = -1;
        List<Hero> prevCharacters = loadCharacters();
        int prevCharCount = prevCharacters.size();
        
        
        while (answer < 0 || answer > prevCharCount)
            answer = this.getChoice(prevCharCount + ": Create new player" + "\n[*]>> ", prevCharacters);
        
        if (answer == prevCharCount && !prevCharacters.isEmpty())
            this.player = createPlayer();
        else{
            this.player = prevCharacters.get(answer);
        }
        System.out.println("Your choice was "  + answer);
    }
    
    /**
     * Loads all created characters from the database.... Im not sure yet how I am going to work
     * with this later on.. but for now it just uses some dummy data.
     * @return
     */
    private List<Hero> loadCharacters(){
        List<Hero> prevCharacters = new ArrayList<Hero>();
        // init dummy data.
        prevCharacters.add(new Hero("lebo","Wuss", 10, 10, 10, 0, 0));
        prevCharacters.add(new Hero("Darleen", "PPLPLSR",10, 10, 10, 0, 0));
        return prevCharacters;
    }
     
    /**
     * Creates a new player
     * 
     * Should be overriden at some point by both controllers.
     * @return
     */
    protected Hero createPlayer(){
        return new Hero("Mr robot", "GODMODE",10, 10, 10, 0, 0);
    }
    
    /**
     * This function should change depending on what is using it... if its the terminal it should print to the terminal
     * if its a gui it should show a dialog box and get input in that way
     * @param message
     * @return
     */
    protected int getChoice(String message, List<Hero> prevCharacters){
        int answer = 0;
        Scanner input = null;
        int prevCharCount = prevCharacters.size();

        try{
            
            
        // prompt for the user for options
            for (int i = 0; i < prevCharCount; i++){
                System.out.print( (i) + ": ");
                System.out.println(prevCharacters.get(i).getName());
            }
            input = new Scanner(System.in);
            System.out.print(message);

            answer = Integer.parseInt(input.next());

        }
        catch (Exception e){ answer = 0;}
        finally{ if (input != null) input.close(); }

        return  answer;
    }


}
