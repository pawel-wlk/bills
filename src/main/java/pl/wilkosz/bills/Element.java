package pl.wilkosz.bills;

import java.io.Serializable;

/**
 * Element of a bill that contains an item and its quantity.
 */
public class Element implements Serializable {
  /**
   * Item contained in bill element.
   */
  private Item item;
  /**
   * Quantity of the item.
   */
  private double quantity;

  /**
   * Constructor.
   * @param item Item in element.
   * @param quantity Quantity of the item.
   * @throws BillException when quantity is negative.
   */
  public Element(final Item item, final double quantity) throws BillException {
    if (quantity < 0) {
      throw new BillException("quantity cannot be negative");
    }
    this.item = item;
    this.quantity = quantity;
  }

  public double getTotalPrice() {
    return this.item.getPrice() * this.quantity;
  }
}

