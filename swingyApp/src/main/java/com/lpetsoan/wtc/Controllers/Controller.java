package com.lpetsoan.wtc.Controllers;


import java.util.ArrayList;
import java.util.List;

// import com.lpetsoan.wtc.Utils.Input;
// import com.lpetsoan.wtc.Utils.Position;
import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.Villian;


public class Controller {
    private Hero player;
    private int map;
    private List<Villian> villians;
    private Villian opponent;

    public Controller(){
        this.player = null;
        this.map = 0;
    }


    public void initPlayer(Hero player){
        this.player = player;
        int level = player.getLevel();

        initVillians();
        
        this.map = (level-1)*5+10-(level%2);

    }
    private void initVillians(){
        villians = new ArrayList<Villian>();

        villians.add(new Villian("Vl", 1, 1, 1, 1, 1));
        villians.add(new Villian("Vl2", 10, 10, 1, 2, 2));

    }

    public int getMap(){
        return this.map;
    }

    public int getPlayerX(){
        return this.player.getX();
    }
    public void moveX(int val){
        this.player.setX(val);
    }

    public int getPlayerY(){
        return this.player.getY();
    }
    public void moveY(int val){
        this.player.setY(val);
    }

    public boolean yAtEdge(){
        return atEdge(this.player.getY());
    }

    public boolean xAtEdge(){
        return atEdge(this.player.getX());
    }

    private boolean atEdge(int val){
        if (val < 0 || val > this.map) { System.out.println("At edge");return true;}
        return false;
    }

    public boolean gameWon(){
        if (this.xAtEdge() || this.yAtEdge()) return true;
        return false;
    }

    public boolean battleAhead(){
        for (Villian v : villians){
            if (v.getX() == getPlayerX() && v.getY() == getPlayerY()){
                System.out.println("You have to fight or Run now");
                opponent = v;
                return true;
            }
        }
        
        return false;
    }


    public boolean fight(){
        while(true){
            System.out.println(this.player.getName() + " vs " + this.opponent.getName());
            // Player attacks first.
            this.player.attack(opponent);
            if (this.opponent.health <= 0){
                System.out.println("You have won the fight");
                // this.player.incXp();
                this.player.pillage(opponent);
                this.player.stats();
                this.villians.remove(opponent);
                opponent = null;
                // break;
                return true;
            }
            this.opponent.attack(player);
            if (this.player.health <= 0){
                System.out.println("You have lost the fight");
                this.player.setX(0);
                this.player.setY(0);
                this.player.health = 100;
                this.initPlayer(player);
                // break;
                return false;
            }
        }

        // if (this.player.health == 0)System.out.println("You lost the game");
        // else System.out.println("You won the battle, but can you win the war");
    }
}
