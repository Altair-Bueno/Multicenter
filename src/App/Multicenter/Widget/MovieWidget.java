package App.Multicenter.Widget;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class MovieWidget {
    private String title;
    private Double rating;
    // Si el rating es "0.0", es porque la película está en la base de datos pero no tiene rating (No ha salido aún, por ejemplo)

    protected MovieWidget(String title){
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
