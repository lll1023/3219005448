package com;

import org.junit.Test;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 10:50
 * @describe:
 */
public class FileUtilTest {

    @Test
    public void testNull(){
        new MainProcess().main(null);
    }

    @Test
    public void testContext(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_dis_15.txt";
        SimHash simHash1 = new SimHash(StringUtil.processFile(file1));
        SimHash simHash2 = new SimHash(StringUtil.processFile(file2));
        String similarity = simHash1.getSimilarity(simHash2);
        StringBuilder builder = new StringBuilder();
        builder.append("原文文件：").append(file1).append("\n");
        builder.append("抄袭版论文的文件：").append(file2).append("\n");
        builder.append("两个文件的相似度为：").append(similarity).append("\n");
        System.out.println(builder.toString());
    }
}
