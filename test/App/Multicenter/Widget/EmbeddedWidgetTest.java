package App.Multicenter.Widget;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


public class EmbeddedWidgetTest {
    public static void main(String[] args) {
        try {
            String youtubeUrl = "www.youtube.com/watch?v=ZVMTaAoO7OM";
            //URL embededURL = new URL("http://www.youtube.com/oembed?url=" + youtubeUrl + "&format=json");
            HttpResponse<String> response = Unirest.get("https://www.youtube.com/oembed?url=" + youtubeUrl + "&format=json").header("Accept", "application/json").asString();
            Gson g = new Gson();
            System.out.println(response.getBody());
            ytInfo a = g.fromJson(response.getBody(), ytInfo.class);
            a.mostrar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class ytInfo {
        private String title;
        private String thumbnail_url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public void mostrar() {
            System.out.println("Title: " + title);
            System.out.println("Url: " + thumbnail_url);
        }

    }

}

