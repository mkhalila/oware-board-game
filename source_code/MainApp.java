import view.OwareUI;

/**
 * Created by mkhal on 06/11/2016.
 */
public class MainApp {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(OwareUI.class);
            }
        }.start();
    }
}
