package com.example.firstspringboot.service;

import com.example.firstspringboot.Dao.KeysRepository;
import com.example.firstspringboot.demo.IKAnlyzerUtils;
import com.example.firstspringboot.domin.Keysbase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class KeysServiceiml implements KeysService {

    @Autowired
    private KeysRepository keysRepository;


   /*
    * @Author: liuxiongfeng
    * @Date: 14:51 2018-7-11
    * @Description: 插入两条数据，测试事物
    **/
    @Transactional
    public void insertTwo() {
        Keysbase keysbase = new Keysbase();
        keysbase.setName("d");
        keysbase.setUrl("sdsdsd");
        keysRepository.save(keysbase);

        Keysbase keysbase2 = new Keysbase();
        keysbase2.setName("wwww");
        keysbase2.setUrl("sdsdsd");
        keysRepository.save(keysbase2);
    }

    /*
     * @Author: liuxiongfeng
     * @Date: 13:56 2018-7-11
     * @Description: 传入文字，匹配到最合适的功能
     **/
    public Object[] textMatch(String text) throws Exception{
        List<Keysbase> all = keysRepository.findAll();
        Object[] arrays = all.toArray();
        Arrays.sort(arrays, new Comparator<Object>(){
            public int compare(Object arg0,Object arg1){
                Keysbase a=(Keysbase)arg0;
                Keysbase b=(Keysbase)arg1;
                int temp= 0;
                try {
                    double x = IKAnlyzerUtils.getSimilarity(a.getName(),text);
                    double y = IKAnlyzerUtils.getSimilarity(b.getName(),text);
                    temp = (x < y) ? -1 : ((x == y) ? 0 : 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return -temp;
            }
        });
        return arrays;
    }

}

