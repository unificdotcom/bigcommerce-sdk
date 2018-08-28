package com.bigcommerce.catalog.models;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, DateTime> {

    public static final String RFC_822_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    @Nullable
    @Override
    public DateTime unmarshal(final String timestamp) throws Exception {
        if (StringUtils.isBlank(timestamp)) {
            return null;
        }
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(RFC_822_DATE_FORMAT);
        return DateTime.parse(timestamp, formatter).toDateTime(DateTimeZone.UTC);
    }

    @Nullable
    @Override
    public String marshal(final DateTime dateTime) throws Exception {
        if (dateTime == null) {
            return null;
        }
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(RFC_822_DATE_FORMAT);
        return formatter.print(dateTime);

    }

}