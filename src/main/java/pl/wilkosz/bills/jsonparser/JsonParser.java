package pl.wilkosz.bills.jsonparser;

import com.google.gson.*;
import java.util.List;
import pl.wilkosz.bills.*;

public class JsonParser {
  /**
   * Dumps bill in form of JSON string.
   * @param bill bill to dump
   * @return JSON string
   */
  public static String dump(final Bill bill) {
    return toJson(bill).toString();
  }

  /**
   * Dumps list of bills in form of json string.
   * @param bills list of bills
   * @return json string
   */
  public static String dumpList(final List<Bill> bills) {
    final JsonArray arr = new JsonArray();

    for (final Bill b : bills) {
      arr.add(toJson(b));
    }

    return arr.toString();
  }

  /**
   * Creates JSON object from bill.
   * @param bill bill to parse
   * @return Gson Json object
   */
  private static JsonObject toJson(final Bill bill) {
    final Gson gs = new Gson();
    final JsonObject jsonObj = gs.toJsonTree(bill).getAsJsonObject();
    jsonObj.addProperty("totalPrice", bill.getTotalPrice());
    return jsonObj;
  }
}
