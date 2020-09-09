package com.lpetsoan.wtc.Utils;

import java.util.Scanner;

public class Input {
    public static String getInput(String message){
        Scanner i;
        String answer = "";

        try{
            i = new Scanner(System.in);

            System.out.print(message);
            answer = i.next();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return answer;
    }
}
