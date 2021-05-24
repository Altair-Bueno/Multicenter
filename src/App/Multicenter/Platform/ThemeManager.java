package App.Multicenter.Platform;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

/**
 * Proporciona herramientas para cambiar el Look and Feel de la aplicación
 */
public class ThemeManager {
    // Constantes  de temas
    public static final int LIGHT = 0;
    public static final int DARK = 1;
    public static final int DARCULA = 2;
    public static final int INTELLIJ = 3;

    /**
     * Sustituye el Look and Feel actual por otro soportado por la aplicación.
     * Los nombres de los temas son constantes de ThemeManager. Es de vital
     * importancia actualizar el ComponentTree al cambiar el tema para que
     * los cambios surtan efecto
     *
     * @see SwingUtilities
     * @param themecode Constante de tema {LIGHT,DARK,DARCULA,INTELLIJ}
     * @return true si el tema se ha aplicado correctamente, falso en otro caso
     */
    public static boolean setTheme(int themecode){
        LookAndFeel laf = switch (themecode){
            case LIGHT -> new FlatLightLaf();
            case DARK -> new FlatDarkLaf();
            case DARCULA -> new FlatDarculaLaf();
            case INTELLIJ-> new FlatIntelliJLaf();
            default -> null;
        };
        if (laf == null) return false;

        boolean out = true;
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            out = false;
        }
        return out;
    }
}
