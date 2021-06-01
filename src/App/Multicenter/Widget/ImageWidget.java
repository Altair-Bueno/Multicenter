package App.Multicenter.Widget;


import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.ImageWidgetData;
import App.Multicenter.Widget.Data.WidgetData;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ImageWidget extends AbstractWidget {

    private final List<String> footer;
    private final JPanel contentPanel = new JPanel();
    private File imagesFolder; // Spacesfolder > Imagesfolder > Imagenes
    private List<File> img; // Las imagenes mantienen el nombre original
    private int carrouselLocation = 0;

    protected ImageWidget(ImageWidgetData iwd) {
        super(iwd);
        imagesFolder = new File(iwd.imagesFolder);
        img = Arrays.stream(iwd.images).map(File::new).collect(Collectors.toList());
        footer = Arrays.asList(iwd.footer);
        showPanel(iwd.position);

        add(contentPanel);
        // TODO set view mode
    }

    public ImageWidget(File f) {
        RandomNameGenerator r = new RandomNameGenerator();
        id = r.generate(f);
        imagesFolder = new File(f, id);
        img = new ArrayList<>();
        footer = new ArrayList<>();
        showPanel(0);

        add(contentPanel);
        // TODO set view mode
    }

    public SearchedString<Widget> search(String cadena) {
        if (footer.isEmpty()) return new SearchedString<>(this, "", cadena);
        return footer.stream().
                map(e -> bestSearchedString(e, cadena, this)).
                max(Comparator.naturalOrder()).
                get();
    }

    private void moveleft() {
        carrouselLocation = carrouselLocation == 0 ?
                (carrouselLocation = img.size() - 1) :
                (carrouselLocation - 1);
        showPanel(carrouselLocation);
    }

    private void moveRight() {
        carrouselLocation = (1 + carrouselLocation) % img.size();
        showPanel(carrouselLocation);
    }

    private void showPanel(int position) {
        JLabel image;
        if (img.isEmpty()) {
            // Placeholder
            image = new JLabel("Add photos using edit mode",
                    new ImageIcon(ClassLoader.getSystemResource("App/Multicenter/Placeholder/Photos/placeholderImagewidget.png")),
                    SwingConstants.LEADING);
        } else {
            image = new JLabel(footer.get(position),
                    new ImageIcon(ClassLoader.getSystemResource(img.get(position).getAbsolutePath())),
                    SwingConstants.LEADING);
        }
        JPanel panel = new JPanel();
        panel.add(image);
        contentPanel.removeAll();
        contentPanel.add(panel);
    }

    @Override
    public void toggleEditMode() {
        // TODO
        edit = !edit;
        if (edit) {
            // Editmode
        } else {
            // Save changes
            // View mode
        }
    }

    @Override
    public void deleteWidget() {
        for (File i : img)
            i.delete();
        imagesFolder.delete();
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
        Files.move(imagesFolder.toPath(), new File(folder, id).toPath());
        imagesFolder = new File(folder, id);
        File[] array = imagesFolder.listFiles();
        if (array == null) throw new IOException("Something went wrong");
        img = Arrays.asList(array);
    }

    @Override
    public WidgetData getWidgetsDataInstance() { // TODO Exception when empty
        ImageWidgetData data = new ImageWidgetData();
        data.classname = IMAGE;
        data.position = carrouselLocation;
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
