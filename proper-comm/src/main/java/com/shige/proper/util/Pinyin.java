package com.shige.proper.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author mq
 * @description: TODO
 * @title: Pinyin
 * @projectName his
 * @date 2020/12/710:22
 */
public class Pinyin {
    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
}
