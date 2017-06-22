package model;

/**
 * Created by Aki on 6/19/2017.
 */

public class MagazineArticle {
    private String articleImage;
    private String articleSource;
    private String articleTitle;
    private String articleDate;

    public MagazineArticle(String articleImage, String articleSource, String articleTitle, String articleDate) {
        this.articleImage = articleImage;
        this.articleSource = articleSource;
        this.articleTitle = articleTitle;
        this.articleDate = articleDate;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }
}
