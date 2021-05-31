package App.Multicenter.Widget;

public class IMDBTest {

    public static void main(String[] args) {
        MovieWidget mw = new MovieWidget("Zootopia");

        System.out.println("Title: " + mw.getTitle() + ", Rating: " + mw.getRating());
    }
}
