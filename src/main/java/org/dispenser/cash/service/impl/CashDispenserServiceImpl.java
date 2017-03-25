package org.dispenser.cash.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dispenser.cash.exception.CashDispenseException;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.service.CashDispenserService;
import org.dispenser.cash.service.Observer;
import org.dispenser.cash.utils.CommonUtils;
import org.dispenser.cash.utils.Constants;
import org.dispenser.cash.utils.FileUtils;

/**
 * @see CashDispenserService
 * @author cdacr
 */
public class CashDispenserServiceImpl
    implements CashDispenserService {

  /** FileUtils instance. */
  private final FileUtils fileUtils = new FileUtils();
  /** default start index of preparing combination. */
  private static final int DEFAULT_START_INDEX = 0;
  /** This stores mapping of denomination and its count. */
  private final DenominationMap denominationMap = new DenominationMap();
  /** Collection of registered observers. */
  private final List<Observer> thresholdObservers = new ArrayList<>();

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.service.CashDispenserService#prepareCombination(int)
   */
  @Override
  public final Map<Integer, Integer> prepareCombination(final int amount)
      throws CashDispenseException {
    final Map<Integer, Integer> combMap = new HashMap<>();
    final boolean flag =
        checkAndPrepareCombination(combMap, amount, DEFAULT_START_INDEX);
    if (!flag) {
      throw new CashDispenseException(
          String.format(Constants.UNABLE_TO_DISPENSE, amount,
              CommonUtils.listToStringWithSeparator(
                  denominationMap.getDenominations(),
                  Constants.DENO_LIST_SEPARATOR)));
    }
    if (getDenominationMap().getTotalAvailableCash() < getDenominationMap()
        .getThreshold()) {
      this.notifyObservers();
    }
    return combMap;
  }

  /**
   * Check and prepare the combination. If combination can be prepared then add
   * that combination in combMap and returns true otherwise false.
   * @param combMap
   *          map to store combination
   * @param amount
   *          amount
   * @param startIndex
   *          starting index to look for denomination
   * @return TRUE|FALSE
   */
  private boolean checkAndPrepareCombination(
      final Map<Integer, Integer> combMap, final int amount,
      final int startIndex) {
    boolean flag = false;
    final List<Integer> denominationList = denominationMap.getDenominations();
    for (int i = startIndex; i < denominationList.size(); i++) {
      final int denomination = denominationList.get(i);
      if (denomination > amount) {
        continue;
      }
      int maxD = amount / denomination;
      if (maxD > denominationMap.getDenominationCount(denomination)) {
        maxD = denominationMap.getDenominationCount(denomination);
      }

      int remainingAmount = amount - maxD * denomination;
      if (remainingAmount == 0) {
        flag = true;
      } else {
        while (!flag && maxD > 0) {
          flag = checkAndPrepareCombination(combMap, remainingAmount, i + 1);
          if (!flag) {
            maxD--;
            remainingAmount = amount - maxD * denomination;
          }
        }
      }

      if (flag) {
        combMap.put(denomination, maxD);
        denominationMap.deduct(denomination, maxD);
        break;
      }
    }

    return flag;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.service.CashDispenserService#loadDenominationMap(java.
   * lang.String)
   */
  @Override
  public final void loadDenominationMap(final String fileName)
      throws IOException, DenominationException {
    fileUtils.loadDenominationMap(denominationMap, fileName);
    if (getDenominationMap().getTotalAvailableCash() < getDenominationMap()
        .getThreshold()) {
      this.notifyObservers();
    }
  }

  /*
   * (non-Javadoc)
   * @see org.dispenser.cash.service.CashDispenserService#getDenominationMap()
   */
  @Override
  public final DenominationMap getDenominationMap() {
    return this.denominationMap;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.service.Threshold#registerObserver(org.dispenser.cash.
   * service.Observer)
   */
  @Override
  public final void registerObserver(final Observer observer) {
    thresholdObservers.add(observer);
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.service.Threshold#removeObserver(org.dispenser.cash.
   * service.Observer)
   */
  @Override
  public final void removeObserver(final Observer observer) {
    thresholdObservers.remove(observer);
  }

  /*
   * (non-Javadoc)
   * @see org.dispenser.cash.service.Threshold#notifyObservers()
   */
  @Override
  public final void notifyObservers() {
    for (final Observer observer : thresholdObservers) {
      observer.notify(String.format(Constants.THRESHOLD_WARNING_MSG,
          getDenominationMap().getTotalAvailableCash()));
    }
  }
}
