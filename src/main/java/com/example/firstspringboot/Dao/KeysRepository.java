package com.example.firstspringboot.Dao;

import com.example.firstspringboot.domin.Keysbase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeysRepository extends JpaRepository<Keysbase,Long> {

    public List<Keysbase> findAll();

}
