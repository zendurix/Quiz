package sample.models;

import java.util.ArrayList;

import java.io.Serializable;


public class Quiz implements  Serializable {

    private ArrayList<Question> questions;
    private int current_question_index;
    private String name;


    public Quiz(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.current_question_index = -1;
    }

    public ArrayList<Question> get_questions() {
        return this.questions;
    }
    public void set_questions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int get_current_question_index() {
        return this.current_question_index;
    }
    public void set_current_question_index(int current_question_index) {
        this.current_question_index = current_question_index;
    }

    public String get_name() {
        return this.name;
    }
    public void set_name(String name) {
        this.name = name;
    }





}
