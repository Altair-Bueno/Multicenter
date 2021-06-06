package App.Multicenter.Widget;


import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.ImageWidgetData;
import App.Multicenter.Widget.Data.WidgetData;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.comparator.ExtensionFileComparator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase encargada de operar con la información
 * necesaria para crear un widget de imágenes.
 * Este widget nos enseñará un carrusel de
 * imágenes en la cual el usuario podrá ir
 * cambiando de imagen visualizada con dos
 * botones de desplazamiento.
 *
 * @see AbstractWidget
 */
public class ImageWidget extends AbstractWidget {
    // TODO Mejor usamos un widget unico en vez de un carrousel
    private final List<String> footer;
    private File imagesFolder; // Spacesfolder > Imagesfolder > Imagenes
    private List<File> img; // Las imagenes mantienen el nombre original
    private int carrouselLocation;
    private File[] imgselected;
    private JButton button = new JButton("Examinar");
    private JButton derecha = new JButton("<<");
    private JButton izquierda = new JButton(">>");

    /**
     * Constructor del widget al que le llega por
     * parámetro un objeto tipo ImageWidgetData.
     *
     * @param iwd
     */
    protected ImageWidget(ImageWidgetData iwd) {
        super(iwd);
        super.setSize(new Dimension(500, 450));
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/imagen.png"))));
        super.setClosable(true);
        imagesFolder = new File(iwd.imagesFolder);
        img = new ArrayList<>(Arrays.stream(iwd.images).map(File::new).collect(Collectors.toList()));
        footer = new ArrayList<>(Arrays.asList(iwd.footer));
        carrouselLocation = 0;
        showPanel();
    }

    /**
     * Constructor del widget al que le llega por
     * parámetro un archivo tipo File.
     *
     * @param f
     */
    public ImageWidget(File f) {
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/imagen.png"))));
        super.setClosable(true);
        RandomNameGenerator r = new RandomNameGenerator();
        id = r.generate(f);
        imagesFolder = new File(f, id);
        img = new ArrayList<>();
        footer = new ArrayList<>();
        imagesFolder.mkdir();
        carrouselLocation = 0;
        showPanel();

    }

    public SearchedString<Widget> search(String cadena) {
        if (footer.isEmpty()) return new SearchedString<>(this, "", cadena);
        return footer.stream().
                map(e -> bestSearchedString(e, cadena, this)).
                max(Comparator.naturalOrder()).
                get();
    }

    /**
     * Método que desplaza la imagen visualizada
     * a la de la izquierda al accionar el botón
     * correspondiente (izquierdo).
     */
    private void moveLeft() {
        carrouselLocation = carrouselLocation == 0 ?
                (carrouselLocation = img.size() - 1) :
                (carrouselLocation - 1);
        System.out.println("Left 1: " + carrouselLocation);
        //showPanel(carrouselLocation);
    }

    /**
     * Método que desplaza la imagen visualizada
     * a la de la derecha al accionar el botón
     * correspondiente (derecho).
     */
    private void moveRight() {
        carrouselLocation = (1 + carrouselLocation) % img.size();
        System.out.println("Right 1: " + carrouselLocation);
    }

    /**
     * Método que hace visible el panel del widget
     * al usuario.
     *
     */
    private void showPanel() {
        JLabel image;

        if (img.isEmpty()) {
            // Placeholder
            image = new JLabel("Add photos using edit mode",
                    new ImageIcon(ClassLoader.getSystemResource("App/Multicenter/Placeholder/Photos/placeholderImagewidget.png")),
                    SwingConstants.LEADING);

            image.setHorizontalTextPosition(JLabel.CENTER);
            image.setVerticalTextPosition(JLabel.BOTTOM);

            super.getContentPane().removeAll();
            super.validate();
            super.add(image);
            SwingUtilities.updateComponentTreeUI(this);
        } else {
            JPanel panel = new JPanel();

            ImageIcon temp = new ImageIcon(
                    new ImageIcon(
                            img.get(carrouselLocation).getAbsolutePath())
                            .getImage()
                            .getScaledInstance(711, 400, Image.SCALE_SMOOTH)
            );

            image = new JLabel(footer.get(carrouselLocation),
                    temp,
                    SwingConstants.LEADING);

            izquierda.addActionListener(e -> {
                moveLeft();
                image.setIcon(new ImageIcon(
                        new ImageIcon(
                                img.get(carrouselLocation).getAbsolutePath())
                                .getImage()
                                .getScaledInstance(711, 400, Image.SCALE_SMOOTH)
                ));
                image.setText(footer.get(carrouselLocation));
            });

            derecha.addActionListener(e -> {
                moveRight();
                image.setIcon(new ImageIcon(
                        new ImageIcon(
                                img.get(carrouselLocation).getAbsolutePath())
                                .getImage()
                                .getScaledInstance(711, 400, Image.SCALE_SMOOTH)
                ));
                image.setText(footer.get(carrouselLocation));
            });

            image.setHorizontalTextPosition(JLabel.CENTER);
            image.setVerticalTextPosition(JLabel.BOTTOM);

            panel.add(derecha);
            panel.add(image);
            panel.add(izquierda);

            super.getContentPane().removeAll();
            super.validate();
            super.add(panel);
            super.pack();
            SwingUtilities.updateComponentTreeUI(this);
        }

    }

    /**
     * Método que visualiza el botón de
     * examinar para seleccionar el archivo
     * o archivos de imagen.
     */
    public void panelexaminar(){
        JPanel panelAñadir = new JPanel();
        JTextField dir = new JTextField();

        button.addActionListener(e -> {

            FileFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());

            JFileChooser fc = new JFileChooser();


            fc.addChoosableFileFilter(imageFilter);
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setMultiSelectionEnabled(true);
            int out = fc.showDialog(getParent(), "Seleccionar imagen");
            fc.setVisible(true);

            if (out == JFileChooser.APPROVE_OPTION) {
                imgselected = fc.getSelectedFiles();
            }

        });

        panelAñadir.add(button);

        // Actualizar
        super.getContentPane().removeAll();
        super.validate();
        super.add(panelAñadir);
        SwingUtilities.updateComponentTreeUI(this);
    }


    /**
     * Método toggle que determina el modo de
     * visualización del widget.
     */
    @Override
    public void toggleEditMode() {
        // TODO
        edit = !edit;
        if (edit) {
            // Editmode
            panelexaminar();
        } else {
            // Save changes
            if(imgselected!=null){
                moveFilesToFolder(imagesFolder);
            }
            // View mode
            showPanel();
        }
    }

    /**
     * Método que borra el widget.
     */
    @Override
    public void deleteWidget() {
        for (File i : img)
            i.delete();
        imagesFolder.delete();
    }

    /**
     * Método que copia la imagen seleccionada
     * del PC del usuario en la carpeta que se
     * crea de la aplicación destinada a
     * almacenar las imágenes del widget.
     *
     * @param folder carpeta a la que mover los archivos
     * @throws IOException
     */
    @Override
    public void moveFilesToFolder(File folder){
        RandomNameGenerator rng = new RandomNameGenerator();


        for(int i = 0; i < imgselected.length; i++){
            File f = imgselected[i];
            String ext = FilenameUtils.getExtension(f.getName());
            String imagename = img.size() + "_" + rng.generate(folder, "." + ext);

            try {
                File temp = new File(folder, imagename);
                Files.copy(f.toPath(), temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                img.add(temp);
                String[] pie = f.getName().split("\\.");
                footer.add(pie[0]);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    /**
     * Método que utiliza la clase ImageWidgetData
     * que implementa la clase Serializable y
     * recoge sus atributos.
     *
     * @return DataInstance del widget
     */
    @Override
    public WidgetData getWidgetsDataInstance() {
        ImageWidgetData data = new ImageWidgetData();
        data.classname = IMAGE;
        data.position = carrouselLocation;
        data.imagesFolder = imagesFolder.getAbsolutePath();
        data.images = new String[img.size()];
        data.footer = new String[img.size()];
        for (int i = 0; i < img.size(); i++) {
            data.images[i] = img.get(i).getAbsolutePath();
            data.footer[i] = footer.get(i);
        }
        //data.images = (String[]) img.stream().map(File::getAbsolutePath).toArray();
        //data.footer = footer.toArray(new String[0]);
        return super.getWidgetsDataInstance(data);
    }


    @Override
    public void close() throws IOException {
        // Nothing to close lmao
    }
}
