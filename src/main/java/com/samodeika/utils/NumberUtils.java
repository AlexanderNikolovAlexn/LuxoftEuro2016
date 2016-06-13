package com.samodeika.utils;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class NumberUtils {

    public static Integer parseInt(String s) {
        Integer result = new Integer(0);
        try {
            result = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            //System.out.println("Warning: " + ex.getMessage());
        }

        return result;
    }

    public static Double parseDouble(String s) {
        Double result = new Double(0d);
        try {
            result = Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            //System.out.println("Warning: " + ex.getMessage());
        }

        return result;
    }

}
