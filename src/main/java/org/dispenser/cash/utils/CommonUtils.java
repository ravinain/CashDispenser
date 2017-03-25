package org.dispenser.cash.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Common utility file.
 * @author cdacr
 */
public final class CommonUtils {

  /** Don't allow instantiation. */
  private CommonUtils() {
  }

  /**
   * Prepares the cash dispense output based on input mapping.
   * @param inputMap
   *          combination of denomination and its count.
   * @return Formatted output.
   */
  public static String
      prepareCashDispenseOutput(final Map<Integer, Integer> inputMap) {
    final StringBuilder output = new StringBuilder();
    output.append("Dispensed Cash : ");
    inputMap
        .forEach((k, v) -> output.append(k).append("x").append(v).append(" "));
    return output.toString();
  }

  /**
   * Prepares the cash dispense output based on input mapping.
   * @param inputMap
   *          combination of denomination and its count.
   * @return Formatted output.
   */
  public static String
      prepareCashAvailableOutput(final Map<Integer, Integer> inputMap) {
    final StringBuilder output = new StringBuilder();
    output.append("Available Cash : ");
    inputMap
        .forEach((k, v) -> output.append(k).append("x").append(v).append(" "));
    return output.toString();
  }

  /**
   * Convert list of integer to string with separated by input separator.
   * @param inputList
   *          Input List
   * @param separator
   *          Separator
   * @return Formatted string.
   */
  public static String listToStringWithSeparator(final List<Integer> inputList,
      final String separator) {
    return inputList.stream().map(i -> i.toString())
        .collect(Collectors.joining(separator));
  }
}
