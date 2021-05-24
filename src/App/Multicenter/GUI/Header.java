package App.Multicenter.GUI;

import App.Multicenter.Space.PersonalSpace;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Header extends JPanel {
    //Atributtes
    String title;
    JLabel text;

    //Constructor
    /** 
     * Crea un objeto header con el titulo pasado por parametro
     * @param title String
     * 
     */
    public Header(String title) {
        this.title = title;
        setPreferredSize(new Dimension(100, 80));
        setBackground(new Color(Color.RED.getRGB()));
        setLayout(null);

        text = new JLabel();
        text.setText(title);
        text.setForeground(Color.lightGray);
        text.setBounds(30, 40, 200, 50);
        add(text);

        setVisible(true);
    }

    //Methods
    /** 
     * Cambia el titulo de la cabecera del espacio personal
     * @param newTitle String
     * 
     */
    public void changeTitle(String newTitle) {
        title = newTitle;
        updateUI();
    }

    /** 
     * Cambia el color de fondo de la cabecera del espacio personal
     * @param color Color
     * 
     */
    public void changeBackground(Color color) {
        
    }

    /** 
     * Cambia el color del titulo de la cabecera del espacio personal
     * @param color Color
     * 
     */
    public void changeTitleColor(Color color) {

    }
}
