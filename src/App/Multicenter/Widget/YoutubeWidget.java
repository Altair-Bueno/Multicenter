package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.WidgetData;
import App.Multicenter.Widget.Data.YoutubeWidgetData;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class YoutubeWidget extends AbstractWidget {
    // Constants
    private final String EDIT = "Pulsa EDITAR e introduce algo de nuevo.";
    private final String BLANKINPUTERROR = "No has introducido nada. " + EDIT;
    private final String NOTFOUNDERROR = "No se ha encontrado el vídeo dada esa URL. " + EDIT;
    private final String LOADINGERROR = "La información no se ha recibido correctamente. " + EDIT;
    private final JTextField Editor = new JTextField();

    // Vars
    private boolean isClickable = false;
    private JPanel PanelError;
    private JPanel Panel;
    private JLabel loadingIconText;

    // Fields
    private String thumbnail_url;
    private String title;
    private String video_url;
    private String author_name;

    protected YoutubeWidget(YoutubeWidgetData ywd) {
        super(ywd);
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/youtube.png"))));
        super.setSize(new Dimension(440, 400));
        super.setTitle("YouTube");
        super.setResizable(true);
        super.setClosable(true);
        this.video_url = ywd.video_url;
        Thread thread = new Thread(() -> {
            try {
                loading();
                loadYTdata(this.getVideo_url());
                setView();
            } catch (IOException ignored) {
                Editor.setEditable(false);
                super.add(Editor);
            }
        });
        thread.start();
    }


    public YoutubeWidget() {
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/youtube.png"))));
        super.setSize(new Dimension(440, 400));
        super.setTitle("YouTube");
        super.setClosable(true);
        super.repaint();
        Editor.setEditable(false);
        super.add(Editor);
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void showErrorPopUp(String text) {
        PanelError = new JPanel();
        JTextPane errorMessage = new JTextPane();

        errorMessage.setText(text);
        errorMessage.setEditable(false);
        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(errorMessage.getFont().deriveFont(Font.BOLD, 14f)); // Bold
        errorMessage.setOpaque(false);
        errorMessage.setPreferredSize(new Dimension(super.getWidth() - 10, super.getHeight() - 25));
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

    public void loadYTdata(String url) {
        if (!url.equals("")) {
            if (url.startsWith("https://")) {
                url = url.replace("https://", "");
            }

            HttpResponse<String> response = Unirest.get("https://www.youtube.com/oembed?url=" + url + "&format=json").header("Accept", "application/json").asString();
            Gson g = new Gson();
            ytInfo a = g.fromJson(response.getBody(), ytInfo.class);
            author_name = a.getAuthor_name();
            video_url = "https://" + url;
            title = a.getTitle();
            thumbnail_url = a.getThumbnail_url();
        }

    }

    public void loading() throws IOException {
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

    @Override
    public WidgetData getWidgetsDataInstance() {
        YoutubeWidgetData ywd = new YoutubeWidgetData();
        ywd.classname = EMBEDDEDYT;
        ywd.video_url = video_url;
        return super.getWidgetsDataInstance(ywd);
    }

    @Override
    public SearchedString<Widget> search(String cadena) {
        if (video_url == null) return new SearchedString<>(this, "", cadena);
        return new SearchedString<>(this, getTitle(), cadena);
    }

    @Override
    public void toggleEditMode() {
        edit = !edit;
        if (edit) {
            isClickable = false;
            Editor.setEditable(true);
            if (getVideo_url() != null) Editor.setText(getVideo_url());
            super.add(Editor);
            remView();
        } else {
            String search = Editor.getText();
            Editor.setEditable(false);
            try {
                if (search.isBlank()) {
                    this.video_url = null;
                    this.title = null;
                    this.thumbnail_url = null;
                    showErrorPopUp(BLANKINPUTERROR);
                } else {
                    loading();
                    if (!search.equals(getVideo_url())) {
                        loadYTdata(search);
                    }
                    setView();
                }
            } catch (IllegalArgumentException e) {
                showErrorPopUp(NOTFOUNDERROR);
            } catch (IOException e) {
                showErrorPopUp(LOADINGERROR);
            }
        }
        updateUI();
    }

    public void setView() throws IOException {
        Panel = new JPanel(); // Delete previous panel versions
        Panel.setLayout(new BorderLayout());

        Image im = ImageIO.read(new URL(getThumbnail_url()));
        im = im.getScaledInstance(350, 195, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel();
        poster.setIcon(new ImageIcon(im));
        poster.setText(
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "h2 {text-align: center;} span {color: yellow;} h1 {text-align: center}" +
                        "</style>" +
                        "</head>" +
                        "<h1>" +
                        this.getTitle() +
                        "</h1>" +
                        "<h2>" +
                        "by: " + this.getAuthor_name() +
                        "</h2>" +
                        "</html>"
        );
        poster.setVerticalTextPosition(JLabel.TOP);
        poster.setHorizontalTextPosition(JLabel.CENTER);
        poster.setIconTextGap(15);

        Border border = poster.getBorder();
        Border margin = new EmptyBorder(10, 30, 20, 10);
        poster.setBorder(new CompoundBorder(border, margin));

        poster.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isClickable) {
                    if (e.getClickCount() > 0) {
                        if (Desktop.isDesktopSupported()) {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                URI uri = new URI(video_url);
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
        setTitle("YouTube - " + this.getTitle());
        repaint();
    }

    public void remView() {
        if (isAncestorOf(Panel)) remove(Panel);
        if (isAncestorOf(PanelError)) remove(PanelError);
        if (isAncestorOf(loadingIconText)) remove(loadingIconText);
        setTitle("YouTube");
    }

    @Override
    public void deleteWidget() {
        title = null;
        thumbnail_url = null;
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
    }

    @Override
    public void close() throws IOException {
    }


//CLASES EXTRA PARA ALMACENAR INFORMACION GSON:

    private class ytInfo {
        protected String title;
        protected String thumbnail_url;
        protected String author_name;

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        @Override
        public String toString() {
            return "Título: " + getTitle();
        }
    }

}