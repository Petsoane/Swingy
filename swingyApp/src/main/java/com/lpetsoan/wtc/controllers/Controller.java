package com.lpetsoan.wtc.controllers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lpetsoan.wtc.models.characters.Hero;
import com.lpetsoan.wtc.views.View;



public class Controller {
    private static Controller controller = null;
    private Hero player;
    private int boardSize;
    private View view;

    private Controller(View view){
        this.boardSize = 0;
        this.view = view;
    }

    public static void startGame(View v){
        if (controller == null) controller = new Controller(v);

        // Initialise game play..
        controller.view.initUI();    
        controller.initGame();

        // Game looop
    }

    private void gameLoop(){
        // EventQueue.invokeLater(() -> {
        // });
    }
    
    private void initGame(){
        // initialize view
        view.initUI();
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
        
        if (view.load()){
            System.out.println("Loading the stuff in");
            this.player = view.createPlayer();
        }
        else{
            System.out.println("Are you here");
            String message = "[*]>> ";
            while (answer < 0 || answer > prevCharCount){
                
                answer = view.getChoice(message, prevCharacters);
            }
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
}
