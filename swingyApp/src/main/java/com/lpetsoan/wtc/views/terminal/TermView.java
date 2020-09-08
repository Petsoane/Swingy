package com.lpetsoan.wtc.views.terminal;

import com.lpetsoan.wtc.models.characters.Hero;
import com.lpetsoan.wtc.views.View;

import java.util.*;

public class TermView implements View {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void initUI() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean load(){
        boolean l = false;
        String answer;

        System.out.println("Load Hero or Create new:\n>>");
        answer = scanner.nextLine();

        if (answer.equals("load"));
            l = true;
        
        
        return l;
    }

    @Override
    public int getChoice(String message, List<Hero> prevCharacters) {
        int answer = 0;
        Scanner input = null;
        int prevCharCount = prevCharacters.size();

        try{
            // prompt for the user for options
            for (int i = 0; i < prevCharCount; i++){
                System.out.print( (i) + ": ");
                System.out.println(prevCharacters.get(i).getName());
            }
            input = new Scanner(System.in);
            System.out.print(message);

            answer = Integer.parseInt(input.next());

        }
        catch (Exception e){ answer = 0;}
        finally{ if (input != null) input.close(); }

        return  answer;
    }

    @Override
    public Hero createPlayer() {
        return new Hero("Mr robot", "GODMODE",10, 10, 10, 0, 0);
    }
}
