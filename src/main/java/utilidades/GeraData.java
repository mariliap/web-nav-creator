package main.java.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeraData {

	
	
	public String obterDataHoje() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();    
        return dateFormat.format(date).toString();
	}
	
	public String obterDataHojeMaisUmAno() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, +1);  
        return dateFormat.format(calendar.getTime()).toString();
	}
	
	public String obterDataAmanha() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);  
        return dateFormat.format(calendar.getTime()).toString();
	}
	
	public String obterDataOntem() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);  
        return dateFormat.format(calendar.getTime()).toString();
	}
}
