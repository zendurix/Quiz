package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import sample.controllers.QuizController;
import sample.models.Quiz;

public class Main extends Application  {

    @FXML
    private TextField quiz_path;
    @FXML
    private Label file_doesnt_exist;

    static Quiz quiz;

    @Override
    public void start(Stage primaryStage) throws Exception{
        generate_scene(primaryStage, "views/main.fxml");
    }


    public void start_quiz_clicked() throws Exception {
        QuizController controller = new QuizController();
        File file = new File(quiz_path.getText());
        if (!file.exists()) {
            file_doesnt_exist.setVisible(true);
            return;
        }
        file_doesnt_exist.setVisible(false);

        Main.quiz = controller.from_file(file);
        Parent root = FXMLLoader.load(getClass().getResource("views/question.fxml"));
        StageHolder.change_scene(root);
    }


    public void score_board_clicked() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/score_board.fxml"));
        StageHolder.change_scene(root);
    }


    public void exit_clicked() {
        Platform.exit();
        System.exit(0);
    }


    private void generate_scene(Stage stage, String scene) throws Exception
    {
        StageHolder.set(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(scene));
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.setTitle("Projekt QUIZ");
        stage.show();
    }


    public static Quiz get_quiz() {
        return quiz;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
