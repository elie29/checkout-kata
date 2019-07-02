package elie29.learning.kata.checkout.domain;

import elie29.learning.kata.checkout.domain.primitive.Discount;
import elie29.learning.kata.checkout.domain.primitive.Item;
import elie29.learning.kata.checkout.repository.DiscountRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DiscountItems implements IDiscountItems
{
   private final Map<Character, Discount> discountItems;
   private final ICacheItems cacheItems;

   public DiscountItems(DiscountRepository discountRepository, ICacheItems cacheItems)
   {
      discountItems = discountRepository.fetchAll();
      this.cacheItems = cacheItems;
   }

   @Override
   public void applyFor(Item item)
   {
      char code = item.getCode();

      if (!discountItems.containsKey(code)) {
         return;
      }

      Discount discount = discountItems.get(code);

      if (discount.getCount() == cacheItems.hit(code)) {
         item.reduce(discount.getAmount());
         cacheItems.reset(code); // found -> reset
      }
   }
}
