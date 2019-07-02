package elie29.learning.kata.checkout.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheItems implements ICacheItems
{
   // Item -> scanning count, default to 0
   private final Map<Character, Integer> scanned = new HashMap<>();

   @Override
   public int hit(char code)
   {
      int count = scanned.getOrDefault(code, 0) + 1;
      scanned.put(code, count);

      return count;
   }

   @Override
   public void reset(char code)
   {
      scanned.put(code, 0);
   }
}
