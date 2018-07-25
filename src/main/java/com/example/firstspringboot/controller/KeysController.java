package com.example.firstspringboot.controller;

import com.example.firstspringboot.Dao.KeysRepository;
import com.example.firstspringboot.domin.Keysbase;
import com.example.firstspringboot.service.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keys")
public class KeysController {
    @Autowired
    private KeysRepository keysRepository;

    @Autowired
    private KeysService keysService;

     /*
      * @author liuxiongfeng
      * @date ${DATE} ${TIME}
      * @param 
      * @return * 插入两条数据
      */
    @PostMapping(value = "/insert")
    public void insert(){
        keysService.insertTwo();
    }
    /*
     * @Author: liuxiongfeng
     * @Date: 16:01 2018-7-11
     * @Description: 保存数据
     **/
    @PostMapping("/save")
    public Keysbase save(String name,String url){
        Keysbase keysbase = new Keysbase();
        keysbase.setUrl(url);
        keysbase.setName(name);
        return keysRepository.save(keysbase);

    }
    
     /*
      * @author liuxiongfeng
      * @date ${DATE} ${TIME}
      * @param 
      * @return  * 获取所有数据
      */
    @GetMapping("/getAll")
    public List<Keysbase> getAll(){
        return keysRepository.findAll();
    }

    /*
     * @Author: liuxiongfeng
     * @Date: 15:57 2018-7-11
     * @Description: 句子相似度计算接口
     **/
    @PostMapping("/textMatch")
    public Object[] textMatch(String text){
        try {
            Object[] objects = keysService.textMatch(text);
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
