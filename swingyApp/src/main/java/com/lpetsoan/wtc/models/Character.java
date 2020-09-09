package com.lpetsoan.wtc.models;

public class Character {
    private String name;
    private int defense;
    private int hitPoints;
    private int attack;
    private int x, y;

    public Character(String name, int defense, int hitPoint, int attack, int x, int y){
        this.name = name;
        this.defense = defense;
        this.hitPoints = hitPoint;
        this.attack = attack;
        this.x = 0;
        this.y = 0;

    }

    public String getName(){
        return this.name;
    }
    
    public int getDefense(){
        return this.defense;
    }
    
    public int getHitPoints(){
        return this.hitPoints;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }
}
