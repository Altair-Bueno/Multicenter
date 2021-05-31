package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.WidgetData;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Youtube Widget, en su parte de lógica accede a la URL de un vídeo de youtube
 * y obtiene su contenido en formato JSON, posteriormente es tratado y parseado para
 * poder obtener datos tales como el título del vídeo, su miniatura, su autor o su fecha de creación.
 */

public class YoutubeWidget extends AbstractWidget{
    private Image thumbnail = null;
    private String title;

    protected YoutubeWidget(){

    }

    public YoutubeWidget(String url){
        HttpResponse<String> response = Unirest.get("https://www.youtube.com/oembed?url=" + url + "&format=json").header("Accept", "application/json").asString();
        Gson g = new Gson();
    }



    @Override
    public WidgetData getWidgetsDataInstance() {
        return null;
    }

    @Override
    public SearchedString<Widget> search(String cadena) {

    }

    @Override
    public void toggleEditMode() {

    }

    @Override
    public void deleteWidget() {

    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
