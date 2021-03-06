package com;

import java.io.FileNotFoundException;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 9:36
 * @describe:
 */
public class MainProcess {
    public static void main(String[] args) {
        if(null==args||args.length<3){
            System.out.println("输入格式错误，请重新尝试");
            return;
        }
        String file1 = args[0];
        String file2 = args[1];
        String targetFile = args[2];
        SimHash simHash1;
        SimHash simHash2;
        try {
            simHash1 = new SimHash(StringUtil.processFile(file1));
            simHash2 = new SimHash(StringUtil.processFile(file2));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        String similarity = simHash1.getSimilarity(simHash2);
        StringBuilder builder = new StringBuilder();
        builder.append("原文文件：").append(file1).append("\n");
        builder.append("抄袭版论文的文件：").append(file2).append("\n");
        builder.append("两个文件的相似度为：").append(similarity).append("\n");
        FileUtil.write(targetFile,builder.toString());
    }
}