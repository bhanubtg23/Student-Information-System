package com.bhanu.sis.pass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication @EnableFeignClients
public class PassApp {
  public static void main(String[] args){ SpringApplication.run(PassApp.class, args); }
}
