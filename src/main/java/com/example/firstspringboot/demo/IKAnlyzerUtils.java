package com.example.firstspringboot.demo;

import org.springframework.util.StringUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class IKAnlyzerUtils {


    //阈值
    public static double YUZHI = 0.2;
    public static void main(String[] args) {
        List<String> arr1 = participle("客户");
        List<String> arr2 = participle("我的客户");
        List<String> arr3 = participle("查看张三的佣金率 ");

        try {
            double percent = getSimilarity(arr1, arr2);
            double percent2 = getSimilarity(arr3, arr2);
            if (!StringUtils.isEmpty(percent)){
                System.out.println(percent);
                System.out.println(percent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
     * @Author: liuxiongfeng
     * @Date: 13:58 2018-7-11
     * @Description: 对输入的句子进行分词，返回分词后的数组
     **/
    public static List<String> participle(String str) {
        List<String> str1 = new Vector<String>();//对输入进行分词
        try {
            StringReader reader = new StringReader(str);
            IKSegmenter ik = new IKSegmenter(reader, true);//当为true时，分词器进行最大词长切分
            Lexeme lexeme = null;
            while ((lexeme = ik.next()) != null) {
                str1.add(lexeme.getLexemeText());
            }
            if (str1.size() == 0) {
                return null;
            }
            //分词后
            System.out.println("str分词后：" + str1);
        } catch (IOException e1) {
            System.out.println();
        }
        return str1;
    }
    /*
     * @Author: liuxiongfeng
     * @Date: 9:11 2018-7-12
     * @Description: return利用余弦定理计算分词结果相似度百分比
     **/
    public static double getSimilarity(List<String> T1, List<String> T2) throws Exception {
        int size = 0, size2 = 0;
        if (T1 != null && (size = T1.size()) > 0 && T2 != null && (size2 = T2.size()) > 0) {
            Map<String, double[]> T = new HashMap<String, double[]>();
            //T1和T2的并集T
            String index = null;
            for (int i = 0; i < size; i++) {
                index = T1.get(i);
                if (index != null) {
                    double[] c = T.get(index);
                    c = new double[2];
                    c[0] = 1;    //T1的语义分数Ci
                    c[1] = YUZHI;//T2的语义分数Ci
                    T.put(index, c);
                }
            }
            for (int i = 0; i < size2; i++) {
                index = T2.get(i);
                if (index != null) {
                    double[] c = T.get(index);
                    if (c != null && c.length == 2) {
                        c[1] = 1; //T2中也存在，T2的语义分数=1
                    } else {
                        c = new double[2];
                        c[0] = YUZHI; //T1的语义分数Ci
                        c[1] = 1; //T2的语义分数Ci
                        T.put(index, c);
                    }
                }
            }
            //开始计算，百分比
            Iterator<String> it = T.keySet().iterator();
            double s1 = 0, s2 = 0, Ssum = 0;  //S1、S2
            while (it.hasNext()) {
                double[] c = T.get(it.next());
                Ssum += c[0] * c[1];
                s1 += c[0] * c[0];
                s2 += c[1] * c[1];
            }
            //百分比
            return Ssum / Math.sqrt(s1 * s2);
        } else {
            throw new Exception("传入参数有问题！");
        }
    }


    public static double getSimilarity(String s1, String s2) throws Exception{
        List<String> t1 = participle(s1);
        List<String> t2 = participle(s2);
        return getSimilarity(t1,t2);
    }
}
