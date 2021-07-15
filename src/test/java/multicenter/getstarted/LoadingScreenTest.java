package multicenter.getstarted;

import app.multicenter.getstarted.LoadingScreen;

public class LoadingScreenTest {

    public static void main(String[] args) throws InterruptedException {
        LoadingScreen loadingScreen = new LoadingScreen();
        loadingScreen.setValue(20);
        Thread.sleep(1000);
        loadingScreen.setValue(40);
        Thread.sleep(60);
        loadingScreen.setValue(100);

        loadingScreen.dispose();
    }
}
