package com.lpetsoan.wtc.View.GUI.Panes;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePane extends JPanel{
    private int size;
    private int playerX;
    private int playerY;

    public GamePane(){
        this.size = 36;
        this.playerX = this.size/2;
        this.playerY = this.size/2;
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        // TODO Auto-generated method stub
        super.paintComponent(arg0);
        paint(arg0);
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);


        JLabel label = new JLabel();

        int sqaure_size = (int)((this.size/1) * getSize().getWidth());

        int startx = sqaure_size /2;
        int starty = sqaure_size;

        for(int i = 0;i < this.size; i++){
            for (int k = 0; k < this.size; k++){
                g.drawRect(startx, starty, sqaure_size, sqaure_size);
            }
            startx = 0;
        }
    }
        
    
}
