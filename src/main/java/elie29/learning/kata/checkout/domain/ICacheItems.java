package elie29.learning.kata.checkout.domain;

public interface ICacheItems
{
   int hit(char code);

   void reset(char code);
}
