package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class GetStartedMenu extends JFrame {
    ChooseSpaceFolderMenu jpanel0;
    ChooseMulticenterTheme jpanel1;

    private JPanel dialog = new JPanel();
    private JPanel buttons = new JPanel();
    private JButton next = new JButton();
    private JButton previous = new JButton();

    private int currentPage = 0;
    private Semaphore semaphore;

    public GetStartedMenu(Semaphore semaphore){
        this.semaphore = semaphore;
        jpanel0 = new ChooseSpaceFolderMenu();
        jpanel0.setVisible(true);
        jpanel1 = new ChooseMulticenterTheme();
        jpanel1.setVisible(true);
        setLayout(new BorderLayout());

        dialog.add(jpanel0); // TODO NOT SHOWING UP
        add(dialog);
        add(buttons);
            buttons.add(previous);
            buttons.add(next);

        next.setText("Siguiente");
        previous.setText("Anterior");
        next.addActionListener(e->nextPage());
        previous.addActionListener(e->previousPage());
        next.setVisible(true);
        previous.setVisible(true);
        previous.setEnabled(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void nextPage(){
        currentPage++;
        switch (currentPage){
            case 1:
                // Choose Theme
                Preferences.setSpacesFolder(jpanel0.getFileLocation());
                dialog.removeAll();
                dialog.add(jpanel1);
                previous.setEnabled(true);
                break;
            default:
                // Done
                Preferences.setTheme(jpanel1.getThemeCode());
                semaphore.release();
                dispose();
        }
        pack();
    }

    private void previousPage(){
        currentPage--;
        switch (currentPage){
            case 0:
                // Choose spacefolder
                dialog.removeAll();
                dialog.add(jpanel0);
                previous.setEnabled(false);
                break;
            default:
                throw new IllegalStateException("IDK man something is broken");
        }
        pack();
    }
}
