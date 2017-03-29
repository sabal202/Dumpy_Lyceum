package sabal.dumpy_lyceum.DTOs;

import java.util.ArrayList;

public class New {

    private String title;
    private String introText;
    private String link;
    private ArrayList<String> images;

    private String fullText;
    private String date;

    public New(String title, String introText, String date, String image, String link) {
        this.title = title;
        this.introText = introText;
        this.date = date;
        this.link = link;

        ArrayList<String> urls = new ArrayList<>();
        urls.add(image);
        this.images = urls;
    }

    public New(String title, String introText, ArrayList<String> images, String fullText) {
        this.title = title;
        this.introText = introText;
        this.images = images;
        this.fullText = fullText;
    }


    public ArrayList<String> getImages() {
        return images;
    }

    public String getMainImage() {
        return images.get(0);
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

    public String getLink() {
        return link;
    }
}

