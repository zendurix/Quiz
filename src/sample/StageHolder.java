package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageHolder {
    protected static Stage instance = null;

    public static Stage get() {
        return instance;
    }

    public static void set(Stage instance) {
        StageHolder.instance = instance;
    }

    public static void change_scene(Parent root) throws Exception {
        Scene scene = new Scene(root);
        instance.setScene(scene);
        instance.show();
        set(instance);
    }

}



