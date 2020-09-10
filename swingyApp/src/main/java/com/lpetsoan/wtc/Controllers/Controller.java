package com.lpetsoan.wtc.Controllers;


import com.lpetsoan.wtc.Utils.Position;
import com.lpetsoan.wtc.models.Hero;


public class Controller {
    private Hero player;
    private int map;

    public Controller(){
        this.player = null;
        this.map = 0;
    }


    public void initPlayer(Hero player){
        this.player = player;
        int level = player.getLevel();

        this.map = (level-1)*5+10-(level%2);

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
}
