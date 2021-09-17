package com;


import java.io.*;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 9:19
 * @describe:
 */
public class FileUtil {
    public static String read(String name) throws FileNotFoundException {
        if (null==name){
            throw new FileNotFoundException("文件名错误");
        }
        File file = new File(name);
        if(!file.exists()||!file.isFile()){
            throw new FileNotFoundException("文件错误，请重新检查文件名或者该路径是否是文件夹");
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
