package com.llp.test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description:
 * create by Administrator
 * create on 2021/07/03/16:07
 */
public class Test01 {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTime = df.format(new Date());
        System.out.println(currentTime);
    }


}
