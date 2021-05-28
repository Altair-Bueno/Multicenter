package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.io.Serializable;
import java.util.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget extends JInternalFrame implements Widget, Serializable, Closeable , Comparable<AbstractWidget> {

    public static final Dimension STANDARD_DIMENSION = new Dimension(100, 100);

    // Variables de clase
    protected String id;
    protected int abstractLayer;
    protected Dimension abstractdimension;
    protected float abstractx;
    protected float abstracty;

    protected AbstractWidget(){
        super();
    }

    // Operaciones
    protected SearchedString<Widget> bestSearchedString(String frase, String ref, Widget w){
        SortedSet<SearchedString<Widget>> res = new TreeSet<>(Comparator.reverseOrder());
        Iterator<String> iter = Arrays.stream(frase.split("\\W+")).iterator();
        while(iter.hasNext()){
            res.add(new SearchedString<Widget>(w,frase,ref));
        }
        return res.first();
    }

    @Override
    public int compareTo(AbstractWidget o) {
        return this.abstractLayer - o.abstractLayer;
    }

    public String getId() {
        return id;
    }

    @Override
    public int getLayer() {
        return super.getLayer();
    }

    @Override
    public void setLayer(int capa) {
        super.setLayer(capa);
        abstractLayer = capa;
    }

    @Override
    public void setAlignmentX(float alignmentX) {
        super.setAlignmentX(alignmentX);
        abstractx = alignmentX;
    }

    @Override
    public void setAlignmentY(float alignmentY) {
        super.setAlignmentY(alignmentY);
        abstracty = alignmentY;
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        abstractdimension = d;
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return super.getSize(rv);
    }

    @Override
    public float getAlignmentX() {
        return super.getAlignmentX();
    }

    @Override
    public float getAlignmentY() {
        return super.getAlignmentY();
    }

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

    @Override
    public JInternalFrame getJInternalFrame() {
        super.setAlignmentX(abstractx);
        super.setAlignmentY(abstracty);
        super.setSize(abstractdimension);
        super.setLayer(abstractLayer);
        super.setLayout(new BorderLayout());
        return this;
    }
}
