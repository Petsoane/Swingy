package com.lpetsoan.wtc;

import com.lpetsoan.wtc.View.GUI.Gui;
import com.lpetsoan.wtc.View.Terminal.TermView;

public class App {
    public static void main(String[] args) {
        // System.out.println( "Hello World!" );
        // TermView t = new TermView();

        // t.start();
        int size = 600;

        Gui g = new Gui("This is a test", size, size);

        g.start();
    }
}
