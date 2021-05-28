package App.Multicenter.Widget;

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
    private JEditorPane jEditorPane = new JEditorPane();
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private boolean edit;

    protected File markdownFile = null;

    protected NotesWidget() {
        // Serializable constructor
    }

    public NotesWidget(int layer, File spacesFolder) {
        // TODO Constructor
        // Crea una clase vacía de cero
        // this(randomgen,layer)...
        RandomNameGenerator r = new RandomNameGenerator();
        String id = r.generate(spacesFolder);
        super.id = id;
        setAlignmentX(0);
        setAlignmentY(0);
        setSize(STANDARD_DIMENSION);
        markdownFile = new File(spacesFolder, id);
        edit = false;
        jEditorPane = new JEditorPane();
        super.add(jEditorPane);
        renderMardown();
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

    @Override
    public JInternalFrame getJInternalFrame() {
        JInternalFrame jInternalFrame = super.getJInternalFrame();
        jInternalFrame.add(jEditorPane);
        return jInternalFrame;
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
