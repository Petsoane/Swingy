package com.lpetsoan.wtc.models.HeroClass;

import java.io.Serializable;

public class HeroClass implements Serializable{
    public String type;
    public int defense;
    public int hitPoints;
    public int attack;


    public HeroClass(String type, int defense, int hitPoints, int attack){
        this.type = type;
        this.defense = defense;
        this.attack = attack;
        this.hitPoints = hitPoints;
    }
    
}
