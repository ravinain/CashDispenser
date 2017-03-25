package org.dispenser.cash.controller.impl;

import java.io.IOException;
import java.util.Map;

import org.dispenser.cash.controller.CashDispenserController;
import org.dispenser.cash.exception.CashDispenseException;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.service.CashDispenserService;
import org.dispenser.cash.service.impl.CashDispenserServiceImpl;
import org.dispenser.cash.utils.CommonUtils;

/**
 * @see CashDispenserController
 * @author cdacr
 */
public class CashDispenserControllerImpl implements CashDispenserController {

  /** cash dispenser service. **/
  private final CashDispenserService service = new CashDispenserServiceImpl();

  /**
   * register this class as observer.
   */
  public CashDispenserControllerImpl() {
    service.registerObserver(this);
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.controller.CashDispenserController#prepareCombination(
   * int)
   */
  @Override
  public final void prepareCombination(final int amount) {
    try {
      final Map<Integer, Integer> combMap = service.prepareCombination(amount);
      System.out.println(CommonUtils.prepareCashDispenseOutput(combMap));
    } catch (final CashDispenseException e) {
      System.err.println(e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.controller.CashDispenserController#loadDenominationMap(
   * java.lang.String)
   */
  @Override
  public final void loadDenominationMap(final String fileName)
      throws IOException, DenominationException {
    service.loadDenominationMap(fileName);
  }

  /*
   * (non-Javadoc)
   * @see
   * org.dispenser.cash.controller.CashDispenserController#getDenominationMap()
   */
  @Override
  public final DenominationMap getDenominationMap() {
    return service.getDenominationMap();
  }

  /*
   * (non-Javadoc)
   * @see org.dispenser.cash.service.Observer#notify(java.lang.String)
   */
  @Override
  public final void notify(final String message) {
    System.out.println(message);
  }
}
