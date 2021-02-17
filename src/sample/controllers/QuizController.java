package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.StageHolder;
import sample.models.Answer;
import sample.models.Question;
import sample.models.Quiz;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class QuizController {

    @FXML
    private Label score;
    @FXML
    private Label max_score;
    @FXML
    private TextField user_name;
    @FXML
    private TextField score_board_path;
    @FXML
    private Label file_doesnt_exist;
    @FXML
    private Label quiz_name;


    public Quiz from_file(File file)  throws Exception {
        Scanner reader = new Scanner(file);

        ArrayList<Question> questions = new ArrayList<Question>();

        String quiz_name = reader.nextLine();
        String question = "";
        String ans_a = "";
        String ans_b = "";
        String ans_c = "";
        String ans_d = "";

        int i = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            switch (i) {
                case 0: {question = line;} break;
                case 1: {ans_a = line;} break;
                case 2: {ans_b = line;} break;
                case 3: {ans_c = line;} break;
                case 4: {ans_d = line;} break;
                case 5: {
                    ArrayList<Answer> answers = new ArrayList<Answer>();
                    for (char ans : line.toCharArray()) {
                        switch (ans) {
                            case 'a' | 'A': { answers.add(Answer.A); } break;
                            case 'b' | 'B': { answers.add(Answer.B); } break;
                            case 'c' | 'C': { answers.add(Answer.C); } break;
                            case 'd' | 'D': { answers.add(Answer.D); } break;
                        }
                    }
                    questions.add( new Question(question, ans_a, ans_b, ans_c, ans_d, answers));
                    i = -1;
                }
                break;
            }
            i ++;
        }
        return new Quiz(quiz_name,questions);
    }


    public void calculate_clicked() {
        Quiz quiz = Main.get_quiz();
        submit_user_score(quiz);
    }


    public void main_menu_clicked() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        StageHolder.change_scene(root);
    }


    public void save_clicked() throws Exception {
        ScoreBoardController controller = new ScoreBoardController();
        String scores_string = controller.load_scores_from_file(score_board_path.getText());
        if (scores_string == "Error") {
            file_doesnt_exist.setVisible(true);
            return;
        }
        file_doesnt_exist.setVisible(false);

        String new_score = score_to_string();
        if (new_score == "") {
            return;
        }

        scores_string += new_score + "\n";
        controller.save_scores_to_file(score_board_path.getText(), scores_string);
    }


    public String score_to_string() {
        String quiz_name_string = quiz_name.getText();
        String user_string = user_name.getText();
        String score_string = score.getText() + "/" + max_score.getText();
        if (score.getText() == "") {
            return "";
        }
        while (quiz_name_string.length() <= 30) {
            quiz_name_string += " ";
        }
        while (user_string.length() <= 25) {
            user_string += " ";
        }
        return quiz_name_string + user_string + score_string;
    }


    public void submit_user_score(Quiz quiz) {
        int score_counter = 0;

        for (Question question : quiz.get_questions()) {
            if (check_answers(question)) {
                score_counter++;
            }
        }

        score.setText(Integer.toString(score_counter));
        max_score.setText(Integer.toString(quiz.get_questions().size()));
        quiz_name.setText(quiz.get_name());
    }


    public boolean check_answers(Question question) {
        int user_points = 0;
        int max_points = question.get_correct_answers().size();

        for (Answer user_answer : question.get_user_answers()) {
            boolean correct = false;
            for (Answer correct_answear : question.get_correct_answers()) {
                if (user_answer == correct_answear) {
                    correct = true;
                }
            }
            if (correct) {
                user_points++;
            } else {
                user_points--;
            }
        }
        if (user_points == max_points) {
            return true;
        } else {
            return false;
        }
    }

}
