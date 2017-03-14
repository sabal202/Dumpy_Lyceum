package sabal.dumpy_lyceum;

import android.text.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Valentun on 07.03.2017.
 */

public class Support {
    //delete html tags from string input
    public static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public static long getUnixTImeFromString (String input) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
        return format.parse(input).getTime();
    }
}
