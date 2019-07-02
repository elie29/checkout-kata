package elie29.learning.kata.checkout.domain;

import elie29.learning.kata.checkout.domain.primitive.Amount;
import elie29.learning.kata.checkout.domain.primitive.Item;
import org.springframework.stereotype.Component;

@Component
public class Checkout
{
   private final IRules rules;
   private int amount = 0;

   public Checkout(IRules rules)
   {
      this.rules = rules;
   }

   public void scan(Item item)
   {
      // Tell, don't ask
      amount += rules.scan(item);
   }

   public Amount total()
   {
      return new Amount(amount);
   }
}
