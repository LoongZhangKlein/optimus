package com.optimus.utils;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UUIDUtils {
    @Test
    public  static Long getUUID(){
        String header="2018";
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String format = time.format(new Date()).replace("-","");
        String uuid=header+format;
        return Long.parseLong(uuid);

    }
}
