package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextUtil {
	public static String getTextfromFile(String filename){
		String context = "";
		BufferedReader reader = null;
        try {
        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename),"UTF-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                context += tempString;
                context += "\n";
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } 
                catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
		return context;
	}

	public static String getTextfromFile(String filename, int lineid) {
		String context = "";
		BufferedReader reader = null;
		int linenum = 1;
        try {
        	InputStreamReader isr = new InputStreamReader(new FileInputStream(filename),"UTF-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if(linenum == lineid){
                	context = tempString.trim();
                	break;
                }
                linenum++;
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } 
                catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
		return context;
	}
	
	public static int SumString(String s) {
        int str = 0;
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            str = str + ch;
        }
        return str;
    }
}
