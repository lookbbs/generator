package com.ydf.generator.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class StringTools {

    public static String upperCamelCase(String source) {
        return upperCamelCase(source, false);
    }

    /**
     * 转换为驼峰命名格式字符串
     *
     * @param source 源字符串
     * @param first  第一个单词的首字母是否需要转换为大写
     * @return 转换后的驼峰格式字符串
     */
    public static String upperCamelCase(String source, boolean first) {
        if (StringUtils.isBlank(source)) {
            return StringUtils.EMPTY;
        }
        // 两个单词间的分割符号
        String underline = "_";
        if (source.indexOf(underline) > 0) {
            String[] split = source.split(underline);
            int i = first ? 0 : 1;
            for (; i < split.length; i++) {
                split[i] = toUpperFirstChar(split[i]);
            }
            return StringUtils.join(split);
        }
        return source;
    }

    private static String toUpperFirstChar(String str) {
        char[] ch = str.toCharArray();
        char a = 'a', z = 'z';
        if (ch[0] >= a && ch[0] <= z) {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
