package pl.wilkosz.bills.databases;

import java.io.*;
import java.util.ArrayList;
import pl.wilkosz.bills.Bill;

/**
 * Interface for saving bills into the binary file (Java basic serialization).
 */
public class FileDatabase implements Database {
  /**
   * Constant database location.
   */
  private  static String DB_FILE = "/home/wilkosz/fakturki";

  /**
   * Reads bills from file.
   * @return ArrayList of bills.
   * @throws IOException When database not found or corrupted.
   */
  public ArrayList<Bill> readBills() throws IOException {
    ArrayList<Bill> bills = new ArrayList<>();
    try (ObjectInputStream objectInputStream =
             new ObjectInputStream(new FileInputStream(DB_FILE))) {
      bills = (ArrayList<Bill>) objectInputStream.readObject();
    } catch (FileNotFoundException notfound) {
      throw new IOException("Database not found");
    } catch (IOException ioe) {
      // pass occurs only when file is empty
    } catch (ClassNotFoundException cnfe) {
      throw new IOException("Database corrupted");
    }

    return bills;
  }

  /**
   * Adds bill to the database file.
   * @param bill bill added to database.
   * @throws IOException when database not found or corrupted.
   */
  public void addBill(Bill bill) throws IOException {
    ArrayList<Bill> bills = readBills();
    bills.add(bill);
    saveBills(bills);
  }

  /**
   * Saves whole bills list to file.
   * @param bills ArrayList of bills.
   * @throws IOException when database not found.
   */
  public void saveBills(ArrayList<Bill> bills) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
      out.writeObject(bills);
    } catch (FileNotFoundException notfound) {
      throw new IOException("Database not found");
    } catch (IOException ioe) {
      // pass, occurs only with empty file
    }
  }
}


