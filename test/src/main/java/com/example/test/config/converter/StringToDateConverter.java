package com.example.test.config.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    public Date convert(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d=null;
        try {
            d = format.parse(s);    //将前端传出的yyyy-MM-dd格式的字符串转为时间
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }
}
