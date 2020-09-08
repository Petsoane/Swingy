package com.lpetsoan.wtc.views.gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lpetsoan.wtc.models.characters.Hero;
import com.lpetsoan.wtc.views.View;
import com.lpetsoan.wtc.views.gui.Listeners.Listener;

public class SwingView extends JFrame implements View{
    JPanel panel;

    public SwingView(){
        // initUI();
        panel = new JPanel();
        add(panel);
    }

    @Override
    public void initUI(){
        // add(new Board());

        setSize(200, 200);
        setTitle("Swingy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    
    @Override
    public boolean load() {
        return Listener.getChoice(panel);
    }

    @Override
    public int getChoice(String message, List<Hero> prevCharacters) {
        return 0;
    }

    @Override
    public Hero createPlayer() {
        return new Hero("Mr robot", "GODMODE",10, 10, 10, 0, 0);
    }
    
}


