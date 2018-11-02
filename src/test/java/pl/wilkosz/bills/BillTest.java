package pl.wilkosz.bills;

import org.junit.Test;
import org.junit.Assert;

public class BillTest {
  @Test
  public void testNegativePrice() {
    Item item = null;
    try {
      item = new Item(-1000, "test name");
    } catch (BillException e) {
      e.printStackTrace();

    }
    Assert.assertNull(item);
  }

  @Test
  public void testNegativeQuantity() {
    Element el = null;

    try {
      el = new Element(new Item(1, "test"), -1);
    } catch (BillException e) {
      e.printStackTrace();
    }

    Assert.assertNull(el);
  }

  @Test
  public void testCorrectDates() {
    Bill b = null;

    try {
      b = new Bill("12.01.1998", "11.01.1998", "123", "123");
    } catch (BillException e) {
      e.printStackTrace();
    }

    Assert.assertNull(b);
  }

  @Test
  public void testEmptyItemName() {
    Item i = null;

    try {
      i = new Item(10, "");
    } catch (BillException e) {
      e.printStackTrace();
    }

    Assert.assertNull(i);
  }
  @Test
  public void testEmptyBuyerId() {
    Bill b = null;

    try {
      b = new Bill("12.01.1998", "12.01.1999", "a", "");
    } catch (BillException e) {
      e.printStackTrace();
    }

    Assert.assertNull(b);
  }
  @Test
  public void testEmptySellerId() {
    Bill b = null;

    try {
      b = new Bill("12.01.1998", "12.01.1999", "", "a");
    } catch (BillException e) {
      e.printStackTrace();
    }

    Assert.assertNull(b);
  }


}
