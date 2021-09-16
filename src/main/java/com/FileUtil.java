package com;


import java.io.*;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 9:19
 * @describe:
 */
public class FileUtil {
    public static String read(String name) {
        if(null==name){
            System.out.println("请输入正确的文件地址");
            return null;
        }
        File file = new File(name);
        if(!file.exists()){
            System.out.println("文件："+name+" 不存在");
            return null;
        }
        if(!file.isFile()){
            System.out.println("路径："+name+" 不是文件");
            return null;
        }
        BufferedReader reader=null;
        StringBuilder builder = new StringBuilder();
        try {
            reader=new BufferedReader(new FileReader(file));
            String temp;
            while ((temp=reader.readLine())!=null){
                builder.append(temp);
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public static void write(String name,String context){
        if(null==name||null==context){
            System.out.println("请输出正确的格式");
            return;
        }
        File file = new File(name);
        BufferedWriter writer=null;
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!file.isFile()){
            System.out.println("路径："+name+" 不是文件，写入文件失败");
            return;
        }
        try {
            writer=new BufferedWriter(new FileWriter(file));
            writer.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
