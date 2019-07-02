package elie29.learning.kata.checkout.domain.primitive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item
{
   @EqualsAndHashCode.Include
   private final char code;

   private int value;

   public Item(char code, int value)
   {
      this.code = code;
      this.value = value;
   }

   public void reduce(int amount)
   {
      value -= amount;
   }
}
