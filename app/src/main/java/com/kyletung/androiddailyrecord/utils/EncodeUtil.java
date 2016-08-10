package com.kyletung.androiddailyrecord.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * All rights reserved by Author<br>
 * Author: Dong YuHui<br>
 * Email: <a href="mailto:dyh920827@hotmail.com">dyh920827@hotmail.com</a><br>
 * Blog: <a href="http://www.kyletung.com">www.kyletung.com</a><br>
 * Create Time: 2016/4/13 at 17:36<br>
 * 编码工具类，提供诸如 MD5 或者 SHA1 等的计算
 */
public class EncodeUtil {

    /**
     * 将字符串转化成 MD5 值
     *
     * @param content 传入字符串
     * @return 返回 MD5 值
     */
    public static String getStringMD5(String content) {
        try {
            // 拿到一个 MD5 转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = content.getBytes();
            // inputByteArray 是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 用于将字节数组换成成 16 进制的字符串
     *
     * @param byteArray 字节数组
     * @return 转化后的字符串
     */
    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new 一个字符数组，这个就是用来组成结果字符串的（解释一下：一个 byte 是八位二进制，也就是 2 位十六进制字符（2 的 8 次方等于 16 的 2 次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

}
