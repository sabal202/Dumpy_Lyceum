package sabal.dumpy_lyceum.DTOs;

public class New {

    private String title;
    private String introText;
    private String fullText;
    private String imageURL;

    private String date;

    public New(String title, String introText, String date, String imageURL) {
        this.title = title;
        this.introText = introText;
        this.date = date;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }


    public String getTitle() {
        return title;
    }

    public String getIntroText() {
        return introText;
    }

    public String getFullText() {
        return fullText;
    }

    public String getDate() {
        return date;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

