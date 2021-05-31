package App.Multicenter.Widget;

import App.Multicenter.Space.RandomNameGenerator;
import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.NotesWidgetData;
import App.Multicenter.Widget.Data.WidgetData;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.*;

/**
 * Widget para escribir notas, basado en Markdown.
 */

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
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private final JEditorPane jEditorPane = new JEditorPane();
    private File markdownFile;

    /**
     * Constructor de la clase que recibe el parámetro
     * @param nwd
     * del tipo NotesWidgetData, que a su vez contiene un String
     * del texto que se quiere escribir en el propio widget.
     */

    protected NotesWidget(NotesWidgetData nwd) {
        super(nwd);
        markdownFile = new File(nwd.markdownFile);
        super.add(jEditorPane);
        try {
            renderMardownMode();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Constructor de la clase, recibe como parámetro el archivo
     * @param spacesFolder
     * Y se encarga tanto de setear la gui, como de
     * renderizar el texto a Markdown.
     */

    public NotesWidget(File spacesFolder) {
        // TODO Constructor
        // Crea una clase vacía de cero
        // this(randomgen,layer)...
        RandomNameGenerator r = new RandomNameGenerator();
        String id = r.generate(spacesFolder);
        super.id = id + ".md";
        setAlignmentX(0);
        setAlignmentY(0);
        setSize(STANDARD_DIMENSION);
        markdownFile = new File(spacesFolder, super.id);
        try {
            markdownFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.add(jEditorPane);
        try {
            renderMardownMode();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        //setVisible(true);
    }

    /**
     * Método buscar:
     * @param cadena La cadena a buscar.
     * @return la cadena que más se parezca basada en el ratio a la que se le ha pasado por parámetro
     */

    public SearchedString<Widget> search(String cadena) {
        if (edit) throw new IllegalStateException("Edit mode on. Please disable edit view first");
        return super.bestSearchedString(jEditorPane.getText(), cadena, this);
    }

    /**
     * Activar o desactivar el modo edición dentro del Widget.
     * Si no está activo, no se podrá editar el texto que contiene.
     * Si está activo, se podrá editar.
     */

    public void toggleEditMode() {
        edit = !edit;
        try {
            if (edit) {
                editorMode();
            } else {
                saveIntoFile();
                renderMardownMode();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteWidget() {
        markdownFile.delete();
        markdownFile = null;
        id = null;
    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
        saveIntoFile();
        folder.mkdir();
        markdownFile.renameTo(new File(folder, id));
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        NotesWidgetData nwd = new NotesWidgetData();
        nwd.classname = NOTES;
        nwd.markdownFile = markdownFile.getAbsolutePath();
        return super.getWidgetsDataInstance(nwd);
    }

    /**
     * Guarda el markdownFile en un archivo.
     * @throws IOException
     */

    private void saveIntoFile() throws IOException {
        FileOutputStream out = new FileOutputStream(markdownFile);
        Writer writer = new OutputStreamWriter(out);
        jEditorPane.write(writer);
        writer.close();
    }

    /**
     * Método que primero, deja el widget en non-editable mode.
     * Y que posterioremente renderiza el texto plano a Markdown.
     * @throws IOException
     */

    private void renderMardownMode() throws IOException {
        jEditorPane.setEditable(false);
        InputStreamReader r = new InputStreamReader(new FileInputStream(markdownFile));
        Node document = parser.parseReader(r);
        jEditorPane.setContentType("text/html");
        String renderedHTML = renderer.render(document);
        jEditorPane.setText("<html>" + renderedHTML + "</html>");
        jEditorPane.addHyperlinkListener(hyperlinkListener);
        r.close();
    }

    /**
     * Modo editor, para añadir más texto plano para un posterior renderizado.
     * @throws IOException
     */

    private void editorMode() throws IOException {
        jEditorPane.setEditable(true);
        jEditorPane.removeHyperlinkListener(hyperlinkListener);
        jEditorPane.setContentType("text/plain");
        jEditorPane.setPage(markdownFile.toURI().toURL());
    }

    @Override
    public void close() throws IOException {
        saveIntoFile();
    }

    @Override
    public int compareTo(AbstractWidget o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return "NotesWidget{" +
                "id='" + id + '\'' +
                ", edit=" + edit +
                ", markdownFile=" + markdownFile +
                '}';
    }
}
