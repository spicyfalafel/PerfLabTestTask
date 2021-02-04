package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateHandler {
    /*
    2020-01-01T12:00:00
    2020-01-01T13:00:00
    yyyy-MM-ddTHH:mm:ss
 */
    public static DateTimeFormatter dtf;
    public static LocalDateTime parseTimeFromString(String timeInString){

         dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss",
                Locale.getDefault());
        return LocalDateTime.parse(timeInString, dtf);
    }

}
