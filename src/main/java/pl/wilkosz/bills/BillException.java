package pl.wilkosz.bills;

/**
 * Exception for wrong bill data.
 */
public class BillException extends Exception {
  public BillException(String m) {
    super(m);
  }
}
