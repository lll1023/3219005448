package com;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lsutin
 * @Date: 2021/9/15 20:54
 * @describe:
 */
public class SimHash {

    private String tokens;

    private BigInteger strSimHash;

    private int hashbits = 64;

    public SimHash(String tokens) {
        this.tokens = tokens;
        this.strSimHash = this.simHash();
    }

    public SimHash(String tokens, int hashbits) {
        this.tokens = tokens;
        this.hashbits = hashbits;
        this.strSimHash = this.simHash();
    }

    /**
     * 求得字符串的simHash值
     * @return simHash
     */
    private BigInteger simHash() {

        int[] v = new int[this.hashbits];

        List<Term> termList = StandardTokenizer.segment(this.tokens); // 使用hanlp对字符串进行分词

        //对分词的一些特殊处理 : 比如: 根据词性添加权重 , 过滤掉标点符号 , 过滤超频词汇等;
        Map<String, Integer> weightOfNature = new HashMap<String, Integer>(); // 词性的权重
        Map<String, String> stopNatures = new HashMap<String, String>();//停用的词性,如一些标点符号之类的;
        Map<String, Integer> wordCount = new HashMap<String, Integer>();//超频词汇

        for (Term term : termList) {
            String word = term.word; //分词字符串

            String nature = term.nature.toString(); // 分词属性;
            //  过滤超频词
            if (wordCount.containsKey(word)) {
                continue;
            }

            // 过滤停用词性
            if (stopNatures.containsKey(nature)) {
                continue;
            }

            // 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
            BigInteger t = this.hash(word);
            for (int i = 0; i < this.hashbits; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                // 3、建立一个长度为64的整数数组(假设要生成64位的数字指纹,也可以是其它数字),
                // 对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和末尾一位加1,
                // 中间的62位减一,也就是说,逢1加1,逢0减1.一直到把所有的分词hash数列全部判断完毕.
                int weight = 1;  //添加权重
                if (weightOfNature.containsKey(nature)) {
                    weight = weightOfNature.get(nature);
                }
                if (t.and(bitmask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    v[i] += weight;
                } else {
                    v[i] -= weight;
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        //降维
        for (int i = 0; i < this.hashbits; i++) {
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return fingerprint;
    }

    /**
     * 计算分词的hash
     * @param source 分词
     * @return hash
     */
    private BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            /**
             * 当sourece 的长度过短，会导致hash算法失效，因此需要对过短的词补偿
             */
            while (source.length() < 3) {
                source = source + source.charAt(0);
            }
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    /**
     * 计算海明距离
     * @param other 另一个simHash
     * @return 海明距离
     */
    private int hammingDistance(SimHash other) {
        BigInteger m = new BigInteger("1").shiftLeft(this.hashbits).subtract(
                new BigInteger("1"));
        BigInteger x = this.strSimHash.xor(other.strSimHash).and(m);
        int tot = 0;
        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }

    /**
     * 求得文本相似度
     * @param dis 海明距离
     * @return 相似度
     */
    private String getSemblance(int dis){
        double i=dis;
        //精确小数点两位
        return String.format("%.2f",1-i/this.hashbits);
    }

    /**
     * 只对外提供这一个求得文本相似度的方法
     * @param other
     * @return
     */
    public String getSimilarity(SimHash other){
        return getSemblance(hammingDistance(other));
    }

}