package App.Multicenter.Platform;


import java.awt.event.ActionEvent;
import java.util.Locale;

/**
 * Proporciona métodos y variables útiles para adaptar
 * el funcionamiento de Multicenter al entorno en el que se está
 * ejecutando. Esta clase funciona de forma estática por lo
 * que no se puede instanciar.
 */
public class PlatformManager {
    // Constantes de clase
    private static final String hostOS = System.getProperty("os.name").toLowerCase(Locale.ROOT);
    private static final String MACOSX = "mac";
    private static final int MAIN_KEYBOARD = hostOS.contains(MACOSX) ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK;

    // Cierre de clase
    private PlatformManager() {
    }

    /**
     * Devuelve el nombre del sistema operativo en el que se está
     * ejecutando multicenter
     *
     * @return nombre del sistema operativo en minúsculas
     */
    public static String getHostOSName() {
        return hostOS;
    }

    /**
     * Configura algunas funciones de la JVM en función del sistema operativo.
     * Alguna de las funciones que realiza son
     * <ul>
     *     <li>macOS Text Antialiasing</li>
     *     <li>macOS Menubar</li>
     *     <li>macOS App icon</li>
     *     <li>macOS Nombre de la app</li>
     * </ul>
     */
    public static void setJVMEnviroment() {
        // TODO Complementar propiedades para cada SO
        switch (hostOS) {
            case MACOSX:
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", LanguageManager.getString("app_name"));
                System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.textantialiasing", "true");
                //Application.getApplication().setDockIconImage(new ImageIcon(MineSweeperResourceManager.getResourceURL(MineSweeperResourceManager.APPICON)).getImage());
                break;
            default:
        }
    }

    /**
     * Devuelve el acelerador utilizado por defecto en cada Sistema Operativo.
     * En el caso de macOS es META_MASK y en Windows/Linux CTRL_MASK
     *
     * @return Meta_MASK o CTRL_MASK
     * @see java.awt.event.ActionEvent
     */
    public static int getAccelerator() {
        return MAIN_KEYBOARD;
    }
}
