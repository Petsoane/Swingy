package com.lpetsoan.wtc;

import java.time.LocalDate;

import javax.print.attribute.Size2DSyntax;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
                default:
                    System.out.println("What are you doing.... Console or gui.... DIm wit");
            }
        }
        else{
            System.out.println("PLease Enter the type you want to play... Gui or Console");
        }
    }
}
