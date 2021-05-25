package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import java.util.Calendar;
import java.util.SortedSet;

public class CalendarWidget extends AbstractWidget{
    private Calendar c;

    public CalendarWidget(){
        c = Calendar.getInstance();
    }

    //TODO ¿Se podría prescindir del calendario?
    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        return null;
    }

}
