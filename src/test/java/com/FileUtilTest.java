package com;

import org.junit.Test;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 10:50
 * @describe:
 */
public class FileUtilTest {

    @Test
    public void testWrite(){
        String name="D:\\课内学习\\软件工程\\target.txt";
        String context="hello";
        FileUtil.write(name,context);
    }

    @Test
    public void testRead(){
        String name="D:\\课内学习\\软件工程\\target.txt";
        System.out.println(FileUtil.read(name));
    }

    @Test
    public void testSimHash(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_add.txt";
        String targetFile = "D:\\课内学习\\软件工程\\target.txt";
        SimHash simHash1 = new SimHash(FileUtil.read(file1));
        SimHash simHash2 = new SimHash(FileUtil.read(file2));
        String similarity = simHash1.getSimilarity(simHash2);
        StringBuilder builder = new StringBuilder();
        builder.append("原文文件：").append(file1).append("\n");
        builder.append("抄袭版论文的文件：").append(file2).append("\n");
        builder.append("两个文件的相似度为：").append(similarity).append("\n");
        FileUtil.write(targetFile,builder.toString());
    }
}
