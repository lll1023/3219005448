package com;

import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @Author: Lsutin
 * @Date: 2021/9/16 10:50
 * @describe:
 */
public class FileUtilTest {

    //1.测试文件名为空
    @Test
    public void testNull(){
        new MainProcess().main(null);
    }

    //2.测试文件名标准个数
    @Test
    public void testFormat(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        new MainProcess().main(new String[]{file1});
    }

    //3.测试文件不存在
    @Test
    public void testFile(){
        String file1 = "D:\\课内学习\\软件工程\\ori.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_dis_15.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //4.测试文本相似度
    @Test
    public void testContext1(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_add.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //5.测试文本相似度
    @Test
    public void testContext2(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_del.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //6.测试文本相似度
    @Test
    public void testContext3(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_dis_1.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //7.测试文本相似度
    @Test
    public void testContext4(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_dis_10.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //8.测试文本相似度
    @Test
    public void testContext5(){
        String file1 = "D:\\课内学习\\软件工程\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\orig_0.8_dis_15.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

    //9.在html中提取文本,比较提取和没提取的文本内容（不包含标点符号以及空格）
    @Test
    public void testContext7(){
        String file1 = "D:\\课内学习\\软件工程\\orig_0.8_dis_1.txt";
        String file2 = "D:\\课内学习\\软件工程\\测试文本\\orig_0.8_dis_1.txt";
        String context1="";
        String context2="";
        try {
            context1 = StringUtil.processFile(file1);
            context2 = StringUtil.processFile(file2);
        }catch (FileNotFoundException e){
            System.out.println();
            return;
        }
        System.out.println(context1.equals(context2));
    }

    //10.测试文本相似度(一个是html文件，一个不是)
    @Test
    public void testContext6(){
        String file1 = "D:\\课内学习\\软件工程\\测试文本\\orig.txt";
        String file2 = "D:\\课内学习\\软件工程\\测试文本\\orig_0.8_dis_1.txt";
        String file3 = "D:\\课内学习\\软件工程\\target.txt";
        new MainProcess().main(new String[]{file1,file2,file3});
    }

}
