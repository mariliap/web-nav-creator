package main.java.utilidades;

public class Utils {

	public static long temporizar(long tempoAnteriorEmMilisegundos) {
        long tempoAtualEmMilisegundos = System.currentTimeMillis();
        System.out.println(converterMiliSegundosEmFormatoLegivel(tempoAtualEmMilisegundos - tempoAnteriorEmMilisegundos));
        return tempoAtualEmMilisegundos;
    }

    public static String converterMiliSegundosEmFormatoLegivel(long milisegundos){
        long x = milisegundos / 1000;
        long secondos = x % 60;
        x /= 60;
        long minutos = x % 60;
        x /= 60;
        long horas = x % 24;
        x /= 24;
        long dias = x;

        return dias + " Dias, "+ horas +" horas, "+ minutos +" minutos, "+ secondos +" segundos.";
    }
}
