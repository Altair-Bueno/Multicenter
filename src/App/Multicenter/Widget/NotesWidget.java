package App.Multicenter.Widget;

import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.*;

public class NotesWidget extends AbstractWidget {
    // TODO EmbeddedWidget Constructor

    private final HyperlinkListener hyperlinkListener = a -> {
        if (HyperlinkEvent.EventType.ACTIVATED.equals(a.getEventType())) {
            System.out.println(a.getURL());
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(a.getURL().toURI());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    private final JEditorPane jEditorPane;
    private final JPanel jPanel;
    private final File markdownFile;

    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private boolean edit;

    public NotesWidget() {
        this(0, Preferences.getSpacesFolder());
    }

    public NotesWidget(int layer, File spacesFolder) {
        // TODO Constructor
        // Crea una clase vacía de cero
        // this(randomgen,layer)...
        RandomNameGenerator r = new RandomNameGenerator();
        String id = r.generate(spacesFolder);

        this.id = id;
        this.layer = layer;
        setAlignmentX(0);
        setAlignmentY(0);
        setSize(STANDARD_DIMENSION);
        markdownFile = new File(spacesFolder, id);
        edit = false;

        jEditorPane = new JEditorPane();
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jEditorPane);
        add(jPanel);

        renderMardown();
    }

    public NotesWidget(String id, int layer, float x, float y, Dimension dimension, File file) {
        // TODO Constructor
        this.id = id;
        this.layer = layer;
        setAlignmentX(x);
        setAlignmentY(y);
        setSize(dimension);
        markdownFile = file;
        edit = false;

        jEditorPane = new JEditorPane();
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jEditorPane);
        add(jPanel);

        renderMardown();

        // TODO Completar GUI de noteswidget
    }

    public SearchedString<Widget> buscar(String cadena) {
        return super.bestSearchedString(jEditorPane.getText(),cadena,this);
    }

    public void toggleEditMode() {
        edit = !edit;
        if (edit) {
            editor();
        } else {
            save();
            renderMardown();
        }
    }

    private void save() {
        try (FileOutputStream out = new FileOutputStream(markdownFile)) {
            Writer writer = new OutputStreamWriter(out);
            jEditorPane.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renderMardown() {
        try (InputStreamReader r = new InputStreamReader(new FileInputStream(markdownFile))) {
            Node document = parser.parseReader(r);
            jEditorPane.setContentType("text/html");
            String renderedHTML = renderer.render(document);
            jEditorPane.setText("<html>" + renderedHTML + "</html>");
            jEditorPane.addHyperlinkListener(hyperlinkListener);
            jEditorPane.setEditable(false);
        } catch (Exception ignored) {
        }

    }

    private void editor() {
        try {
            jEditorPane.setContentType("text/plain");
            jEditorPane.setPage(markdownFile.toURI().toURL());
            jEditorPane.removeHyperlinkListener(hyperlinkListener);
            jEditorPane.setEditable(true);
        } catch (Exception ignored) {
        }

    }

    @Override
    public void close() throws IOException {
        save();
    }
}
