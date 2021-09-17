package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

/**
 * @Author: Lsutin
 * @Date: 2021/9/17 8:44
 * @describe:
 */
public class StringUtil {

    private static String delHTML(String context){
        StringBuilder builder = new StringBuilder();
        String[] strings = {"[\\pP\\p{Punct}]"," "};
        try {
            Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*",Pattern.DOTALL);
            boolean isHTML = htmlPattern.matcher(context).matches();
            if(!isHTML){
                for (String string:strings){
                    context = context.replaceAll(string,"");
                }
                return context;
            }
            //解析html内容
            Document doc = Jsoup.parse(context);
            //获取tr标签中的td标签内容
            //这个是观察文本中的html格式后，找出的针对这类html的解析
            Elements trs = doc.select("table").select("tr");
            for(int i = 0;i<trs.size();i++){
                Elements tds = trs.get(i).select("td");
                for(int j = 0;j<tds.size();j++){
                    String text = tds.get(j).text();
                    builder.append(text);
                }
            }
            context = builder.toString();
            for (String string:strings){
                context = context.replaceAll(string,"");
            }
        }catch (NullPointerException e){

        }

        return context;
    }

    public static String processFile(String file){
        return delHTML(FileUtil.read(file));
    }
}
