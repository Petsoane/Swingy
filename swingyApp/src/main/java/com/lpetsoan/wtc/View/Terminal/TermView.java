package com.lpetsoan.wtc.View.Terminal;

// import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.Factories.HeroFactory;

// import java.util.Scanner;
import java.lang.System;

import com.lpetsoan.wtc.Controllers.Controller;
import com.lpetsoan.wtc.Utils.Input;
import com.lpetsoan.wtc.View.View;

public class TermView implements View{
    // private Hero player;
    private int map;
    private Controller controller;


    public TermView(){
        this.controller = new Controller();
    }

    private void initPlayer(){
        int answer = -1;

        try{

            int tmp;
            while (answer == -1){
                try{
                    
                    tmp = Integer.parseInt(Input.getInput("1:Create hero\n2:Load hero \n"));
                    answer = tmp;
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a 1 or 2");
                }
            }

            if (answer == 1){
                System.out.println("Creating a new Hero");
                this.controller.initPlayer(HeroFactory.getFactory().newHero());
            }
            else{
                System.out.println("Loading the old heroes please wait");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void gameLoop(){
        // int level = this.player.getLevel();
        int current_x = this.controller.getPlayerX();
        int current_y = this.controller.getPlayerY();


        // this.map = (level-1)*5+10-(level%2);
        this.map = this.controller.getMap();
        char move;

        System.out.println("The map is " + this.map);

        while(true){
            if (this.controller.gameWon()){
                System.out.println("Game is won");
                break;
            }
            
            if (this.controller.battleAhead()){
                if (this.fightOrFlight()){
                    this.controller.fight();
                }
                current_x = this.controller.getPlayerX();
                current_y = this.controller.getPlayerY();
            }
            renderMap();
            
            move = (Input.getInput(">>")).charAt(0);
            if(move == 'k'){
                System.out.println("Save state and exit");
                break;
            }

            // move player.....
            switch(move){
                case 'a':
                    // if ((current_y - 1) >= 0) this.player.setY(current_y - 1); break;
                    if (!controller.yAtEdge()) this.controller.moveY(current_y - 1);break;
                case 'd':
                    // if ((current_y + 1) < this.map) this.player.setY(current_y + 1); break;
                    if (!controller.yAtEdge()) this.controller.moveY(current_y + 1);break;

                case 'w':
                    // if ((current_x - 1) >= 0) this.player.setX(current_x - 1); break;
                    if (!controller.xAtEdge()) this.controller.moveX(current_x - 1);break;

                case 's':
                    // if ((current_x + 1) < this.map) this.player.setX(current_x + 1); break;
                    if (!controller.xAtEdge()) this.controller.moveX(current_x + 1);break;

            }

            System.out.println(move);
            current_x = this.controller.getPlayerX();
            current_y = this.controller.getPlayerY();
        }
    }

    private void renderMap(){
        // Input.clear();
        for (int i = 0; i < this.map; i++){
            for (int k = 0; k < this.map; k++){
                if (this.controller.getPlayerX() == i && this.controller.getPlayerY() == k) System.out.print("0");
                else System.out.print("*");
            }
            System.out.println();
        }
    }
    
    @Override
    public void start() {
        Input.clear();
        System.out.println("Starting the game");
        initPlayer();
        gameLoop();
    }
    public boolean fightOrFlight(){
        char answer;

        answer = Input.getInput("Do you want to fight (Y/n): ").charAt(0);

        if (answer == 'y' || answer == 'Y'){
            return true;
        }
        return false;
    }
    
    
}
