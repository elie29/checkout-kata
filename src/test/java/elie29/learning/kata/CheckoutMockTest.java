package elie29.learning.kata;

import elie29.learning.kata.checkout.domain.Checkout;
import elie29.learning.kata.checkout.domain.IRules;
import elie29.learning.kata.checkout.domain.primitive.Amount;
import elie29.learning.kata.checkout.domain.primitive.Item;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

@RunWith(JUnitParamsRunner.class)
public class CheckoutMockTest
{
   @Rule // for runner purpose
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   private IRules rules;

   private Checkout checkout;

   @Before
   public void setUp()
   {
      checkout = new Checkout(rules);
   }

   @Test
   @Parameters(method = "getItemAndTotal")
   public void checkItemAndTotal(Item item, Amount total)
   {
      // given
      given(rules.scan(item)).willReturn(item.getValue());

      // when
      checkout.scan(item);

      // then
      assertThat(checkout.total(), is(total));
      then(rules).should(times(1)).scan(item);
   }

   @Test
   @Parameters(method = "getItemsAndTotal")
   public void checkItemsAndTotal(List<Item> items, Amount total)
   {
      for (Item item : items) {
         // given
         given(rules.scan(item)).willReturn(item.getValue());
         // when
         checkout.scan(item);
      }

      // then
      assertThat(checkout.total(), is(total));
      then(rules).should(times(items.size())).scan(any(Item.class));
   }

   protected Object[] getItemAndTotal()
   {
      return m(
         m(new Item('A', 50), new Amount(50)),
         m(new Item('B', 30), new Amount(30))
      );
   }

   protected Object[] getItemsAndTotal()
   {
      return m(
         m(Arrays.asList(new Item('A', 50), new Item('A', 50)), new Amount(100)),
         m(Arrays.asList(new Item('A', 50), new Item('B', 30)), new Amount(80)),
         m(Arrays.asList(new Item('A', 50), new Item('B', 30), new Item('C', 60)), new Amount(140))
      );
   }

   private Object[] m(Object... args)
   {
      return args;
   }
}
