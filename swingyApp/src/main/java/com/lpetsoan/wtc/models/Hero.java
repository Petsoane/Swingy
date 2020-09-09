package com.lpetsoan.wtc.models;

import com.lpetsoan.wtc.models.HeroClass.*;

public class Hero extends Character{
    private int level;
    private HeroClass hClass;
    private int xp;

    public Hero(String name, HeroClass heroClass){
        super(name, heroClass.defense, heroClass.hitPoints, heroClass.attack, 0, 0);
        this.level = 0;
        this.hClass = heroClass;
        this.xp = 0;
    }

    public int getLevel(){
        return this.level;
    }

    public int getXP(){
        return xp;
    }

    public String getHeroClass(){
        return this.hClass.type;
    }

}
