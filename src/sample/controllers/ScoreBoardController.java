package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.StageHolder;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class ScoreBoardController {

    @FXML
    private TextArea scores_text;
    @FXML
    private TextField file_path;


    public void main_menu_clicked() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        StageHolder.change_scene(root);
    }


    public void load_clicked() throws Exception {
        String scores_string = load_scores_from_file(file_path.getText());
        scores_text.setText(scores_string);
    }

    public String load_scores_from_file(String file_path)   throws Exception {
        File file = new File(file_path);

        if (!file.exists()) {
            return "Error";
        }

        Scanner reader = new Scanner(file);

        String scores_string = "";
        while (reader.hasNextLine()) {
            scores_string += reader.nextLine() + "\n";
        }
        reader.close();
        return scores_string;
    }


    public void save_scores_to_file(String file_path, String scores_text)   throws Exception {
        File file = new File(file_path);
        FileWriter writer = new FileWriter(file);
        writer.write(scores_text.toCharArray());
        writer.close();
    }
}
