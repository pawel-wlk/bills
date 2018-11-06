package pl.wilkosz.bills.databases;


import java.io.IOException;
import pl.wilkosz.bills.Bill;
import java.util.ArrayList;

public interface Database {
  ArrayList<Bill> readBills() throws IOException;

  void saveBills(ArrayList<Bill>  bills) throws IOException;

  void addBill(Bill bill) throws IOException;
}
