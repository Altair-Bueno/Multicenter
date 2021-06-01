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

    // Constants
    private final String EDIT = "Pulsa EDITAR e introduce algo de nuevo.";
    private final String BLANKINPUTERROR = "No has introducido nada. " + EDIT;
    private final String NOTFOUNDERROR = "Película no encontrada en la base de datos. " + EDIT;
    private final String LOADINGERROR = "Error obteniendo la información. " + EDIT;
    private final JTextField Editor = new JTextField();

    // Vars
    private boolean isClickable = false;
    private JPanel PanelError;
    private JPanel Panel;
    private JLabel loadingIconText;

    // Fields
    private String title;
    private Double rating;
    private String URLImage;
    private String filmid;
    // Si el rating es "0.0", es porque la película está en la base de datos pero no tiene rating (No ha salido aún, por ejemplo)

    protected MovieWidget(MovieWidgetData mwd){
        super(mwd);
        this.rating = 0.0;
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/claqueta.png"))));
        super.setSize(new Dimension(400, 350));
        super.setTitle("Película");
        super.setClosable(true);
        super.setResizable(false);
        this.filmid = mwd.filmid;
        Thread thread = new Thread(()->{
            try {
                loading();
                searchAndSetById(this.filmid);
                setView();
            } catch (IOException ignored) {
                Editor.setEditable(false);
                super.add(Editor);
            }
        });
        thread.start();
    }

    public MovieWidget(){
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/claqueta.png"))));
        super.setTitle("Película");
        super.setClosable(true);
        super.setResizable(false);
        super.repaint();

        Editor.setEditable(false);
        super.add(Editor);
    }

    public synchronized String getURLImage() {
        return URLImage;
    }

    public synchronized String getMovieTitle() {
        return title;
    }

    public synchronized String getFilmId() {
        return filmid;
    }

    public synchronized Double getRating() {
        return rating;
    }

    public synchronized void showErrorPopUp(String text){
        PanelError = new JPanel();
        JTextPane errorMessage = new JTextPane();

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

        PanelError.setLayout(new GridBagLayout());
        PanelError.setPreferredSize(super.getPreferredSize());
        PanelError.add(errorMessage, gbc);

        super.getContentPane().removeAll();
        super.revalidate();
        super.add(PanelError);
        repaint();
    }


    public synchronized void searchandSet(String title){
        // Búsqueda de title en la DB

        String searchUrl = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/search/" + title;

        HttpResponse<String> response = Unirest.get(searchUrl)
                    .header("x-rapidapi-key", "abd2447dc9msh86d9476225f947bp1e9a90jsn1e5f6f17f5b1")
                    .header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
                    .asString();

        Gson gson = new Gson();
        SearchResult sr = gson.fromJson(response.getBody(), SearchResult.class);
        if (sr.getTitles().size() == 0) {
            showErrorPopUp(NOTFOUNDERROR);
        } else {
            String result = gson.toJson(gson.fromJson(response.getBody(), SearchResult.class).getTitles().get(0));
            SearchedTitle movie = gson.fromJson(result, SearchedTitle.class);

            this.rating = 0.0;
            this.filmid = movie.getId();

            searchAndSetById(movie.getId());

            }
    }

    public synchronized void searchAndSetById(String id){
        // Coger rating con el id de la película
        Gson gson = new Gson();

        HttpResponse<String> response2 = Unirest.get("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/film/" + id)
                .header("x-rapidapi-key", "abd2447dc9msh86d9476225f947bp1e9a90jsn1e5f6f17f5b1")
                .header("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
                .asString();
        try{
            Film f = gson.fromJson(response2.getBody(), Film.class);
            this.rating = f.getRating();
            this.title = f.getTitle().substring(0,f.getTitle().length()-1);
            this.URLImage = f.getPoster();
        } catch (NumberFormatException ignored){ // already set by default on calling methods
        }
    }

    @Override
    public synchronized WidgetData getWidgetsDataInstance() {
        MovieWidgetData mwd = new MovieWidgetData();
        mwd.classname = EMBEDDEDMOVIE;
        mwd.filmid = getFilmId();
        return super.getWidgetsDataInstance(mwd);
    }

    @Override
    public SearchedString<Widget> search(String cadena) {
        if(getFilmId() == null) return new SearchedString<>(this, "", cadena);
        return new SearchedString<>(this, getMovieTitle(), cadena);
    }

    @Override
    public synchronized void toggleEditMode() {
        edit = !edit;
        if(edit){
            isClickable = false;
            Editor.setEditable(true);
            if(getMovieTitle() != null) Editor.setText(getMovieTitle());
            super.add(Editor);
            remView();
        }else{
            String search = Editor.getText().replaceAll("[^a-zA-Z0-9: -]+", "");
            Editor.setEditable(false);
            Thread thread  = new Thread(()->{
                try {
                    if(search.isBlank()){
                        showErrorPopUp(BLANKINPUTERROR);
                    }else {
                        loading();
                        if (!search.equals(this.getMovieTitle())) { // Evita volver a buscar si no has cambiado nada
                            searchandSet(search);
                        }
                        setView();
                    }
                } catch (IOException e) {
                    showErrorPopUp(LOADINGERROR);
                }
            });
            thread.start();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public synchronized void loading() throws IOException {
        loadingIconText = new JLabel("Cargando...", SwingConstants.CENTER);

        ImageIcon gif = new ImageIcon(ClassLoader.getSystemResource("App/Multicenter/Gifs/loadingWheel.gif"));
        gif = new ImageIcon(gif.getImage().getScaledInstance(super.getWidth() / 3, super.getHeight() / 3, Image.SCALE_DEFAULT));

        loadingIconText.setIcon(gif);

        loadingIconText.setFont(loadingIconText.getFont().deriveFont(50.0f));


        loadingIconText.setVerticalTextPosition(JLabel.BOTTOM);
        loadingIconText.setHorizontalTextPosition(JLabel.CENTER);
        loadingIconText.setHorizontalAlignment(SwingConstants.CENTER);
        loadingIconText.setVerticalAlignment(SwingConstants.CENTER);


        super.getContentPane().removeAll();
        super.validate();
        super.add(loadingIconText);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public synchronized void setView() throws IOException {
        Panel = new JPanel(); // Delete previous panel versions
        isClickable = true;

        Panel.setLayout(new BorderLayout());

        Image im = ImageIO.read(new URL(getURLImage()));

        im = im.getScaledInstance(170, 250, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel();
        poster.setIcon(new ImageIcon(im));
        poster.setText(
                "<html>" +
                        "<head>" +
                            "<style>" +
                                "h2 {text-align: center;} span {color: yellow;} h1 {text-align: center;}" +
                            "</style>" +
                        "</head>" +
                        "<h1>" +
                            this.getMovieTitle() +
                        "</h1>" +
                        "<h2>" +
                            "<span>&#11088</span>Valoración: " + this.getRating() +
                        "</h2>" +
                "</html>"
        );
        poster.setVerticalTextPosition(JLabel.CENTER);
        poster.setHorizontalTextPosition(JLabel.LEFT);
        poster.setIconTextGap(30);

        Border border = poster.getBorder();
        Border margin = new EmptyBorder(10,30,20,10);
        poster.setBorder(new CompoundBorder(border, margin));

        poster.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(isClickable){
                    if (e.getClickCount() > 0) {
                        if (Desktop.isDesktopSupported()) {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                URI uri = new URI("https://www.imdb.com/title/" + getFilmId() + "/");
                                desktop.browse(uri);
                            } catch (IOException | URISyntaxException ex) {
                                // do nothing
                            }
                        }

                    }

                }
            }
        });

        Panel.add(poster, BorderLayout.CENTER);
        super.getContentPane().removeAll();
        super.revalidate();
        super.add(Panel);
        setTitle("Película - " + this.getMovieTitle());
        repaint();
    }

    public synchronized void remView() {
        if(isAncestorOf(Panel)) remove(Panel);
        if(isAncestorOf(PanelError)) remove(PanelError);
        if(isAncestorOf(loadingIconText)) remove(loadingIconText);
        setTitle("Película");
    }

    @Override
    public void deleteWidget() {
        id = null;
    }

    @Override
    public void moveFilesToFolder(File folder) {
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
