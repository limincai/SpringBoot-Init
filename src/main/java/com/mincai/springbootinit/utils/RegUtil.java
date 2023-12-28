package com.mincai.springbootinit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author limincai
 * 正则表达式工具类
 */
public class RegUtil {

    /**
     * 判断是否含有特殊字符
     *
     * @return true为包含，false为不包含
     */
    public static boolean hasSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
