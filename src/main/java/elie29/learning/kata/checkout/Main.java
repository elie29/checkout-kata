package elie29.learning.kata.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// all components should be under checkout package
public class Main
{

   public static void main(String[] args)
   {
      SpringApplication.run(Main.class, args);
   }
}