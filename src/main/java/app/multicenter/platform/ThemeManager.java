package app.multicenter.platform;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Proporciona herramientas para cambiar el Look and Feel de la aplicación
 * Estos temas son proporcionadas de forma gratuita por FormDevSoftware. Para
 * más información visitar el sitio web de
 * <a href="https://github.com/JFormDesigner/FlatLaf">Flatlaf</a>
 */
public class ThemeManager {
    // Constantes  de temas
    public static final int LIGHT = 0;
    public static final int DARK = 1;
    public static final int DARCULA = 2;
    public static final int INTELLIJ = 3;
    private static final String[] THEME_NAMES = {"Light", "Dark", "Darcula", "IntelliJ"};
    private static int CURRENT = -1;

    /**
     * Sustituye el Look and Feel actual por otro soportado por la aplicación.
     * Los nombres de los temas son constantes de ThemeManager. Es de vital
     * importancia actualizar el ComponentTree al cambiar el tema para que
     * los cambios surtan efecto
     *
     * @param themecode Constante de tema {LIGHT,DARK,DARCULA,INTELLIJ}
     * @return true si el tema se ha aplicado correctamente, falso en otro caso
     * @see SwingUtilities#updateComponentTreeUI(Component)
     */
    public static boolean setTheme(int themecode) {
        LookAndFeel laf = switch (themecode) {
            case LIGHT -> new FlatLightLaf();
            case DARK -> new FlatDarkLaf();
            case DARCULA -> new FlatDarculaLaf();
            case INTELLIJ -> new FlatIntelliJLaf();
            default -> null;
        };
        if (laf == null) return false;
        CURRENT = themecode;

        boolean out = true;
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e.getMessage());
            out = false;
        }
        return out;
    }

    /**
     * Devuelve el código de tema actualmente en la aplicación
     *
     * @return Código de tema actual
     * @throws IllegalStateException Si no se ha inicializado correctamente la aplicación
     */
    public static int getCurrentTheme() {
        if (CURRENT == -1) throw new IllegalStateException("ThemeManager not correctly initialized");
        return CURRENT;
    }

    public static String[] getThemeNames() {
        return Arrays.copyOf(THEME_NAMES, THEME_NAMES.length);
    }
}
