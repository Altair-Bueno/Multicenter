package App.Multicenter.Widget;

public class CalendarWidget extends AbstractWidget{
    // TODO CalendarWidget Constructor

    public CalendarWidget(){
        c = Calendar.getInstance();
    }

    //TODO ¿Se podría prescindir del calendario?
    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        return null;
    }

}
