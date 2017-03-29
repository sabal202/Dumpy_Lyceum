package sabal.dumpy_lyceum;

import android.text.Html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import sabal.dumpy_lyceum.DTOs.New;

/**
 * Created by Valentun on 07.03.2017.
 */

public class Utils {
    //delete html tags from string input
    public static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public static ArrayList<New> parseNewsFromHtml(Document doc) throws IOException {
        ArrayList<New> result = new ArrayList<>();

        Elements cards = doc.getElementsByClass("catItemView");
        for (Element card : cards) {
            String title, intro, date, imageURL = null, link;

            Element TitleElement = card.getElementsByClass("catItemTitle").first()
                    .getElementsByTag("a").get(0);

            title = TitleElement.text();

            link = TitleElement.attr("href");

            Element imageTag = card.getElementsByTag("img").first();

            if (imageTag != null) imageURL = imageTag.attr("src");

            intro = card.getElementsByClass("catItemIntroText").first()
                    .text();
            date = card.getElementsByClass("catItemDateCreated").first()
                    .text().trim();

            result.add(new New(title, intro, date, imageURL, link));
        }

        return result;
    }

    public static New parseFullNewFromHtml(Document doc) throws IOException {
        Element item = doc.getElementById("k2Container");

        String title = item.getElementsByClass("itemTitle").first().text().trim();
        String intro = item.getElementsByClass("itemIntroText").first().getElementsByTag("p")
                .first().text();
        StringBuilder sb = new StringBuilder("");
        Elements paragraphs = item.getElementsByClass("itemFullText").first().getElementsByTag("p");
        for (Element e : paragraphs) {
            sb.append(e.text()).append("\n");
        }
        String fullText = sb.toString();

        Elements imageElements = item.getElementsByClass("sigFreeLink");
        ArrayList<String> images = new ArrayList<>();
        for (Element e : imageElements) images.add(e.attr("href"));

        return new New(title, intro, images, fullText);
    }
}
