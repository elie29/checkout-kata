package elie29.learning.kata.checkout.domain;

import elie29.learning.kata.checkout.domain.primitive.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PriceRules implements IRules
{
   private final IDiscountItems discountItems;

   @Override
   public int scan(Item item)
   {
      discountItems.applyFor(item);

      return item.getValue();
   }
}
