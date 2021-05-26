package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.io.IOException;
import java.util.Calendar;

public class CalendarWidget extends AbstractWidget {
    private final Calendar c;

    public CalendarWidget() {
        c = Calendar.getInstance();
    }

    public SearchedString<Widget> buscar(String cadena) {
        return null;
    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public void close() throws IOException {

    }
}
