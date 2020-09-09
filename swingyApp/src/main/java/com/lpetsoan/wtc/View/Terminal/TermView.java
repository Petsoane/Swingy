package com.lpetsoan.wtc.View.Terminal;

import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.Factories.HeroFactory;

import java.util.Scanner;
import java.lang.System;

import com.lpetsoan.wtc.View.View;

public class TermView implements View{
    private Hero player;

    public TermView(){
    }

    private void initPlayer(){
        Scanner input = null;
        int answer = -1;

        try{
            input = new Scanner(System.in);

            System.out.println("1:Create hero\n2:Load hero");

            int tmp;
            while (answer == -1){
                try{
                    
                    tmp = Integer.parseInt(input.next());
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
        finally{
            if (input != null) input.close();
        }


    }


    @Override
    public void start() {
        System.out.println("Starting the game");
        initPlayer();
    }

    
}
