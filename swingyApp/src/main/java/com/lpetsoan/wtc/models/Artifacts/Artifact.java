package com.lpetsoan.wtc.models.Artifacts;

import java.io.Serializable;

import javax.lang.model.element.TypeElement;

public class Artifact implements Serializable {
    public String type;
    public int attack;
    public int defense;
    public int hitpoints;

    public Artifact(String name, int attack, int defense, int hitpoints){
        this.type = name;
        this.attack = attack;
        this.defense = defense;
        this.hitpoints = hitpoints;        
    }
}
