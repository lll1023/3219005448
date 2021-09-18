package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

/**
 * @Author: Lsutin
 * @Date: 2021/9/17 8:44
 * @describe:
 */
public class StringUtil {

    /**
     * 过滤文本内容：
     *     （1）如果是html文件，使用jsoup解析html，提取td标签的内容，再删除其中的标点符号和空格
     *     （2）使用正则表达式判断出文件不是html，再删除其中的标点符号和空格
     * @param context 过滤后的内容
     * @return
     */
    private static String delHTML(String context){
        StringBuilder builder = new StringBuilder();
        //要删除的内容
        String[] strings = {"[\\pP\\p{Punct}]"," "};
        try {
            //判断是否是html文件
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

    /**
     * 对外提供的处理文件的方法
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static String processFile(String file) throws FileNotFoundException {
        return delHTML(FileUtil.read(file));
    }
}
