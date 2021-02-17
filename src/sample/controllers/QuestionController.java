package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;
import sample.StageHolder;
import sample.models.Answer;
import sample.models.Question;
import sample.models.Quiz;
import java.util.ArrayList;

public class QuestionController {

    @FXML
    private Label question;
    @FXML
    private Label answer_a;
    @FXML
    private Label answer_b;
    @FXML
    private Label answer_c;
    @FXML
    private Label answer_d;

    @FXML
    private CheckBox ans_a_checkBox;
    @FXML
    private CheckBox ans_b_checkBox;
    @FXML
    private CheckBox ans_c_checkBox;
    @FXML
    private CheckBox ans_d_checkBox;

    @FXML
    private Label current_index;
    @FXML
    private Label max_index;

    @FXML
    private Button button_submit;
    @FXML
    private Button button_next;
    @FXML
    private Button button_prev;


    void save_current_answers(Quiz quiz) {
        if (quiz.get_current_question_index() < 0) {
            return;
        }
        ArrayList<Answer> user_answers = new ArrayList<Answer> ();
        if (ans_a_checkBox.isSelected()) {
            user_answers.add(Answer.A);
        }
        if (ans_b_checkBox.isSelected()) {
            user_answers.add(Answer.B);
        }
        if (ans_c_checkBox.isSelected()) {
            user_answers.add(Answer.C);
        }
        if (ans_d_checkBox.isSelected()) {
            user_answers.add(Answer.D);
        }
        get_current_question(quiz).set_user_answers(user_answers);
    }


    public void next_clicked() {
        Quiz quiz = Main.get_quiz();
        save_current_answers(quiz);

        increment_current_question_counter(quiz);
        Question current_question = get_current_question(quiz);
        update_question_window(current_question, quiz.get_current_question_index());

        max_index.setText(Integer.toString(quiz.get_questions().size()));
        button_submit.setVisible(quiz.get_current_question_index() == quiz.get_questions().size()-1);
        button_next.setVisible(quiz.get_current_question_index() < quiz.get_questions().size()-1);
        button_prev.setVisible(quiz.get_current_question_index() > 0);
    }


    public void prev_clicked() {
        Quiz quiz = Main.get_quiz();
        save_current_answers(quiz);

        decrement_current_question_counter(quiz);
        Question current_question = get_current_question(quiz);
        update_question_window(current_question, quiz.get_current_question_index());

        button_submit.setVisible(false);
        button_prev.setVisible(quiz.get_current_question_index() > 0);
        button_next.setVisible(true);
    }


    public void submit_clicked() throws Exception {
        Quiz quiz = Main.get_quiz();
        save_current_answers(quiz);
        Parent root = FXMLLoader.load(getClass().getResource("../views/quiz_submit.fxml"));
        StageHolder.change_scene(root);
    }

    void update_question_window(Question current_question, int current_question_index) {
        question.setText(current_question.get_question());

        answer_a.setVisible(true);
        answer_b.setVisible(true);
        answer_c.setVisible(true);
        answer_d.setVisible(true);
        ans_a_checkBox.setVisible(true);
        ans_b_checkBox.setVisible(true);
        ans_c_checkBox.setVisible(true);
        ans_d_checkBox.setVisible(true);

        answer_a.setText(current_question.get_ans_a());
        answer_b.setText(current_question.get_ans_b());
        answer_c.setText(current_question.get_ans_c());
        answer_d.setText(current_question.get_ans_d());

        ans_a_checkBox.setSelected(current_question.get_user_answers().contains(Answer.A));
        ans_b_checkBox.setSelected(current_question.get_user_answers().contains(Answer.B));
        ans_c_checkBox.setSelected(current_question.get_user_answers().contains(Answer.C));
        ans_d_checkBox.setSelected(current_question.get_user_answers().contains(Answer.D));

        current_index.setText(Integer.toString(current_question_index + 1));
    }


    void increment_current_question_counter(Quiz quiz) {
        int current_counter = quiz.get_current_question_index();
        if (current_counter + 1 < quiz.get_questions().size()) {
            quiz.set_current_question_index(current_counter + 1);
        }
    }


    void decrement_current_question_counter(Quiz quiz) {
        int current_counter = quiz.get_current_question_index();
        if (current_counter - 1 >= 0) {
            quiz.set_current_question_index(current_counter - 1);
        }
    }


    Question get_current_question(Quiz quiz) {
        return quiz.get_questions().get(quiz.get_current_question_index());
    }

}
