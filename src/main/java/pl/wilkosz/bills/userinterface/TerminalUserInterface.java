package pl.wilkosz.bills.userinterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pl.wilkosz.bills.*;
import pl.wilkosz.bills.databases.Database;
import pl.wilkosz.bills.databases.FileDatabase;
import pl.wilkosz.bills.jsonparser.JsonParser;

/**
 * Basic terminal-based UI.
 */
public class TerminalUserInterface {
  /**
   * Database connection.
   */
  private static final Database db = new FileDatabase();

  /**
   * Main method.
   * @param args args passed by the user, not used.
   */
  public static void main(final String[] args) {
    final Scanner scanner = new Scanner(System.in);
    boolean chosen = false;

    while (!chosen) {
      System.out.println("Add(a) or show(s) bills?");
      final char choice = scanner.nextLine().charAt(0);

      if (choice == 'a') {
        chosen = true;
        addBill();
      } else if (choice == 's') {
        chosen = true;
        showBills();
      }
    }
  }

  private static void showBills() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Show all (a) bills or of a particular buyer (b)?");
    final char choice = scanner.nextLine().charAt(0);

    List<Bill> bills;
    try {
      bills = db.readBills();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return;
    }

    if (choice == 'a') {
      System.out.println(JsonParser.dumpList(bills));
    } else if (choice == 'b') {
      final List<Bill> result = new ArrayList<>();
      System.out.println("provide buyer's id");
      final String id = scanner.nextLine();

      for (final Bill bill : bills) {
        if (bill.buyerId.equals(id)) {
          result.add(bill);
        }
      }
      System.out.println(JsonParser.dumpList(result));
    }
  }

  private static void addBill() {
    final Scanner scanner = new Scanner(System.in);

    System.out.println("Specify seller id");
    final String sellerId = scanner.nextLine();
    System.out.println("Specify buyer id");
    final String buyerId = scanner.nextLine();
    System.out.println("Specify issue date");
    final String issueDate = scanner.nextLine();
    System.out.println("Specify payment date");
    final String paymentDate = scanner.nextLine();

    Bill bill;
    try {
      bill = new Bill(issueDate, paymentDate, sellerId, buyerId);
    } catch (BillException e) {
      System.out.println(e.getMessage());
      return;
    }

    char choice;

    while (true) {
      System.out.println("Add (a) another item or finish (f)?");
      choice = scanner.nextLine().charAt(0);

      if (choice == 'a') {
        System.out.println("Specify name");
        final String name = scanner.nextLine();
        System.out.println("Specify price per unit");
        final double price = scanner.nextDouble();
        System.out.println("Specify quantity");
        final double quantity = scanner.nextDouble();
        scanner.nextLine();

        try {
          bill.addItems(new Item(price, name), quantity);
        } catch (BillException e) {
          System.out.println(e.getMessage());
        }

      } else if (choice == 'f') {
        System.out.println("finishing ...");
        final String json = JsonParser.dump(bill);
        System.out.println(json);

        try {
          db.addBill(bill);
        } catch (IOException e) {
          System.out.println(e.getMessage());
          return;
        }
      }
    }
  }
}
