package sample.models;

import java.util.ArrayList;

public class Question {
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private ArrayList<Answer> correct_answers;
    private ArrayList<Answer> user_answers;

    public Question(String question, String ans_a, String ans_b, String ans_c, String ans_d, ArrayList<Answer> correct_answers) {
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.correct_answers = correct_answers;
         this.user_answers = new ArrayList<Answer>();
    }

    public String get_question() {
        return this.question;
    }
    public void set_question(String question) {
        this.question = question;
    }

    public String get_ans_a() {
        return this.ans_a;
    }
    public void set_ans_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String get_ans_b() {
        return this.ans_b;
    }
    public void set_ans_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String get_ans_c() {
        return this.ans_c;
    }
    public void set_ans_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String get_ans_d() {
        return this.ans_d;
    }
    public void set_ans_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public ArrayList<Answer> get_correct_answers() {
        return this.correct_answers;
    }
    public void set_correct_answers(ArrayList<Answer> correct_answers) {
        this.correct_answers = correct_answers;
    }

    public ArrayList<Answer> get_user_answers() {
        return this.user_answers;
    }
    public void set_user_answers(ArrayList<Answer> user_answers) {
        this.user_answers = user_answers;
    }
}
