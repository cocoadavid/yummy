package com.codecool.yummy;

import com.codecool.yummy.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

import javax.annotation.Resource;

/**
 * Created by szilarddavid on 2017.06.21..
 */

@SpringBootApplication
//FIST VERSION OF IMAGE UPLOADING
// @EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
public class Yummy implements CommandLineRunner {

    @Resource
    StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Yummy.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}