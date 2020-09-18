package com.lpetsoan.wtc.View.GUI;

import com.lpetsoan.wtc.View.View;
import com.lpetsoan.wtc.View.GUI.Panes.GamePane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Gui implements Runnable, KeyListener {
    private Display display;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private boolean running = false;

    public int width, height;
    private int x, y;
    public String title;

    private int map_size;

    public Gui(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);
        display.getCanvas().addKeyListener(this);
    }

    private void tick() {
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

        map_size = 10;

        sq_size = (int) ((1 / (double) map_size) * this.width) - 5;
        startx = starty = 0;

        for (int i = 0; i < map_size; i++) {
            for (int k = 0; k < map_size; k++) {
                if (x == startx && y == starty)
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
        // TODO Auto-generated method stub
        System.out.println("There was a key pressed" + e.getKeyCode());
        final int RIGHT = 39;
        final int LEFT = 37;
        final int UP = 38;
        final int DOWN = 40;
        final int shift = (int) ((1 / (double) map_size) * this.width) - 5;

        switch (e.getKeyCode()) {
            case RIGHT:
                x += shift;
                break;
            case LEFT:
                x -= shift;
                break;
            case UP:
                y -= shift;
                break;
            case DOWN:
                y += shift;
                break;
        }

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
    private JFrame frame;
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
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas() {
        return this.canvas;
    }

}
