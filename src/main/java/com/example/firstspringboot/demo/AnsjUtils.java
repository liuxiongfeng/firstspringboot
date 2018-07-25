package com.example.firstspringboot.demo;/*
 * @Author liuxiongfeng
 * @Description Ansj分词器的功能类
 */

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;

public class AnsjUtils {

    public static void main(String[] args) {

        //System.out.println(to62RadixString(10000000000l));
        Map<String,List<String>> map =  fcbyNature("查看张三的佣金率");

        System.out.println(map);
        //System.out.println(radixString("aUKYOA"));
        List<String> vector = IKAnlyzerUtils.participle("查看张三的佣金率");
        Map<String, List<String>> map1 = addNature2(vector);
        System.out.println(map1);
    }
    /*
     * @Author: liuxiongfeng
     * @Date: 13:55 2018-7-12
     * @Description: 获取不同词性的分词结果
     *
     **/
    public static Map<String,List<String>> fcbyNature(String str){
        Map<String,List<String>> map = new HashMap<>();
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        Result result = ToAnalysis.parse(str); //分词结果的一个封装，主要是一个List<Term>的terms
        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms

        System.out.println(terms.size());

        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if (map.containsKey(natureStr)){
                map.get(natureStr).add(word);
            }else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(natureStr,list);
            }
        }
        return map;
    }
    public static List<Term> addNature1(String[] strs){
        List<String> lists = Arrays.asList(strs) ;
        //对现有的词组进行词性标注
        List<Term> recognition = new NatureRecognition().recognition(lists, 0) ;
        System.out.println(recognition);
        return recognition;
    }
    public static Map<String,List<String>> addNature2(List<String> lists){
        Map<String,List<String>> map = new HashMap<>();
        //对现有的词组进行词性标注
        List<Term> terms = new NatureRecognition().recognition(lists, 0) ;
        System.out.println(terms.size());

        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if (map.containsKey(natureStr)){
                map.get(natureStr).add(word);
            }else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(natureStr,list);
            }
        }
        System.out.println(terms);
        return map;
    }



    /*62进制数值表*/
    static final char[] DIGITS =
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                    'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /*
     * @Author: liuxiongfeng
     * @Date: 15:17 2018-7-16
     * @Description: 将10进制转换成62进制的数
     **/
    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % 62);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / 62;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.reverse().toString();
    }

    /*
     * @Author: liuxiongfeng
     * @Date: 15:18 2018-7-16
     * @Description: 将62进制转换成10进制
     **/
    private static long radixString(String str) {
        long sum = 0l;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            sum += indexDigits(str.charAt(len-i-1))*Math.pow((double)62,(double)i);

        }
        return sum;
    }

    /*
     * @Author: liuxiongfeng
     * @Date: 15:18 2018-7-16
     * @Description: 找到字符在62进制中代表的10进制大小
     **/
    private static int indexDigits(char ch){
        for (int i = 0; i < DIGITS.length; i++) {
            if (ch == DIGITS[i]){
                return i;
            }
        }
        return -1;
    }

}
