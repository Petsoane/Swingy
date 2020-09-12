package com.lpetsoan.wtc.models;

import java.util.Random;

import com.lpetsoan.wtc.models.Artifacts.Artifact;

public class Villian extends Character {
    private Random rand;

    public Villian(String name, int defense, int attack, int hitPoints, int x, int y){
        super(name, defense, attack, hitPoints, x, y);
        rand = new Random();
        genRandomArtifact();
    }

    private void genRandomArtifact(){
        int i = rand.nextInt(3);

        switch(i){
            case 0:
                this.armor = new Artifact("Armor", 10, 0, 0);break;
            case 1:
                this.helm = new Artifact("Helm", 0, 0, 10);break;
            case 2:
                this.weapon = new Artifact("Weapon", 0, 10, 0);break;
        }
    }

    public Artifact dropItem(){
        int i = rand.nextInt(4);
        Artifact a = null;

        switch(i){
            case 0:
                a = this.weapon;
                break;
            case 1:
                a = this.helm;
                break;
            case 2:
                a = this.armor;
                break;

        }
        return a;
    }
    
}
