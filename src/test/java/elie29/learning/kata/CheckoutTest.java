package elie29.learning.kata;

import elie29.learning.kata.checkout.domain.CacheItems;
import elie29.learning.kata.checkout.domain.Checkout;
import elie29.learning.kata.checkout.domain.DiscountItems;
import elie29.learning.kata.checkout.domain.PriceRules;
import elie29.learning.kata.checkout.domain.primitive.Amount;
import elie29.learning.kata.checkout.domain.primitive.Item;
import elie29.learning.kata.checkout.repository.DiscountRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public class CheckoutTest
{

   private Checkout checkout;

   @Before
   public void setUp()
   {
      DiscountItems discountItems = new DiscountItems(new DiscountRepository(), new CacheItems());
      PriceRules priceRules = new PriceRules(discountItems);
      checkout = new Checkout(priceRules);
   }

   @Test
   @Parameters(method = "getItemWithTotal")
   public void shouldScanItemReturnTotal(Item item, int amount)
   {
      checkout.scan(item);

      assertThat(checkout.total(), is(new Amount(amount)));
   }

   protected Object[] getItemWithTotal()
   {
      return new Object[]{
         new Object[]{new Item('A', 50), 50},
         new Object[]{new Item('B', 30), 30},
         new Object[]{new Item('C', 20), 20},
         new Object[]{new Item('D', 15), 15},
      };
   }

   @Test
   public void checkAddItemsIncremental()
   {
      checkout.scan(new Item('A', 50));
      assertThat(checkout.total(), is(new Amount(50)));

      checkout.scan(new Item('B', 30));
      assertThat(checkout.total(), is(new Amount(80)));

      checkout.scan(new Item('A', 50));
      assertThat(checkout.total(), is(new Amount(130)));

      checkout.scan(new Item('C', 20));
      assertThat(checkout.total(), is(new Amount(150)));

      checkout.scan(new Item('B', 30));
      assertThat(checkout.total(), is(new Amount(170)));
   }

   @Test
   public void checkoutSeveralTimeTheSameAmount()
   {
      checkout.scan(new Item('A', 50));
      for (int i = 0; i < 150; ++i) {
         assertThat(checkout.total(), is(new Amount(50)));
      }
   }

   @Test
   @Parameters(method = "getListOfItems")
   public void checkListOfItems(List<Item> items, int total)
   {
      for (Item item : items) {
         checkout.scan(item);
      }
      assertThat(checkout.total(), is(new Amount(total)));
   }

   protected Object[] getListOfItems()
   {
      return new Object[]{
         new Object[]{Arrays.asList(new Item('A', 50), new Item('A', 50)), 100},
         new Object[]{Arrays.asList(new Item('A', 50), new Item('A', 50), new Item('A', 50)), 130},
         new Object[]{Arrays.asList(new Item('B', 30), new Item('B', 30)), 50},
         new Object[]{Arrays.asList(new Item('A', 50), new Item('B', 30)), 80},
         new Object[]{Arrays.asList(new Item('A', 50), new Item('A', 50), new Item('C', 20)), 120},
         new Object[]{Arrays.asList(new Item('A', 50), new Item('A', 50), new Item('C', 20), new Item('D', 15)), 135},
         new Object[]{Arrays.asList(new Item('A', 50), new Item('B', 30), new Item('C', 20), new Item('D', 15)), 115},
      };
   }
}
