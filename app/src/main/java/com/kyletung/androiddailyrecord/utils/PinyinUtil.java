package com.kyletung.androiddailyrecord.utils;

import java.io.UnsupportedEncodingException;

/**
 * All Rights Reserved by Company.
 * Created by DongYuHui on 2016/9/23.
 * 获取字符串的各个首字母
 */

public class PinyinUtil {

    /**
     * 提取首字母
     *
     * @param name 原名
     * @return 返回首字母
     */
    private static String getFirstLetter(String name) {
        StringBuilder buffer = new StringBuilder();
        try {
            byte b[] = name.getBytes("GBK");//把中文转化成byte数组
            for (int i = 0; i < b.length; i++) {
                if ((b[i] & 255) > 128) {
                    int char1 = b[i++] & 255;
                    char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
                    int chart = char1 + (b[i] & 255);
                    buffer.append(changeChar((char) chart, true));
                } else {
                    char c = (char) b[i];
                    if (!Character.isJavaIdentifierPart(c))//确定指定字符是否可以是 Java 标识符中首字符以外的部分。
                        c = '#';
                    buffer.append(c);
                }
            }
        } catch (UnsupportedEncodingException e) {
            buffer.append("");
        }
        return buffer.toString();
    }

    /**
     * 得到首字母
     *
     * @param c        字符
     * @param isUpCase 是否大小写
     * @return 如果是中文，返回大小写，不是中文返回井号
     */
    private static char changeChar(char c, boolean isUpCase) {
        char result;
        if ((int) c >= 45217 && (int) c <= 45252)
            result = 'A';
        else if ((int) c >= 45253 && (int) c <= 45760)
            result = 'B';
        else if ((int) c >= 45761 && (int) c <= 46317)
            result = 'C';
        else if ((int) c >= 46318 && (int) c <= 46825)
            result = 'D';
        else if ((int) c >= 46826 && (int) c <= 47009)
            result = 'E';
        else if ((int) c >= 47010 && (int) c <= 47296)
            result = 'F';
        else if ((int) c >= 47297 && (int) c <= 47613)
            result = 'G';
        else if ((int) c >= 47614 && (int) c <= 48118)
            result = 'H';
        else if ((int) c >= 48119 && (int) c <= 49061)
            result = 'J';
        else if ((int) c >= 49062 && (int) c <= 49323)
            result = 'K';
        else if ((int) c >= 49324 && (int) c <= 49895)
            result = 'L';
        else if ((int) c >= 49896 && (int) c <= 50370)
            result = 'M';
        else if ((int) c >= 50371 && (int) c <= 50613)
            result = 'N';
        else if ((int) c >= 50614 && (int) c <= 50621)
            result = 'O';
        else if ((int) c >= 50622 && (int) c <= 50905)
            result = 'P';
        else if ((int) c >= 50906 && (int) c <= 51386)
            result = 'Q';
        else if ((int) c >= 51387 && (int) c <= 51445)
            result = 'R';
        else if ((int) c >= 51446 && (int) c <= 52217)
            result = 'S';
        else if ((int) c >= 52218 && (int) c <= 52697)
            result = 'T';
        else if ((int) c >= 52698 && (int) c <= 52979)
            result = 'W';
        else if ((int) c >= 52980 && (int) c <= 53688)
            result = 'X';
        else if ((int) c >= 53689 && (int) c <= 54480)
            result = 'Y';
        else if ((int) c >= 54481 && (int) c <= 55289)
            result = 'Z';
        else
            result = '#';
        if (!isUpCase)
            result = Character.toLowerCase(result);
        return result;
    }

}
