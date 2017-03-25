package org.dispenser.cash.utils;

/**
 * Constants file.
 * @author cdacr
 */
public final class Constants {

  /** Don't allow instantiation. */
  private Constants() {
  }

  /** Invalid denomination mapping message. */
  public static final String INVALID_DENOMINATION_MAPPING =
      "Invalid denomination mapping: %s";

  /** Cash dispense error message. */
  public static final String UNABLE_TO_DISPENSE =
      "Unable to dispense %d. Either ATM has insufficient "
          + "cash or entered amount is not multiple of %s.";

  /** Denomination mapping file. */
  public static final String DENO_MAP_FILE = "Deno_Map.properties";

  /** Denomination mapping file. */
  public static final String LESS_THAN_THRESHOLD_MAP_FILE =
      "Less_Than_Threshold.properties";

  /** Denomination list separator. */
  public static final String DENO_LIST_SEPARATOR = ", ";

  /** Invalid data mapping file. */
  public static final String INVALID_DATA_FILE = "Invalid_Data.properties";
  /** Threshold warning message, pass the available cash. */
  public static final String THRESHOLD_WARNING_MSG =
      "Warning: Only %d cash has left in machine.";
}
