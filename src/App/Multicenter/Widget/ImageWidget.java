package App.Multicenter.Widget;


import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.ImageWidgetData;
import App.Multicenter.Widget.Data.WidgetData;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ImageWidget extends AbstractWidget {

    File imagesFolder; // Spacesfolder > Imagesfolder > Imagenes
    List<File> img; // Las imagenes mantienen el nombre original
    List<String> footer;

    protected ImageWidget (ImageWidgetData iwd){
        super(iwd);
        imagesFolder = new File(iwd.imagesFolder);
        img = Arrays.stream(iwd.images).map(File::new).collect(Collectors.toList());
        footer = Arrays.asList(iwd.footer);
        // TODO set view mode
    }

    public ImageWidget(File f) {
        RandomNameGenerator r = new RandomNameGenerator();
        id = r.generate(f);
        imagesFolder = new File(f,id);
        img = new ArrayList<>();
        footer = new ArrayList<>();
        // TODO set view mode
    }

    public SearchedString<Widget> search(String cadena) {
        if (footer.isEmpty()) return new SearchedString<>(this,"",cadena);
        return footer.stream().
                map(e -> bestSearchedString(e,cadena,this)).
                max(Comparator.naturalOrder()).
                get();
    }

    @Override
    public void toggleEditMode() {
        // TODO
    }

    @Override
    public void deleteWidget() {
        for (File i : img)
            i.delete();
        imagesFolder.delete();
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
        Files.move(imagesFolder.toPath(),new File(folder,id).toPath());
        imagesFolder = new File(folder,id);
        File[] array = imagesFolder.listFiles();
        if (array == null) throw new IOException("Something went wrong");
        img = Arrays.asList(array);
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        ImageWidgetData data = new ImageWidgetData();
        data.classname = IMAGE;
        data.imagesFolder = imagesFolder.getAbsolutePath();
        data.images = (String[]) img.stream().map(File::getAbsolutePath).toArray();
        data.footer = footer.toArray(new String[0]);
        return super.getWidgetsDataInstance(data);
    }


    @Override
    public void close() throws IOException {
        // Nothing to close lmao
    }
}
