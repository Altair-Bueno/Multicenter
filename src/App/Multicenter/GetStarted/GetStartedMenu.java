package App.Multicenter.GetStarted;

import App.Multicenter.Preferences.Preferences;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class GetStartedMenu extends JFrame {
    ChooseSpaceFolderMenu jpanel0;
    ChooseMulticenterTheme jpanel1;

    private final JPanel dialog = new JPanel();
    private final JPanel buttons = new JPanel();
    private final JButton next = new JButton();
    private final JButton previous = new JButton();

    private int currentPage = 0;
    private final Semaphore semaphore;

    public GetStartedMenu(Semaphore semaphore) {
        toFront();
        this.semaphore = semaphore;
        jpanel0 = new ChooseSpaceFolderMenu(this);
        jpanel1 = new ChooseMulticenterTheme(this);
        setLayout(new BorderLayout());
        dialog.setLayout(new BorderLayout());

        dialog.add(jpanel0, BorderLayout.CENTER);
        add(dialog, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
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
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void nextPage() {
        currentPage++;
        switch (currentPage) {
            case 1:
                // Choose Theme
                Preferences.setSpacesFolder(jpanel0.getFileLocation());
                dialog.removeAll();
                dialog.add(jpanel1);
                previous.setEnabled(true);
                pack();
                setLocationRelativeTo(null);
                break;
            default:
                // Done
                semaphore.release();
                dispose();
        }
        pack();
    }

    private void previousPage() {
        currentPage--;
        switch (currentPage) {
            case 0:
                // Choose spacefolder
                dialog.removeAll();
                dialog.add(jpanel0);
                previous.setEnabled(false);
                SwingUtilities.updateComponentTreeUI(this);
                pack();
                setLocationRelativeTo(null);
                break;
            default:
                throw new IllegalStateException("IDK man something is broken");
        }
        pack();
    }
}
