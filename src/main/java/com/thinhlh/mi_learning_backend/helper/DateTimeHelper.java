package com.thinhlh.mi_learning_backend.helper;

import com.thinhlh.mi_learning_backend.exceptions.ConversionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

public abstract class DateTimeHelper {
    public abstract class Format {
        public static final String DD_MM_YYYY = "dd/MM/yyyy";
        public static final String DD__MM__YYYY = "dd-MM-yyyy";
        public static final String HH_MM = "HH:mm";
    }

    public static Optional<Calendar> stringToCalendar(String dateString, String format) {
        try {
            if (dateString == null) {
                return Optional.empty();
            }

            var simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            var calendar = Calendar.getInstance();

            calendar.setTime(simpleDateFormat.parse(dateString));

            return Optional.of(calendar);
        } catch (ParseException e) {
            throw new ConversionException("Unable to convert date");
        }
    }

    public static String calendarToString(Calendar calendar, String format) {
        var simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);

        return simpleDateFormat.format(calendar.getTime());
    }

    public static Optional<Calendar> timeStampToCalendar(Optional<Long> timestamp) {
        if (timestamp.isEmpty()) return Optional.empty();

        var calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timestamp.get());

        return Optional.of(calendar);
    }
}
