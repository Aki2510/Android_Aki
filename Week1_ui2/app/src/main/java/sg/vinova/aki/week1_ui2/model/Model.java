package sg.vinova.aki.week1_ui2.model;

/**
 * Created by Aki on 6/21/2017.
 */

public class Model {
    String image;
    String title;
    String author;
    String content;

    public Model(String image, String title, String author, String content) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
