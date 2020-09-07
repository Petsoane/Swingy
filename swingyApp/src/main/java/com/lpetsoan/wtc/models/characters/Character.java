package com.lpetsoan.wtc.models.characters;

public class Character{
    private String name;
    private int attack;
    private int defense;
    private int hitPoints;
    private String weapon;
    private int x, y;

    public Character(String name, int attack, int defense, int hitPoints, int x, int y ){
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.x = x;
        this.y = y;
        this.weapon = null;
    }  

    public String getName(){
        return this.name;
    }

    public String getWeapon(){
        return this.weapon;
    }
    
    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public int getHitPoints(){
        return this.hitPoints;
    } 

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}