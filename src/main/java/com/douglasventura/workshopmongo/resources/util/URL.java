package com.douglasventura.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class URL {

  public static String decoderParams(String text) {
    try {
      return URLDecoder.decode(text, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static Instant convertDate(String textDate, Instant defaultValue) {
    try {
      LocalDate localDate = LocalDate.parse(textDate);
      Instant date = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
      return date;
    } catch (Exception e) {
      return defaultValue;
    }
  }
}
