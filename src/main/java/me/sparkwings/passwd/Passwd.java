package me.sparkwings.passwd;

public class Passwd {

    private String label, content;

    public Passwd(String label, String content) {
        this.label = label;
        this.content = content;
    }

    public String getLabel() {
        return this.label;
    }

    public String getContent() {
        return this.content;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
