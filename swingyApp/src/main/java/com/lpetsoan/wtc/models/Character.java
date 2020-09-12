package com.lpetsoan.wtc.models;

import java.io.Serializable;

import com.lpetsoan.wtc.models.Artifacts.Artifact;

public class Character implements Serializable {
    protected String name;
    protected int defense;
    protected int hitPoints;
    protected int attack;
    public int health;
    protected int x, y;
    // protected Artifact artifact;
    protected Artifact weapon;
    protected Artifact armor;
    protected Artifact helm;

    public Character(String name, int defense, int hitPoint, int attack, int x, int y){
        this.name = name;
        this.defense = defense;
        this.hitPoints = hitPoint;
        this.attack = attack;
        this.x = x;
        this.y = y;
        this.health = 100;
        this.weapon = new Artifact("Weapon", 0,0,0);
        this.armor = new Artifact("Armor", 0,0,0);
        this.helm = new Artifact("Helm", 0,0,0);
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


    private void takeDamage(int amount){
        System.out.println("Taking damage");
        // amount /= defense;
        health -= (amount);
        System.out.println(health + " Left of my life");
    }

    public void attack(Character opp){
        int damage = this.attack + this.weapon.attack + this.helm.attack;

        System.out.println(this.name + " attacks " + opp.name);

        opp.takeDamage(damage + hitPoints);
    }
    
    public void stats(){
        System.out.println("Name: " + getName());
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Hit points: " + getHitPoints());
        System.out.println("Health: " + this.health);
    }
    
}
