package com.sckj.utils;

import org.json.HTTP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {
    /**
     * 得到网页中图片的地址
     * @param htmlStr html字符串
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static String replaceHtmlImgSrc(String htmlStr) {
        String statTag = "src=\"https://"+HttpUtils.getServerUrl()+"/";//"src=\"http://junlenet.com/"
        String newStr = replaceHtmlTag(htmlStr, "img", "src", statTag, "\"");
        return newStr;
    }

    /**
     * 替换指定标签的属性和值
     * @param str 需要处理的字符串
     * @param tag 标签名称
     * @param tagAttrib 要替换的标签属性值
     * @param startTag 新标签开始标记
     * @param endTag  新标签结束标记
     * @return
     * @author huweijun
     * @date 2016年7月13日 下午7:15:32
     */
    public static String replaceHtmlTag(String str, String tag, String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + tag + "\\s+([^>]*)\\s*" ;
        String regxpForTagAttrib = tagAttrib + "=\\s*\"([^\"]+)\"" ;
        Pattern patternForTag = Pattern.compile (regxpForTag,Pattern. CASE_INSENSITIVE );
        Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib,Pattern. CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer( "<"+tag+" ");
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttrib.find()) {
                String attributeStr = matcherForAttrib.group(1);
                matcherForAttrib.appendReplacement(sbreplace, startTag + attributeStr + endTag);
            }
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    /**
     * 替换指定标签的属性和值
     * @param str 需要处理的字符串
     * @param tag 标签名称
     * @param tagAttrib 要替换的标签属性值
     * @param targetValues 新标签开始标记
     * @return
     * @author huweijun
     * @date 2016年7月13日 下午7:15:32
     */
    public static String replaceHtmlTag(String str, String tag, String tagAttrib,  List<String> targetValues) {
        String regxpForTag = "<\\s*" + tag + "\\s+([^>]*)\\s*" ;
        String regxpForTagAttrib = tagAttrib + "=\\s*\"([^\"]+)\"" ;
        Pattern patternForTag = Pattern.compile (regxpForTag,Pattern. CASE_INSENSITIVE );
        Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib,Pattern. CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        int i =0;
        while (result) {
            StringBuffer sbreplace = new StringBuffer( "<"+tag+" ");
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttrib.find()) {
                String attributeStr = matcherForAttrib.group(1);
                matcherForAttrib.appendReplacement(sbreplace, targetValues.get(i));
            }
            matcherForAttrib.appendTail(sbreplace);
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
            i++;
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuffer content = new StringBuffer();
        content.append("<ul class=\"imgBox\"><li><img id=\"160424\" src=\"uploads/allimg/160424/1-160424120T1-50.jpg\" class=\"src_class\"></li>");
        content.append("<li><img id=\"150628\" src=\"uploads/allimg/150628/1-15062Q12247.jpg\" class=\"src_class\"></li></ul>");
        System.out.println("原始字符串为:"+content.toString());
        String newStr = replaceHtmlTag(content.toString(), "img", "src", "src=\"http://junlenet.com/", "\"");
        System.out.println("       替换后为:"+newStr);
    }


}
