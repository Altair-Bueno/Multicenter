package App.Multicenter.Platform;


public class PlatformManager {
    public static String hostOS;

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
    public void setJVMEnviroment() {
        // TODO PlatformManager setJVMEnviroment
    }

    /**
     * Devuelve el acelerador utilizado por defecto en cada Sistema Operativo.
     * En el caso de macOS es META_MASK y en Windows/Linux CTRL_MASK
     *
     * @see java.awt.event.ActionEvent
     * @return Meta_MASK o CTRL_MASK
     */
    public int getAccelerator(){
        // TODO PlatformManager getAccelerator
        return 0;
    }
}
