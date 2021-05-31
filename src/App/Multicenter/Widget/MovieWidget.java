package App.Multicenter.Widget;

import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.MovieWidgetData;
import App.Multicenter.Widget.Data.WidgetData;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de operar con la información
 * necesaria para crear un widget de películas.
 * Este widget en principio solo nos enseñará el
 * título oficial y la valoración general de la
 * película que el usuario busque al crear el
 * widget.
 *
 */
public class MovieWidget extends AbstractWidget {

    private final JPanel jPanel = new JPanel();
    private final JButton busc = new JButton();
    private final JTextField errorMessage = new JTextField();
    private String title;
    private Double rating;
    private String URLImage;
    // Si el rating es "0.0", es porque la película está en la base de datos pero no tiene rating (No ha salido aún, por ejemplo)

    protected MovieWidget(MovieWidgetData mwd){
        super(mwd);
        super.add(jPanel);
    }

    public MovieWidget(String title){

        try{
            searchandSet(title);
        } catch(IllegalArgumentException e) {
            showErrorPopUp("Película no encontrada en la base de datos");
        }

    }

    public void showErrorPopUp(String text){
        JPanel panelt = new JPanel();
        JPanel panelb = new JPanel();

        errorMessage.setText(text);
        errorMessage.setHorizontalAlignment(0);
        errorMessage.setEditable(false);
        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(errorMessage.getFont().deriveFont(Font.BOLD, 14f)); // Bold
        errorMessage.setOpaque(false);

        busc.setText("Buscar de nuevo");
        busc.setPreferredSize(new Dimension(100,20));
        panelt.setLayout(new GridLayout(1,1));
        panelb.setLayout(new GridLayout(1,1));
        panelt.add(errorMessage);
        panelb.add(busc);


        super.add(panelt);
        super.add(panelb);
    }

    public void searchandSet(String title) throws IllegalArgumentException{
        // Búsqueda de title en la DB

        String searchUrl = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/search/" + title;

        HttpResponse<String> response = Unirest.get(searchUrl)
                .header("x-rapidapi-key", "abd2447dc9msh86d9476225f947bp1e9a90jsn1e5f6f17f5b1")
                .header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
                .asString();

        Gson gson = new Gson();
        SearchResult sr = gson.fromJson(response.getBody(), SearchResult.class);
        if(sr.getTitles().size() == 0){
            throw new IllegalArgumentException("Not found similar titles");
        }else{
            RandomNameGenerator r = new RandomNameGenerator();

            super.add(jPanel);

            String result = gson.toJson(gson.fromJson(response.getBody(), SearchResult.class).getTitles().get(0));
            SearchedTitle movie = gson.fromJson(result, SearchedTitle.class);

            // Coger rating con el id de la película

            HttpResponse<String> response2 = Unirest.get("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/film/" + movie.getId())
                    .header("x-rapidapi-key", "abd2447dc9msh86d9476225f947bp1e9a90jsn1e5f6f17f5b1")
                    .header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
                    .asString();
            try{
                Film f = gson.fromJson(response2.getBody(), Film.class);
                this.rating = f.getRating();
                this.title = f.getTitle();
            } catch (NumberFormatException e){
                this.rating = 0.0;
                this.title = movie.getTitle();
            }

        }
    }

    public String getTitle() {
        return title;
    }


    public Double getRating() {
        return rating;
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        MovieWidgetData mwd = new MovieWidgetData();
        mwd.classname = EMBEDDEDMOVIE;
        return super.getWidgetsDataInstance(mwd);
    }

    @Override
    public SearchedString<Widget> search(String cadena) {
        return null;
    }

    @Override
    public void toggleEditMode() {
        edit = !edit;
        if(edit){

        }
    }

    @Override
    public void deleteWidget() {
        id = null;
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    // AUX CLASSES

    private class Film{
        String title;
        Double rating;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Double getRating() {
            return rating;
        }

        public String getTitle() {
            return title;
        }

        public String toString(){
            return "Title: " + getTitle() + "; Rating: " + getRating();
        }
    }

    private class SearchedTitle {
        public String title, id;

        public String getTitle() {
            return title;
        }

        public String getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    private class SearchResult{
        List<Object> titles = new ArrayList<>();

        public void setTitles(List<Object> titles) {
            this.titles = titles;
        }

        public List<Object> getTitles() {
            return titles;
        }
    }

}
