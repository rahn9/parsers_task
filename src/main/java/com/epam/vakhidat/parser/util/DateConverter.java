package com.epam.vakhidat.parser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {
    public static Date parseDate(String stringDate) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sf.parse(stringDate);
        return date;
    }
}
