package com.lpetsoan.wtc.models.Factories;

import java.util.*;

import com.lpetsoan.wtc.Utils.Input;
import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.HeroClass.*;

public class HeroFactory {
    private static  HeroFactory factory = null;
    private List<HeroClass> heroTypes;

    private HeroFactory(){
        heroTypes = new ArrayList<HeroClass>();

        heroTypes.add(new HeroClass("Basic", 1, 1, 1));
    }

    public static HeroFactory getFactory(){
        if (factory == null) factory = new HeroFactory();

        return factory; 
    }

    public Hero newHero(){
        String name = Input.getInput("Please enter Hero name: ");

        for (int i = 0; i < heroTypes.size(); i++){
            System.out.println(i + " : " + heroTypes.get(i).type);
        }

        int type = -1;
        int tmp;
        while (type == -1){
            try{
                tmp =Integer.parseInt(Input.getInput("Choose a class: "));
                if ( tmp > heroTypes.size()) {
                    System.out.print("No such hero try again\n:");
                    continue;
                }
                type = tmp;
            }
            catch (NumberFormatException e){
                System.out.print("Invalid input, enter a number\n:");
            }
        }

        Hero h = new Hero(name, heroTypes.get(type));
        // stats(h);
        h.stats();
        return h;
    }

    private void stats(Hero h){
        System.out.println("Name: " + h.getName());
        System.out.println("Class: " + h.getClass());
        System.out.println("Level: " + h.getLevel());
        System.out.println("Experience: " + h.getXP());
        System.out.println("Attack: " + h.getAttack());
        System.out.println("Defense: " + h.getDefense());
        System.out.println("Hit points: " + h.getHitPoints());
    }

    public List<HeroClass> getHeroTypes(){
        return this.heroTypes;
    }

}
