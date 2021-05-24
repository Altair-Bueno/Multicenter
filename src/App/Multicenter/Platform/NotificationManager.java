package App.Multicenter.Platform;

import java.awt.*;
import java.util.*;

/**
 * Ofrece operaciones para mostrar notificaciones al usuario usando el
 * centro de control. Esta clase funciona de forma estática por lo
 * que no se puede instanciar.
 */
public class NotificationManager {

    private static Map<Integer , Timer> timers = new HashMap<>();
    private static int next = 0;

    // Cierre de clase
    private NotificationManager(){}
    /**
     * Comprueba si la aplicación es capaz de mostrar notificaciones en el
     * centro de control. Puede que el usuario deba otorgar permisos a Java
     * para que poder mandar notificaciones. Puede que los cambios no surtan
     * efectos hasta que se reinicie la JVM
     *
     * @return Si las notificaciones funcionan en este dispositivo
     */
    public static boolean isSupported(){
        return SystemTray.isSupported();
    }

    /**
     * Muestra una notificacion en el centro de control del usuario
     * @param image Icono de notificación
     * @param tooltip Información adicional
     * @param caption Título de la notificación
     * @param text Contenido de la notificación
     * @param messageType Tipo de mensaje
     * @throws AWTException Si las notificaciones no están soportadas en este sistema
     */
    public static void notifyNow(Image image, String tooltip, String caption , String text,  TrayIcon.MessageType messageType) throws AWTException {
        TrayIcon trayIcon = new TrayIcon(image, tooltip);
        notifyNow(trayIcon, caption , text, messageType);
    }

    /**
     * Muestra una notificacion en el centro de control del usuario
     *
     * @param trayIcon Representa un elemento que puede ser añadido al centro de control del usuario
     * @param caption Título de la notificación
     * @param text Contenido de la notificación
     * @param messageType Tipo de mensaje
     * @throws AWTException Si las notificaciones no están soportadas en este sistema
     */
    private static void notifyNow(TrayIcon trayIcon, String caption, String text, TrayIcon.MessageType messageType) throws AWTException {
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.add(trayIcon);
        trayIcon.displayMessage(caption,text,messageType);
    }

    /**
     * Muestra una notificación en el centro de control del usuario en
     * la fecha indicada. Si dicha fecha es anterior a la fecha actual del
     * sistema se mostrará inmediatamente. Devuelve un identificador de
     * temporizador utilizado para poder desactivarlo
     *
     * @param date Fecha en la que se producirá la notificación
     * @param image Icono de notificación
     * @param appname Nombre del recurso que manda la notificación
     * @param caption Título de la notificación
     * @param text Contenido de la notificación
     * @param messageType Tipo de mensaje
     * @return Identificador de temporizador
     * @throws AWTException Si las notificaciones no están soportadas en este sistema
     */
    public static int notifyLater(Date date ,Image image, String appname , String caption, String text, TrayIcon.MessageType messageType) throws AWTException {
        return notifyLater(date , new TrayIcon(image,appname),caption , text, messageType);
    }

    /**
     * Muestra una notificación en el centro de control del usuario en
     * la fecha indicada. Si dicha fecha es anterior a la fecha actual del
     * sistema se mostrará inmediatamente
     *
     * @param date Fecha en la que se producirá la notificación
     * @param trayIcon Representa un elemento que puede ser añadido al centro de control del usuario
     * @param caption Título de la notificación
     * @param text Contenido de la notificación
     * @param messageType Tipo de mensaje
     * @return Identificador de temporizador
     * @throws AWTException Si las notificaciones no están soportadas en este sistema
     */
    private static int notifyLater(Date date , TrayIcon trayIcon, String caption, String text, TrayIcon.MessageType messageType) throws AWTException {
        if (!isSupported())throw new AWTException("Unable to send desktop notification");
        next++;
        TimerTask timerTask = new TimerTask() {
            int timerCode = next;

            @Override
            public void run() {
                try {
                    timers.remove(timerCode);
                    notifyNow(trayIcon, caption, text, messageType);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,date);
        timers.put(next,timer);
        return next;
    }

    /**
     * Desactiva el temporizador utilizado para mostrar la notificación.
     * Si dicho temporizador se puede eliminar esta función devolverá true.
     * En cualquier otro caso false
     *
     * @param code Código de temporizador
     * @return Si la eliminación es exitosa o no
     */
    public static boolean disableNotifyLater(int code) {
        Timer timer = timers.get(code);
        if (timer == null) return false;
        else {
            timer.cancel();
            timers.remove(code);
            return true;
        }
    }

    /**
     * Desactiva todos los temporizadores de la app Multicenter
     */
    public static void disableAll(){
        for (Timer timer:timers.values()){
            timer.cancel();
        }
        timers = new HashMap<>();
    }
}
