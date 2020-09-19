package com.lpetsoan.wtc.View.GUI;

import com.lpetsoan.wtc.Controllers.Controller;
import com.lpetsoan.wtc.View.View;
import com.lpetsoan.wtc.View.GUI.Panes.GamePane;
import com.lpetsoan.wtc.models.Factories.HeroFactory;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument.ElementSpec;
import javax.swing.text.Highlighter.Highlight;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.Scanner;

public class Gui implements Runnable, KeyListener {
    private Display display;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private boolean running = false;
    private Controller controller;

    
    public int width, height;
    private int x, y;
    public String title;
    private JTextArea area = new JTextArea(width,height);

    private int map_size;

    public Gui(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.controller = new Controller();
        area.setSize(new Dimension(width, height));
    }

    private void init() {
        // prep the user...
        this.controller.initPlayer(this.controller.loadHero());

        x = controller.getPlayerX();
        y = controller.getPlayerY();

        // prep the display
        display = new Display(title, width, height);
        display.getCanvas().addKeyListener(this);

        // init info frame 
        
    }

    private void dumpFight(){
        Scanner i = null;
       

        area.append("This was an epic fight");
        // area.setText("");
        area.setVisible(true);

        try{
            i = new Scanner(new File("Sim.txt"));
            while (i.hasNextLine()){
                // System.out.println(i.nextLine()); 
                area.append(i.nextLine() + "\n");
            }
        }
        catch (Exception e){e.printStackTrace();}
        finally{
            if (i != null) i.close();
        }
    }
    private void tick() {
        if (controller.gameWon()) {
            System.out.println("The game is won");
            controller.resetPlayer();

            x = controller.getPlayerX();
            y = controller.getPlayerY();
        }

        if (controller.battleAhead()){
            JOptionPane.showMessageDialog(this.display.getCanvas(), "There is a fight iminant", "Fight!! Fight!", JOptionPane.PLAIN_MESSAGE);

            int fight = JOptionPane.showConfirmDialog(this.display.getCanvas(), "Do you want to fight", "What are you to do?", JOptionPane.YES_NO_OPTION);

            if (fight == JOptionPane.YES_OPTION){
                if (controller.fight()){
                    JOptionPane.showMessageDialog(display.getCanvas(), "You have won the battle but can you win the war", "Winner, Winner", JOptionPane.PLAIN_MESSAGE);

                    if (controller.itemDropped()){
                        String message = "An artifact was dropped, wanna pick it up?";
                        int a = JOptionPane.showConfirmDialog(display.getCanvas(), message, "Artifact", JOptionPane.YES_NO_OPTION);

                        if (a == JOptionPane.YES_OPTION){
                            controller.pickUpItem();
                        }
                    }
                }
                else{
                    System.out.println("You have lost the fight");
                    JOptionPane.showMessageDialog(display.getCanvas(), "You lost the battle keep on trying its gonn get better",  "Losers weep", JOptionPane.PLAIN_MESSAGE);
                    x = controller.getPlayerX();
                    y = controller.getPlayerY();
                }

                g = display.getCanvas().getGraphics();

                g.clearRect(0, 0, width, height);
                g.dispose();
                
                dumpFight();
                JScrollPane pane = new JScrollPane(area);
                pane.setSize(new Dimension(width, height));
                display.getGameFrame().add(pane);
                area.setVisible(true);
                display.getGameFrame().pack();
                // display.getInfoFrame().setVisible(true);
                // display.getInfoFrame().pack();
                // display.getGameFrame().setVisible(false);
                // JOptionPane.showMessageDialog(null, "Continue", "Pause", JOptionPane.PLAIN_MESSAGE);
                thread.suspend();
                // display.getInfoFrame().setVisible(false);
                // display.getGameFrame().setVisible(true);
                display.getGameFrame().remove(pane);
                display.getGameFrame().add(display.getCanvas());
                display.getGameFrame().pack();


            }
            else{
                System.out.println("Lucky bastard... you are getting teleported");
                controller.moveX(++x);
                controller.moveY(++y);
            }
        }
        // System.out.println("Tickicj away");
        // try {
        // thread.wait(1000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(1);
            return;
        }

        g = display.getCanvas().getGraphics();

        g.clearRect(0, 0, width, height);

        // g.fillRect(x, y, width, height);
        drawGrid(g);

        bs.show();
        g.dispose();

    }

    private void drawGrid(Graphics g) {
        int sq_size;
        int startx, starty;

        map_size = this.controller.getMap();

        sq_size = (int) ((1 / (double) map_size) * this.width) - 5;
        startx = starty = 0;

        for (int i = 0; i < map_size; i++) {
            for (int k = 0; k < map_size; k++) {
                if ((x * sq_size) == startx && (y * sq_size) == starty)
                    g.fillRect(startx, starty, sq_size, sq_size);
                else
                    g.drawRect(startx, starty, sq_size, sq_size);
                startx += sq_size;
            }
            startx = 0;

            starty += sq_size;
        }

    }

    @Override
    public void run() {
        System.out.println("This thing is being ran");
        init();
        while (running) {
            render();
            tick();
            try {
                thread.sleep(260);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("There was a key pressed" + e.getKeyCode());
        final int RIGHT = 39;
        final int LEFT = 37;
        final int UP = 38;
        final int DOWN = 40;
        final int ENTER = 10;
        final int shift = (int) ((1 / (double) map_size) * this.width) - 5;

        switch (e.getKeyCode()) {
            case RIGHT:
                if (!this.controller.xAtEdge()) {
                    controller.moveX(controller.getPlayerX() + 1);
                }
                x += 1;
                break;
            case LEFT:

                if (!this.controller.xAtEdge()) {
                    controller.moveX(controller.getPlayerX() - 1);
                }
                x -= 1;
                break;
            case UP:

                if (!this.controller.yAtEdge()) {
                    controller.moveY(controller.getPlayerY() - 1);
                }
                y -= 1;
                break;
            case DOWN:

                if (!this.controller.yAtEdge()) {
                    controller.moveY(controller.getPlayerY() + 1);
                }
                y += 1;
                break;
            case ENTER:
                thread.resume();
        }
        System.out.println(x);
        System.out.println(y);

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
}

class Display {
    private JFrame gameFrame;
    private JFrame infoFrame;
    private Canvas canvas;

    private int width, height;

    private String title;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        gameFrame = new JFrame(title);
        gameFrame.setSize(width, height);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        
        // Set up the info frame
        infoFrame = new JFrame("Info frame");
        infoFrame.setSize(width, height);
        infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoFrame.setResizable(false);
        infoFrame.setLocationRelativeTo(null);
        
        
        
        // set up the game frame
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        gameFrame.add(canvas);
        gameFrame.pack();

    }

    public Canvas getCanvas() {
        return this.canvas;
    }
    public JFrame getGameFrame(){
        return this.gameFrame;
    }

    public JFrame getInfoFrame(){
        return infoFrame;
    }

}
