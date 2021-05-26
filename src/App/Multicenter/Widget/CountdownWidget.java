package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.io.IOException;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountdownWidget extends AbstractWidget {
    private final String titulo;
    private final Date fechaevento;

    /**
     * Crea un widget de cuenta atrás, ajustando las variables
     * de clases necesarias para su funcionamiento.
     *
     * <p><p>Aclaración para la implementación de la fecha del evento
     * recibida como parámetro:
     * Date tiene que crearse con new Date(Año, Mes, Dia), donde:
     * <ul>
     *     <li>Año = (año_deseado - 1900) &emsp; &emsp; (Ej: para 2022, pondriamos 2022 - 1900 = 122)</li>
     *     <li>Mes = (mes_deseado - 1) &emsp; &emsp; &emsp; &ensp;(Ej: Para Junio (06), pondriamos 06-1 = 5)</li>
     *     <li>Día = (dia_deseado) &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;(Tal cual, un número entre 1 y 31)</li>
     * </ul>
     *
     * @param fechaevento La fecha del evento sobre la cual se crea
     *                    la cuenta atrás.
     * @param titulo      El título del evento.
     */
    public CountdownWidget(Date fechaevento, String titulo) {
        this.titulo = titulo;
        this.fechaevento = fechaevento;
    }

    public int[] check() {
        int[] timetoevent = new int[4]; // 0 - Days, 1 - Hours, 2 - Minutes, 3 - Seconds
        long diff = (fechaevento.getTime() - new Date().getTime()) / 1000; // new Date() = current system date

        timetoevent[0] = (int) (diff / (60 * 60 * 24)); // Days
        timetoevent[1] = (int) (diff / (60 * 60)) % 24; // Hours
        timetoevent[2] = (int) (diff / 60) % 60; // Minutes
        timetoevent[3] = (int) diff % 60; // Seconds

        return timetoevent;
    }

    public SearchedString<Widget> buscar(String cadena) {
        return new SearchedString<>(this, titulo, cadena);
    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public void close() throws IOException {

    }
}

