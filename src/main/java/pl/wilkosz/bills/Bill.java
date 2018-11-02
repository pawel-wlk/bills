package pl.wilkosz.bills;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The bill (no pun intended).
 */
public class Bill implements Serializable {
  /**
   * Format for parsing dates.
   */
  private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

  /**
   * List of elements of the bill.
   */
  private List<Element> elements;
  /**
   * Date of issue.
   */
  public Date issueDate;
  /**
   * Date of payment.
   */
  public Date paymentDate;
  /**
   * Identification number of the seller.
   */
  public String sellerId;
  /**
   * Identification number of the buyer.
   */
  public String buyerId;

  /**
   * Constructor.
   * @param issueDate date of issue
   * @param paymentDate date of payment
   * @param sellerId id of seller
   * @param buyerId id of buyer
   * @throws BillException when payment date is before issue date or one of the IDs is empty
   */
  public Bill(final String issueDate, final String paymentDate,
              final String sellerId, final String buyerId)
      throws BillException {
    this.elements = new ArrayList<Element>();
    try {
      this.paymentDate = format.parse(paymentDate);
      this.issueDate = format.parse(issueDate);
    } catch (ParseException e) {
      throw new BillException("incorrect date format");
    }

    if (this.paymentDate.compareTo(this.issueDate) < 0) {
      throw new BillException("payment date cannot be past the issue date");
    }
    if (sellerId.isEmpty()) {
      throw new BillException("Seller id cannot be empty");
    }
    if (buyerId.isEmpty()) {
      throw new BillException("Buyer id cannot be empty");
    }
    this.sellerId = sellerId;
    this.buyerId = buyerId;
  }

  /**
   * Add item in given quantity.
   * @param item the item
   * @param quantity the quantity
   */
  public void addItems(final Item item, final double quantity) throws BillException {
    this.elements.add(new Element(item, quantity));
  }

  /**
   * Get total price.
   */
  public double getTotalPrice() {
    double total = 0;
    for (final Element e : elements) {
      total += e.getTotalPrice();
    }

    return total;
  }

}
