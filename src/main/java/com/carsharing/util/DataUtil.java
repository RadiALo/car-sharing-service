package com.carsharing.util;

import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;

@Component
public class DataUtil {
    public Date currentData(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
