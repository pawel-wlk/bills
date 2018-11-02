package pl.wilkosz.bills;

import java.io.Serializable;

/**
 * Singular item that could be put on a bill.
 */
public class Item implements Serializable {
  /**
   * Price of an unit of the item.
   */
  private double price;
  /**
   * Full name of a product.
   */
  private String fullName;

  /**
   * Basic constructor.
   * @param price price of the item
   * @param fullName full name of the item
   * @throws BillException when price is negative or name is empty.
   */
  public Item(final double price, final String fullName) throws BillException {
    if (price < 0) {
      throw new BillException("price cannot be negative");
    }
    if (fullName.isEmpty()) {
      throw new BillException("product name cannot be empty");
    }
    this.price = price;
    this.fullName = fullName;
  }

  public double getPrice() {
    return this.price;
  }
}
