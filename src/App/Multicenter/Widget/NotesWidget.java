package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Position;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.SortedSet;

public class NotesWidget extends AbstractWidget {
    // TODO EmbeddedWidget Constructor
    private final HyperlinkListener hyperlinkListener = a ->{
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
    JEditorPane jEditorPane;
    JPanel jPanel;
    File markdownFile;

    Parser parser = Parser.builder().build();
    HtmlRenderer renderer = HtmlRenderer.builder().build();
    boolean edit;

    public NotesWidget(int layer){
        // TODO Constructor
        // Crea una clase vacía de cero
        // this(randomgen,layer)...
    }

    public NotesWidget(String id, int layer, float x, float y, Dimension dimension,File file){
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

        try {
            renderMardown();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO Completar GUI de noteswidget
    }

    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        // TODO EmbeddedWidget buscar
        return null;
    }
    public boolean toggleEditMode(){
        edit = !edit;
        return toggleEditMode(edit);
    }
    /*
    private boolean toggleEditMode(boolean b){
        boolean out;
        try {
            if (b){
                jEditorPane.setPage(markdownFile.toURI().toURL());
                jEditorPane.removeHyperlinkListener(hyperlinkListener);
            }else {
                Writer writer = new OutputStreamWriter(new FileOutputStream(markdownFile));
                jEditorPane.write(writer);
                writer.close();
            }
            jEditorPane.setEditable(b);

            out = true;
        }catch (Exception e){
            out = false;
        }
        return out;
    }*/

    private boolean toggleEditMode(boolean b){
        // TODO
        boolean out;
        try {
            if (b){
                //jEditorPane.setContentType("text/html");
                editor();
            }else {
                save();
                renderMardown();
            }
            out = true;
        } catch (IOException e) {
            e.printStackTrace();
            out = false;
        }
        return out;
    }

    private void save() throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(markdownFile));
        jEditorPane.write(writer);
        writer.close();
    }
    private void renderMardown() throws IOException {
        InputStreamReader r = new InputStreamReader(new FileInputStream(markdownFile));
        Node document = parser.parseReader(r);
        jEditorPane.setContentType("text/html");
        jEditorPane.setText("<html>" + renderer.render(document) + "</html>");
        jEditorPane.addHyperlinkListener(hyperlinkListener);
        r.close();
        jEditorPane.setEditable(false);
    }
    private void editor() throws IOException {
        jEditorPane.setContentType("text/plain");
        jEditorPane.setPage(markdownFile.toURI().toURL());
        jEditorPane.removeHyperlinkListener(hyperlinkListener);
        jEditorPane.setEditable(true);
    }

}
