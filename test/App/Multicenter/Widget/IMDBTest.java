package App.Multicenter.Widget;

public class IMDBTest {

    public static void main(String[] args) {
        MovieWidget mw = new MovieWidget("Star Wars A New Hope");

        System.out.println("Title: " + mw.getTitle() + ", Rating: " + mw.getRating());
    }
}
