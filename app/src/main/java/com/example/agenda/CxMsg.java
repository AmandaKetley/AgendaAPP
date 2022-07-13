package com.example.agenda;

import android.app.Activity;
import android.app.AlertDialog;

public class CxMsg {

    public static void mostrar(String txt, Activity act){
        AlertDialog.Builder adc = new AlertDialog.Builder(act);
        adc.setMessage(txt);
        adc.setNeutralButton("OK",null);
        adc.show();
    }
}
