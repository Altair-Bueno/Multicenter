package App.Multicenter.Widget;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class IMDBTest {

    public static void main(String[] args) {
        MovieWidget mw = new MovieWidget();
        mw.searchandSet("Zootopia");

        System.out.println("Title: " + mw.getTitle() + ", Rating: " + mw.getRating());

        JFrame frame = new JFrame();
        JPanel panelt = new JPanel();
        JPanel panelb = new JPanel();
        JTextField jtf = new JTextField();
        JButton busc = new JButton();
        JButton deletewidg = new JButton();

        jtf.setText("Película no encontrada en la base de datos");
        jtf.setHorizontalAlignment(0);
        jtf.setEditable(false);
        jtf.setForeground(Color.RED);
        jtf.setFont(jtf.getFont().deriveFont(Font.BOLD, 14f)); // Bold
        jtf.setOpaque(false);

        busc.setText("Buscar de nuevo");
        busc.setPreferredSize(new Dimension(100,20));
        deletewidg.setText("Eliminar Widget");
        deletewidg.setPreferredSize(new Dimension(100,20));

        panelt.setLayout(new GridLayout(1,1));
        panelb.setLayout(new GridLayout(1,1));
        panelt.add(jtf);
        panelb.add(busc);

        frame.setLayout(new GridLayout(2,1));
        frame.add(panelt);
        frame.add(panelb);
        frame.setSize(500,100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);



    }
}
