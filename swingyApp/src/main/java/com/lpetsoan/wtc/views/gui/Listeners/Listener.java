package com.lpetsoan.wtc.views.gui.Listeners;

import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

public class Listener {
    private static ChoiceListener choiceListener = null;


    /**
     * 
     * @return true or false.
     */
    public static boolean getChoice(JPanel panel){
        choiceListener = new ChoiceListener(panel);

        return choiceListener.getChoice();
    }
    
    
}

class ChoiceListener{
    private JPanel panel;
    private boolean value;
    private Semaphore s;

    public ChoiceListener(JPanel panel){
        this.panel = panel;
        this.s = new Semaphore(0);
    }

    public boolean getChoice(){
        try{
            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");

            yes.setBounds(10,10,10,10);
            yes.addActionListener(new YesEvent());
            no.setBounds(10,10,10,10);
            no.addActionListener(new NoEvent());

            panel.add(yes);
            panel.add(no);
            panel.repaint();

            s.acquire();

            panel.remove(yes);
            panel.remove(no);
            panel.repaint();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return value;
    }

    private class YesEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            value = true;
            s.release();
        }
    }
    private class NoEvent implements ActionListener{
        public void actionPerformed(ActionEvent e){
            value = false;
            s.release();
        }
    }
}