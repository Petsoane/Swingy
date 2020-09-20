package com.lpetsoan.wtc;

import javax.print.attribute.Size2DSyntax;

import com.lpetsoan.wtc.View.GUI.Gui;
import com.lpetsoan.wtc.View.Terminal.TermView;

public class App {
    public static void main(String[] args) {
        System.out.println(args.length);

        if (args.length == 1){
            switch(args[0]){
                case "Console":
                TermView t = new TermView();
                t.start();
                break;
                case "Gui":
                    Gui g = new Gui("Just a simple test", 600, 600);
                    g.start();
            }
        }
        else{
            System.out.println("PLease Enter the type you want to play... Gui or Console");
        }
    }
}
