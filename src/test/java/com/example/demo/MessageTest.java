package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.Message; 

@SpringBootTest   
public class MessageTest {
   
   @Test
   public void messageOk1() {
       Message message = new Message();
       String result = message.getMessage(5);
       assertEquals("YES", result);
   }
   
   @Test
   public void messageOk2() {
       Message message = new Message();
       String result = message.getMessage(-5);
       assertEquals("NO", result);
   }
}
