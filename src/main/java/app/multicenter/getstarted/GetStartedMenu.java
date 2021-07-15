package app.multicenter.getstarted;

import app.multicenter.preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class GetStartedMenu extends JFrame {
    private final JPanel dialog = new JPanel();
    private final JPanel buttons = new JPanel();
    private final JButton next = new JButton();
    private final JButton previous = new JButton();
    private final Semaphore semaphore;
    ChooseSpaceFolderMenu jpanel0;
    ChooseMulticenterTheme jpanel1;
    private int currentPage = 0;

    public GetStartedMenu(Semaphore semaphore) {
        toFront();
        this.semaphore = semaphore;
        jpanel0 = new ChooseSpaceFolderMenu(this);
        jpanel1 = new ChooseMulticenterTheme(this);
        setLayout(new BorderLayout());
        dialog.setLayout(new BorderLayout());

        dialog.add(jpanel0, BorderLayout.CENTER);
        add(dialog, BorderLayout.NORTH);
        add(buttons, BorderLayout.PAGE_END);
        buttons.add(previous);
        buttons.add(next);

        next.setText("Siguiente");
        previous.setText("Anterior");
        next.addActionListener(e -> nextPage());
        previous.addActionListener(e -> previousPage());
        next.setVisible(true);
        previous.setVisible(true);
        previous.setEnabled(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(350, 190));
        setLocationRelativeTo(null);
        setResizable(false);
        Preferences.setTheme(0);
        SwingUtilities.updateComponentTreeUI(this);
        setVisible(true);
    }

    private void nextPage() {
        currentPage++;
        switch (currentPage) {
            case 1 -> {
                // Choose Theme
                Preferences.setSpacesFolder(jpanel0.getFileLocation());
                dialog.removeAll();
                dialog.add(jpanel1);
                previous.setEnabled(true);
                //setLocationRelativeTo(null);
                SwingUtilities.updateComponentTreeUI(this);
            }
            default -> {
                // Done
                semaphore.release();
                dispose();
            }
        }
    }

    private void previousPage() {
        currentPage--;
        switch (currentPage) {
            case 0 -> {
                // Choose spacefolder
                dialog.removeAll();
                dialog.add(jpanel0);
                previous.setEnabled(false);
                SwingUtilities.updateComponentTreeUI(this);
            }
            //setLocationRelativeTo(null);
            default -> throw new IllegalStateException("IDK man something is broken");
        }
    }
}
