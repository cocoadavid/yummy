package com.codecool.yummy;

import com.codecool.yummy.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * Created by szilarddavid on 2017.06.21..
 */

@SpringBootApplication
public class Yummy implements CommandLineRunner {

    @Resource
    StorageService storageService;
    public static void main(String[] args){
        SpringApplication.run(Yummy.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
