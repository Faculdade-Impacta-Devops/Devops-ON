package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest   
public class MessageTest {
   
   @Test
   public void messageOk1() {
       Message message = new Message();
       String result = message.message(5);
       assertEquals("YES", result);
   }
   
   @Test
   public void messageOk2() {
       Message message = new Message();
       String result = message.message(-5);
       assertEquals("NO", result);
   }
}
