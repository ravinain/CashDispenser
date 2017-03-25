package org.dispenser.cash.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dispenser.cash.utils.CommonUtils;

/**
 * Store denomination mapping and valid denomination list.
 * @author cdacr
 */
public final class DenominationMap {

  /** denomination and its counts mapping. */
  private final Map<Integer, Integer> denominationCountMap = new HashMap<>();
  /** This flag is used to sort the denominationList. */
  private boolean modified = false;
  /** List of all available denominations. */
  private List<Integer> denominationList;
  /** Threshold, default is 100. */
  private int threshold = 100;

  /**
   * Add/Update denomination in the map and set the modified true.
   * @param denomination
   *          denomination
   * @param count
   *          count
   */
  public void add(final Integer denomination, final Integer count) {
    int value = 0;
    if (denominationCountMap.containsKey(denomination)) {
      value = denominationCountMap.get(denomination);
    }
    denominationCountMap.put(denomination, value + count);
    modified = true;
  }

  /**
   * Deduct the denomination count in the map and if after deduction count is
   * zero or less then zero remove it from the map.
   * @param denomination
   *          denomination
   * @param count
   *          count
   */
  public void deduct(final Integer denomination, final Integer count) {
    int value = 0;
    if (denominationCountMap.containsKey(denomination)) {
      value = denominationCountMap.get(denomination);
    }
    if (value - count > 0) {
      denominationCountMap.put(denomination, value - count);
    } else {
      denominationCountMap.remove(denomination);
    }
    modified = true;
  }

  /**
   * Fetch count of denomination.
   * @param key
   *          denomination
   * @return count
   */
  public Integer getDenominationCount(final Integer key) {
    return denominationCountMap.get(key);
  }

  /**
   * Fetch all valid denominations in descending order.
   * @return {@link List} of valid denominations
   */
  public List<Integer> getDenominations() {
    if (modified) {
      denominationList = denominationCountMap.keySet().stream()
          .sorted((a, b) -> b - a).collect(Collectors.toList());
    }
    return denominationList;
  }

  /**
   * Fetch total available cash.
   * @return sum
   */
  public int getTotalAvailableCash() {
    return denominationCountMap.entrySet().stream()
        .mapToInt((e) -> e.getKey() * e.getValue()).sum();
  }

  /**
   * Fetch threshold.
   * @return threshold
   */
  public int getThreshold() {
    return threshold;
  }

  /**
   * Set new threshold value.
   * @param threshold
   *          the threshold to set
   */
  public void setThreshold(final int threshold) {
    this.threshold = threshold;
  }

  @Override
  public String toString() {
    return CommonUtils.prepareCashAvailableOutput(denominationCountMap);
  }

}
