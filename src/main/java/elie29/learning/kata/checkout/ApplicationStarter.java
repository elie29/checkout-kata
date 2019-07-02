package elie29.learning.kata.checkout;

import elie29.learning.kata.checkout.domain.Checkout;
import elie29.learning.kata.checkout.domain.primitive.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component // to be scanned by the the container
@Slf4j
public class ApplicationStarter
{

   @Autowired
   ApplicationContext ctx;

   @EventListener(ContextRefreshedEvent.class)
   public void start()
   {
      Checkout checkout = ctx.getBean(Checkout.class);

      checkout.scan(new Item('A', 50));
      checkout.scan(new Item('B', 30));
      checkout.scan(new Item('B', 30));

      // should print out out 100
      log.info("Checkout result {}", checkout.total());
   }
}
