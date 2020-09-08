package com.lpetsoan.wtc.views;

import com.lpetsoan.wtc.models.characters.Hero;

import java.util.*;

public interface View {

    void initUI();
    /**
     * Prompts the user whether they want tp create a new Hero
     * or load an old one.
     * @return
     */
    public boolean load();

    /**
     * This function should change depending on what is using it... if its the terminal it should print to the terminal
     * if its a gui it should show a dialog box and get input in that way
     * @param message
     * @return
     */
    public int getChoice(String message, List<Hero> prevCharacters);

         
    /**
     * Creates a new player
     * 
     * Should be overriden at some point by both controllers.
     * @return
     */
    public Hero createPlayer();

}
