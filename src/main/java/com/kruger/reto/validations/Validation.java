package com.kruger.reto.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

        public boolean requireNumbers(String identification){

            if(identification.matches("[+-]?\\d*(\\.\\d+)?")) return true;
            else return false;

        }
        public boolean requireText(String Name) {
        Pattern pat = Pattern.compile("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+");
        Matcher mather = pat.matcher(Name);
        if(mather.matches()) return true;
        else return false;
    }

    public boolean lengthIdentification(String identification) {
        if(identification.length()<10 || identification.length()>10) return false;
        else return true;
    }

    public boolean validateEmail(String email) {
        Pattern pat = Pattern.compile("([a-z0-9]*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mather = pat.matcher(email);
        if(mather.find()) return true;
        else return false;
    }


}
