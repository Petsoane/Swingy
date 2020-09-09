package com.lpetsoan.wtc.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {
    public static String getInput(String message){
        BufferedReader i = null;
        String answer = "";

        try{
            i = new BufferedReader( new InputStreamReader(System.in));

            System.out.print(message);
            while(answer.equals(""))
                answer = i.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            // if (i != null) i.close();
        }

        return answer;
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

}
