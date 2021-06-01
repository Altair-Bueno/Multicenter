package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.MovieWidgetData;
import App.Multicenter.Widget.Data.WidgetData;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

    private final JPanel PanelError = new JPanel();
    private final JButton busc = new JButton();
    private final JTextPane errorMessage = new JTextPane();
    private final JEditorPane Editor = new JEditorPane();
    private JPanel Panel = new JPanel();
    private final JPanel loadingPanel = new JPanel();
    private final JLabel loadingIconText = new JLabel();

    private String title;
    private Double rating;
    private String URLImage;
    private String id;
    // Si el rating es "0.0", es porque la película está en la base de datos pero no tiene rating (No ha salido aún, por ejemplo)

    protected MovieWidget(MovieWidgetData mwd){
        super(mwd);
        this.rating = 0.0;
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/claqueta.png"))));
        super.setSize(new Dimension(400, 350));
        super.setTitle("Película");
        super.repaint();
        try {
            loading();
        } catch (IOException ignored) {
        }
        searchAndSetById(mwd.filmid);
        try {
            setView();
        } catch (IOException e) {
            Editor.setEditable(false);
            super.add(Editor);
        }
    }

    public MovieWidget(){
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/claqueta.png"))));
        super.setSize(new Dimension(400, 350));
        super.setTitle("Película");
        super.repaint();
        Editor.setEditable(false);
        super.add(Editor);
    }

    public String getURLImage() {
        return URLImage;
    }

    public String getMovieTitle() {
        return title;
    }

    public String getFilmId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public void showErrorPopUp(String text){
        JPanel panelt = new JPanel();
        JPanel panelb = new JPanel();

        errorMessage.setText(text);
        errorMessage.setEditable(false);
        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(errorMessage.getFont().deriveFont(Font.BOLD, 14f)); // Bold
        errorMessage.setOpaque(false);
        errorMessage.setPreferredSize(new Dimension(super.getWidth()-10, super.getHeight()-25));
        StyledDocument doc = errorMessage.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        errorMessage.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
        errorMessage.setAlignmentY(JTextPane.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        busc.setText("Buscar de nuevo");
        busc.setPreferredSize(new Dimension(super.getWidth()-10, 20));
        busc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botonPulsado();
            }
        } );;

        panelt.add(errorMessage);
        panelb.add(busc);

        PanelError.setLayout(new GridBagLayout());
        PanelError.setPreferredSize(super.getPreferredSize());
        PanelError.add(panelt, gbc);
        PanelError.add(panelb);
        super.getContentPane().removeAll();
        super.revalidate();
        super.add(PanelError);
        //super.pack();
    }

    public void botonPulsado(){
        super.remove(PanelError);
        toggleEditMode();
    }

    public void searchandSet(String title) throws IllegalArgumentException{
        try {
            loading();
        } catch (IOException ignored) {
        }

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
            this.title = movie.title;
            this.rating = 0.0;
            this.id = movie.getId();

            searchAndSetById(movie.getId());

        }
    }

    public void searchAndSetById(String id){
        // Coger rating con el id de la película
        Gson gson = new Gson();

        HttpResponse<String> response2 = Unirest.get("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/film/" + id)
                .header("x-rapidapi-key", "abd2447dc9msh86d9476225f947bp1e9a90jsn1e5f6f17f5b1")
                .header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
                .asString();
        try{
            Film f = gson.fromJson(response2.getBody(), Film.class);
            this.rating = f.getRating();
            this.title = f.getTitle();
            this.URLImage = f.getPoster();
        } catch (NumberFormatException ignored){ // already set by default on calling methods
        }
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        MovieWidgetData mwd = new MovieWidgetData();
        mwd.classname = EMBEDDEDMOVIE;
        mwd.filmid = getFilmId();
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
            Editor.setEditable(true);
            super.add(Editor);
            remView();
        }else{
            String search = Editor.getText();
            Editor.setEditable(false);
            try{
                searchandSet(search);
                setView();
            } catch(IllegalArgumentException e) {
                showErrorPopUp("Película no encontrada en la base de datos");
            } catch (IOException e) {
                showErrorPopUp("La información no se ha recibido correctamente");
            }
        }
        updateUI();
    }

    public void loading() throws IOException {
        loadingPanel.setLayout(new GridLayout(1, 1));

        ImageIcon gif = new ImageIcon(ClassLoader.getSystemResource("App/Multicenter/Gifs/loadingWheel.gif"));
        gif = new ImageIcon(gif.getImage().getScaledInstance(super.getWidth() / 5, super.getHeight() / 5, Image.SCALE_DEFAULT));

        loadingIconText.setIcon(gif);
        loadingIconText.setText("Cargando...");
        loadingIconText.setVerticalTextPosition(JLabel.BOTTOM);
        loadingIconText.setHorizontalTextPosition(JLabel.CENTER);

        Border border = loadingIconText.getBorder();
        Border margin = new EmptyBorder(10,30,20,10);

        loadingIconText.setBorder(new CompoundBorder(border, margin));

        loadingPanel.add(loadingIconText);
        super.getContentPane().removeAll();
        super.validate();
        super.add(loadingPanel);

    }

    public void setView() throws IOException {
        Panel = new JPanel(); // Delete previous panel versions
        Panel.setLayout(new BorderLayout());

        Image im = ImageIO.read(new URL(getURLImage()));
        int w = im.getWidth(null);
        int h = im.getHeight(null);
        System.out.println("W: " + w + " H: " + h);

        //im = im.getScaledInstance(im.getWidth(null) / 10, im.getHeight(null) / 10, Image.SCALE_SMOOTH);
        im = im.getScaledInstance(170, 250, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel();
        poster.setIcon(new ImageIcon(im));
        poster.setText("<html><head>" + "<style>h2 {text-align: center;} span {color: yellow;}</style>" + "</head><h2>" + this.getMovieTitle() + "</h2>" + "<h2><span>&#11088</span>Valoración: " + this.getRating() + "</h2></html>");
        poster.setVerticalTextPosition(JLabel.CENTER);
        poster.setHorizontalTextPosition(JLabel.LEFT);
        poster.setIconTextGap(30);

        Border border = poster.getBorder();
        Border margin = new EmptyBorder(10,30,20,10);
        poster.setBorder(new CompoundBorder(border, margin));

        poster.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            URI uri = new URI("https://www.imdb.com/title/" + getFilmId() + "/");
                            desktop.browse(uri);
                        } catch (IOException ex) {
                            // do nothing
                        } catch (URISyntaxException ex) {
                            //do nothing
                        }
                    }

                }
            }
        });

        Panel.add(poster, BorderLayout.CENTER);
        super.getContentPane().removeAll();
        super.revalidate();
        setSize(new Dimension(400, 350));
        super.add(Panel);
        setTitle("Película - " + this.getMovieTitle());
        repaint();
    }

    public void remView() {
        remove(Panel);
        setTitle("Película");
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

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        String poster;


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
