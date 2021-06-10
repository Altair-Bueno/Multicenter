package multicenter.Platform;

import app.multicenter.Platform.NotificationManager;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.util.Date;
import java.util.Timer;

@Ignore("Muy molesto")
public class NotificationManagerTest {
    Image image = Toolkit.getDefaultToolkit().createImage("resources/App/Multicenter/Icons/information.svg");

    @Test
    public void notifyNow() throws AWTException {
        for (int i = 0; i < 4; i++)
            NotificationManager.notifyNow(image, null, "N" + i, null, TrayIcon.MessageType.INFO);

    }

    @Test
    public void notifyLater() throws AWTException, InterruptedException {
        NotificationManager.notifyLater(new Date(System.currentTimeMillis() + 1000), image, null, "1000 milis later", null, TrayIcon.MessageType.INFO);
        NotificationManager.notifyLater(new Date(System.currentTimeMillis() + 2000), image, null, "2000 milis later", null, TrayIcon.MessageType.INFO);
        NotificationManager.notifyLater(new Date(System.currentTimeMillis() + 3000), image, null, "2000 milis later", null, TrayIcon.MessageType.INFO);

        Thread.sleep(5000);
    }

    @Test
    public void disble() throws AWTException, InterruptedException {
        Timer t = NotificationManager.notifyLater(new Date(System.currentTimeMillis() + 5000), image, null, "No debe aparecer", null, TrayIcon.MessageType.INFO);
        Thread.sleep(1000);
        t.cancel();
        Thread.sleep(10000);
    }
}
