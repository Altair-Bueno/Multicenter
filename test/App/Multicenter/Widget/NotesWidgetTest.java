package App.Multicenter.Widget;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NotesWidgetTest {
    public static void main(String [] args){
        JFrame jFrame = new JFrame();
        JDesktopPane jDesktopPane = new JDesktopPane();
        jDesktopPane.setSize(new Dimension(500,500));
        JPanel jPanel = new JPanel();
        NotesWidget notesWidget = new NotesWidget("3242",0,10,10,new Dimension(300,300),new File("/Users/compux72/Github/Multicenter/test/App/Multicenter/Widget/test.md"));
        jFrame.add(jPanel);
        jPanel.add(jDesktopPane);
        jDesktopPane.add(notesWidget);

        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
            boolean b =notesWidget.toggleEditMode();
            //System.out.println(b);
        });

        jPanel.add(edit);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.pack();
        notesWidget.setVisible(true);
        jDesktopPane.setVisible(true);
        jFrame.setVisible(true);
    }
}
