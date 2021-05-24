package App.Multicenter.Platform;


import java.awt.event.ActionEvent;
import java.util.Locale;

public class PlatformManager {
    private static final String hostOS = System.getProperty("os.name").toLowerCase(Locale.ROOT);
    private static final String MACOSX = "mac";
    private static final int MAIN_KEYBOARD = hostOS.contains(MACOSX) ? ActionEvent.META_MASK : ActionEvent.CTRL_MASK;

    /**
     * Devuelve el nombre del sistema operativo en el que se está
     * ejecutando multicenter
     * @return nombre del sistema operativo
     */
    public static String getHostOSName() {
        return hostOS;
    }

    /**
     * Configura algunas funciones de la JVM en función del sistema operativo.
     * Alguna de las funciones que realiza son
     * <ul>
     *     <li>macOS Text Antialiasing</li>
     *     <li>macOS menubar</li>
     *     <li>macOS app icon</li>
     * </ul>
     */
    public static void setJVMEnviroment() {
        switch (hostOS) {
            case MACOSX:
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", LanguageManager.getString("app_name"));
                System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.awt.textantialiasing", "true");
                break;
                default:
        }
    }

    /**
     * Devuelve el acelerador utilizado por defecto en cada Sistema Operativo.
     * En el caso de macOS es META_MASK y en Windows/Linux CTRL_MASK
     *
     * @see java.awt.event.ActionEvent
     * @return Meta_MASK o CTRL_MASK
     */
    public static int getAccelerator(){
        return MAIN_KEYBOARD;
    }
}
