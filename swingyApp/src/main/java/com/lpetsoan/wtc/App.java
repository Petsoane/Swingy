package com.lpetsoan.wtc;


import com.lpetsoan.wtc.controllers.Controller;
import com.lpetsoan.wtc.views.View;
import com.lpetsoan.wtc.views.gui.SwingView;
import com.lpetsoan.wtc.views.terminal.TermView;

public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        View tview = new TermView();
        View sview = new SwingView();

        // Controller.startGame(tview);
        Controller.startGame(sview);
        
    }
}
