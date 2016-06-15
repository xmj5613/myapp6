package com.example.myapp6.models;

/**
 * Created by pc on 2016/4/13.
 */
public class Contact {
    private String letter;
    private String name;

    public Contact(String letter, String name) {
        this.letter = letter;
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
