package com.lpetsoan.wtc.models;

import com.lpetsoan.wtc.models.Artifacts.Artifact;
import com.lpetsoan.wtc.models.HeroClass.*;

public class Hero extends Character{
    private int level;
    private HeroClass hClass;
    private int xp;
   

    public Hero(String name, HeroClass heroClass){
        super(name, heroClass.defense, heroClass.hitPoints, heroClass.attack, 0, 0);
        this.level = 1;
        this.hClass = heroClass;
        this.setXp();
    }

    public int getLevel(){
        return this.level;
    }
    private void levelUP(){
        System.out.println("Leveling up");
        this.level += 1;
        // this.setXp();
        this.health = 100;
        this.attack += (10);
        this.defense += (10);
        this.hitPoints += (100);
        stats();
    }

    public int getXP(){
        return xp;
    }
    private void setXp(){
        xp = level*1000+ ((int)Math.pow((level - 1), 2)) * 450;
    }
    public void incXp(int amount){
        this.xp += amount;

        if (level < 2 && xp >= 2450 && xp < 4800){
            levelUP();
        }
        else if (level < 3 && xp >= 4800 && xp < 8050){
            levelUP();
        }
        else if (level < 4 && xp >= 8050 && xp < 12200){
            levelUP();
        }
        else if (level < 5 && xp >= 12200){
            levelUP();
        }
    }

    public String getHeroClass(){
        return this.hClass.type;
    }

    public void setArtifact(Artifact a){
        if (a.type == "Weapon"){
            this.weapon = a;
        }
    }
    public void pillage(Villian opp){
        this.health += (50 + (this.health / 2));
        this.incXp((opp.getAttack() + opp.getHitPoints()) * (500 * opp.getDefense()));
    }

    @Override
    public void stats() {
        super.stats();
        System.out.println("Class: " + getClass());
        System.out.println("Level: " + getLevel());
        System.out.println("Experience: " + getXP());
    }

}
