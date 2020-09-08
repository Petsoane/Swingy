package com.lpetsoan.wtc.models.characters;

public class Hero extends Character {
    private String heroClass;
    private int xp;

    public Hero(String name, String heroClass, int attack,int defense, int hitPoints, int x, int y){
        super(name, attack, defense, hitPoints, x, y);
        this.heroClass = heroClass;
        this.xp = 1;
    }

    public String getHeroClass(){
        return this.heroClass;
    }

    public int getXp(){
        return this.xp;
    }
}
