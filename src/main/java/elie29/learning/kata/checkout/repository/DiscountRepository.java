package elie29.learning.kata.checkout.repository;

import elie29.learning.kata.checkout.domain.primitive.Discount;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiscountRepository
{
   public Map<Character, Discount> fetchAll()
   {
      return new HashMap<>()
      {
         {
            put('A', new Discount(3, 20));
            put('B', new Discount(2, 10));
         }
      };
   }
}
