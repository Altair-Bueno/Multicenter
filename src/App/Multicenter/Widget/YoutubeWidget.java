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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

public class YoutubeWidget extends AbstractWidget {
    private final JPanel PanelError = new JPanel();
    private final JButton busc = new JButton();
    private final JTextPane errorMessage = new JTextPane();
    private final JEditorPane Editor = new JEditorPane();
    private JPanel Panel = new JPanel();
    private final JPanel loadingPanel = new JPanel();
    private final JLabel loadingIconText = new JLabel();

    private String thumbnail_url;
    private String title;
    private String video_url;

    protected YoutubeWidget(YoutubeWidgetData ywd) {
        super(ywd);
        super.setFrameIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("App/Multicenter/Icons/WidgetIcons/youtube.png"))));
        super.setSize(new Dimension(400,350));
        super.setTitle("Youtube");
        //super.repaint();
        this.video_url = ywd.video_url;
        Thread thread = new Thread(()->{
            try {
                loading();
                loadYTdata(this.video_url);
                super.setResizable(true);
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
        super.setSize(new Dimension(400,350));
        super.setTitle("Youtube");
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

    public String getVideo_url() {
        return video_url;
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
        } );

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

    public void loadYTdata(String url){
        if(!url.equals("")){
            if(url.substring(0,8).equals("https://")){
                url = url.replace("https://", "");
            }
            try{
                loading();
            } catch (IOException ign){}

            HttpResponse<String> response = Unirest.get("https://www.youtube.com/oembed?url=" + url + "&format=json").header("Accept", "application/json").asString();
            Gson g = new Gson();
            ytInfo a = g.fromJson(response.getBody(), ytInfo.class);
            video_url = "https://"+url;
            title = a.getTitle();
            thumbnail_url = a.getThumbnail_url();
        }

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
                loadYTdata(search);
                setView();
            } catch(IllegalArgumentException e) {
                showErrorPopUp("No se ha encontrado el vídeo dada esa URL");
            } catch (IOException e) {
                showErrorPopUp("La información no se ha recibido correctamente");
            }
        }
        updateUI();
    }

    public void setView() throws IOException {
        Panel = new JPanel(); // Delete previous panel versions
        Panel.setLayout(new BorderLayout());

        Image im = ImageIO.read(new URL(getThumbnail_url()));
        //int w = im.getWidth(null);
        //int h = im.getHeight(null);
        //System.out.println("W: " + w + " H: " + h);R

        //im = im.getScaledInstance(im.getWidth(null) / 10, im.getHeight(null) / 10, Image.SCALE_SMOOTH);
        im = im.getScaledInstance(480, 360, Image.SCALE_SMOOTH);
        JLabel poster = new JLabel();
        poster.setIcon(new ImageIcon(im));
        poster.setText(
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "h2 {text-align: center;} span {color: yellow;}" +
                        "</style>" +
                        "</head>" +
                        "<h1>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;" + this.getTitle() +
                        "</h1>" +
                "</html>"
        );
        poster.setVerticalTextPosition(JLabel.TOP);
        poster.setHorizontalTextPosition(JLabel.CENTER);
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
                            URI uri = new URI(video_url);
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
        setTitle(this.getTitle());
        repaint();
    }

    public void remView() {
        remove(Panel);
        setTitle("Youtube");
    }

    @Override
    public void deleteWidget() {
        title = null;
        thumbnail_url = null;
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException { }

    @Override
    public void close() throws IOException { }


//CLASES EXTRA PARA ALMACENAR INFORMACION GSON:

    private class ytInfo{
        protected String title;
        protected String thumbnail_url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setThumbnail_url(String thumnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public String getTitle() {
            return title;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        @Override
        public String toString() {
            return "Título: " + getTitle();
        }
    }

}