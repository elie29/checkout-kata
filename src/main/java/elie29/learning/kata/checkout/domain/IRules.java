package elie29.learning.kata.checkout.domain;

import elie29.learning.kata.checkout.domain.primitive.Item;

public interface IRules
{
   int scan(Item item);
}
