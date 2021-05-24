package App.Multicenter.Platform;

import org.junit.Assert;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.Locale;

public class PlatformManagerTest {
    @Test
    public void Windows(){
        if (PlatformManager.getHostOSName().contains("windows"))
            Assert.assertEquals(ActionEvent.CTRL_MASK , PlatformManager.getAccelerator());
    }
    @Test
    public void Mac() {
        if (PlatformManager.getHostOSName().contains("mac"))
            Assert.assertEquals(ActionEvent.META_MASK,PlatformManager.getAccelerator());
    }
    @Test
    public void Linux() {
        if (PlatformManager.getHostOSName().contains("linux"))
            Assert.assertEquals(ActionEvent.CTRL_MASK,PlatformManager.getAccelerator());
    }
}
