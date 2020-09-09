package com.lpetsoan.wtc.View.Terminal;

import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.Factories.HeroFactory;

import java.util.Scanner;
import java.lang.System;

import com.lpetsoan.wtc.Utils.Input;
import com.lpetsoan.wtc.View.View;

public class TermView implements View{
    private Hero player;
    private int map;


    public TermView(){
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
                this.player = HeroFactory.getFactory().newHero();
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
        int level = this.player.getLevel();
        int current_x = this.player.getX();
        int current_y = this.player.getY();


        this.map = (level-1)*5+10-(level%2);
        char move;

        System.out.println("The map is " + this.map);

        while(true){
            renderMap();
            
            move = (Input.getInput(">>")).charAt(0);
            if(move == 'k'){
                System.out.println("Save state and exit");
                break;
            }
            switch(move){
                case 'a':
                    if ((current_y - 1) >= 0) this.player.setY(current_y - 1); break;
                case 'd':
                    if ((current_y + 1) < this.map) this.player.setY(current_y + 1); break;
                case 'w':
                    if ((current_x - 1) >= 0) this.player.setX(current_x - 1); break;
                case 's':
                    if ((current_x + 1) < this.map) this.player.setX(current_x + 1); break;
            }

            System.out.println(move);
            current_x = this.player.getX();
            current_y = this.player.getY();
        }
    }

    private void renderMap(){
        Input.clear();
        for (int i = 0; i < this.map; i++){
            for (int k = 0; k < this.map; k++){
                if (this.player.getX() == i && this.player.getY() == k) System.out.print("0");
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

    
}
