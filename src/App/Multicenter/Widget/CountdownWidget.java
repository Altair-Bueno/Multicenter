package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;
import java.time.*;
import java.util.TreeSet;

public class CountdownWidget extends AbstractWidget{
    String titulo;
    int tiempo;
    Clock c;
    Clock fechaDeseada;
    Instant tiempoFinal;
    Instant tiempoActual;
    Duration tiempoRestante;

    public CountdownWidget(int dia, int mes, int anyo, String titulo) throws ParseException { //Debe introducir el día, hora y minuto del evento deseado
        this.titulo = titulo;
        //Test

        String fechaDS = dia + "/" + mes + "/" + anyo;
        Date fechaFinal = new SimpleDateFormat("dd/MM/yyyy").parse(fechaDS);

        tiempoActual = c.instant();
        tiempoFinal.adjustInto(fechaFinal.toInstant());

        tiempoRestante = Duration.between(tiempoFinal, tiempoActual);
    }

    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this, titulo, cadena));
        return res;
    }

}

