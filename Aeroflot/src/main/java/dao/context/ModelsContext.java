package dao.context;


import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;



public class ModelsContext {

    public static final String            DATE_PATTERN        = "yyyy-MM-dd";
    public static final DateTimeFormatter TIMESTAMP_FORMATTER =
        new DateTimeFormatterBuilder().appendValue(YEAR, 4)
                                      .appendLiteral('-')
                                      .appendValue(MONTH_OF_YEAR, 2)
                                      .appendLiteral('-')
                                      .appendValue(DAY_OF_MONTH, 2)
                                      .appendLiteral(' ')
                                      .appendValue(HOUR_OF_DAY, 2)
                                      .appendLiteral(':')
                                      .appendValue(MINUTE_OF_HOUR, 2)
                                      .appendLiteral(':')
                                      .appendValue(SECOND_OF_MINUTE, 2)
                                      .toFormatter();



    public static String toTimestampFormat(String localDateTime) {

        String[] dateTime = localDateTime.split("\\.");
        dateTime = dateTime[0].split("T");
        String ret = String.join(" ", dateTime);

        if (ret.matches(".*:.{2}:.{2}")) {
            return ret;
        }
        else {
            return ret + ":00";
        }
    }

}
