package com.lpetsoan.wtc.Controllers;

import java.beans.DesignMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.validation.*;
import javax.validation.ValidatorFactory;
// import javax.xml.validation.Validator;

// import com.lpetsoan.wtc.Utils.Input;
// import com.lpetsoan.wtc.Utils.Position;
import com.lpetsoan.wtc.models.Hero;
import com.lpetsoan.wtc.models.Villian;
import com.lpetsoan.wtc.models.Artifacts.Artifact;
import com.lpetsoan.wtc.validators.interfaces.ControllerValidator;

public class Controller {
    private Hero player;

    @ControllerValidator
    private Integer map;

    private List<Villian> villians;
    private Villian opponent;

    private Artifact droppedItem = null;

    public Controller() {
        this.player = null;
        this.map = 0;
    }

    public void initPlayer(Hero player) {
        this.player = player;
        int level = player.getLevel();

        // this.map = (level - 1) * 5 + 10 - (level % 2);
        this.player.setX(this.map / 2);
        this.player.setY(this.map / 2);
        initVillians();

    }

    public void resetPlayer() {
        initPlayer(this.player);
    }

    private void initVillians() {
        Random r = new Random();
        // r.setSeed();
        villians = new ArrayList<Villian>();

        int count = 10;
        // System.out.println("What is happening" + count);

         // validate the controller
         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         Validator validator = factory.getValidator();
         Set<ConstraintViolation<Controller>> vr = validator.validate(this);
 
         if (vr.size() > 0){
             for (ConstraintViolation<Controller> c : vr) {
                 System.out.println(c.getMessage());
             }
             System.exit(1);
         }
         
        for (int i = 0; i < count; i++) {
            villians.add(new Villian("Vl", r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(this.map),
                    r.nextInt(this.map)));
        }

    }

    public int getMap() {
        return this.map;
    }

    public int getPlayerX() {
        return this.player.getX();
    }

    public void moveX(int val) {
        this.player.setX(val);
    }

    public int getPlayerY() {
        return this.player.getY();
    }

    public void moveY(int val) {
        this.player.setY(val);
    }

    public boolean yAtEdge() {
        return atEdge(this.player.getY());
    }

    public boolean xAtEdge() {
        return atEdge(this.player.getX());
    }

    private boolean atEdge(int val) {
        if (val < 0 || val >= this.map) {
            System.out.println("At edge");
            return true;
        }
        return false;
    }

    public boolean gameWon() {
        if (this.xAtEdge() || this.yAtEdge())
            return true;
        return false;
    }

    public boolean battleAhead() {
        for (Villian v : villians) {
            // System.out.println(v.getAttack());
            // System.out.println(v.getY());
            if (v.getX() == getPlayerX() && v.getY() == getPlayerY()) {
                System.out.println("You have to fight or Run now");
                opponent = v;
                return true;
            }
        }

        return false;
    }

    public boolean itemDropped() {
        return (droppedItem != null);
    }

    public void pickUpItem() {
        this.player.setArtifact(droppedItem);
        droppedItem = null;
    }

    public boolean fight() {
        PrintStream con = System.out;
        try {
            PrintStream file = new PrintStream(new File("Sim.txt"));

            System.setOut(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {

            System.out.println(this.player.getName() + " vs " + this.opponent.getName());
            // Player attacks first.
            this.player.attack(opponent);
            if (this.opponent.health <= 0) {
                this.player.pillage(opponent);
                this.player.stats();

                // Drop the villians artifact.
                this.droppedItem = opponent.dropItem();

                // Remove the villian from the map.
                this.villians.remove(opponent);
                opponent = null;
                System.setOut(con);
                return true;
            }

            this.opponent.attack(player);
            if (this.player.health <= 0) {
                // Reset the players position.
                this.player.setX(0);
                this.player.setY(0);

                // Get something nyana for the loss.
                this.player.incXp(100);
                this.player.stats();

                // Reset the players health
                this.player.health = 100;

                // Reset the villians.
                this.initPlayer(player);
                System.setOut(con);
                return false;
            }
        }

        // if (this.player.health == 0)System.out.println("You lost the game");
        // else System.out.println("You won the battle, but can you win the war");
    }

    public void saveHero() {
        FileOutputStream outFile = null;
        ObjectOutputStream serializer = null;

        try {
            outFile = new FileOutputStream(new File("State.dnt"));
            serializer = new ObjectOutputStream(outFile);

            serializer.writeObject(this.player);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (serializer != null)
                    serializer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Hero loadHero() {
        Hero tmp = null;
        FileInputStream inFile = null;
        ObjectInputStream deserializer = null;

        try {
            inFile = new FileInputStream("State.dnt");
            deserializer = new ObjectInputStream(inFile);

            tmp = (Hero) deserializer.readObject();
            tmp.stats();
        } catch (FileNotFoundException e) {
            System.out.println("NO HISTORY FILE");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inFile != null)
                    inFile.close();
                if (deserializer != null)
                    deserializer.close();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tmp;
    }
}
