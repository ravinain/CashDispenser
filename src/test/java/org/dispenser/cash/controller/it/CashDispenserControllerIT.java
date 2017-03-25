package org.dispenser.cash.controller.it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.dispenser.cash.controller.CashDispenserController;
import org.dispenser.cash.controller.impl.CashDispenserControllerImpl;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.utils.CommonUtils;
import org.dispenser.cash.utils.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link CashDispenserController} Test.
 * @author cdacr
 */
public class CashDispenserControllerIT {

  /** CashDispenserController instance. */
  private CashDispenserController controller = null;

  /** Expected Exception rule. */
  @Rule
  public ExpectedException expect = ExpectedException.none();

  /** Store console output. */
  private final ByteArrayOutputStream content = new ByteArrayOutputStream();
  /** Stream for console output. */
  private PrintStream printStream = null;

  /**
   * Initialization before executing test case.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    controller = new CashDispenserControllerImpl();
    printStream = new PrintStream(content);
    System.setErr(printStream);
    System.setOut(printStream);
  }

  /**
   * Release resources after executing test case.
   * @throws Exception
   *           Exception
   */
  @After
  public final void tearDown() throws Exception {
    controller = null;
    printStream = null;
    System.setErr(null);
    System.setOut(null);
  }

  /**
   * {@link CashDispenserController#prepareCombination(int)}.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testPrepareCombination()
      throws IOException, DenominationException {
    final String expectedOutput = "Dispensed Cash : 50x1 20x3 ";
    controller.loadDenominationMap(Constants.DENO_MAP_FILE);
    controller.prepareCombination(110);
    assertThat(content.toString(), containsString(expectedOutput));
  }

  /**
   * {@link CashDispenserController#prepareCombination(int)} Error scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testPrepareCombinationError()
      throws IOException, DenominationException {
    controller.loadDenominationMap(Constants.DENO_MAP_FILE);
    controller.prepareCombination(30);
    assertThat(content.toString(),
        containsString(String.format(Constants.UNABLE_TO_DISPENSE, 30,
            CommonUtils.listToStringWithSeparator(
                controller.getDenominationMap().getDenominations(),
                Constants.DENO_LIST_SEPARATOR))));
  }

  /**
   * {@link CashDispenserController#loadDenominationMap(String)}.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMap()
      throws IOException, DenominationException {
    controller.loadDenominationMap(Constants.DENO_MAP_FILE);
    final DenominationMap denominationMap = controller.getDenominationMap();
    assertNotNull(denominationMap);
    assertThat(denominationMap.getDenominationCount(50), is(4));
    assertThat(denominationMap.getDenominationCount(20), is(6));
  }

  /**
   * {@link CashDispenserController#loadDenominationMap(String)}.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapAndNotify()
      throws IOException, DenominationException {
    controller.loadDenominationMap(Constants.LESS_THAN_THRESHOLD_MAP_FILE);
    final DenominationMap denominationMap = controller.getDenominationMap();
    assertNotNull(denominationMap);
    assertThat(denominationMap.getDenominationCount(50), is(1));
    assertThat(denominationMap.getDenominationCount(20), is(2));

    final String expected = String.format(Constants.THRESHOLD_WARNING_MSG,
        denominationMap.getTotalAvailableCash());
    assertTrue((content.toString()).contains(expected));
  }

  /**
   * {@link CashDispenserController#loadDenominationMap(String)} IOException
   * Scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapException()
      throws IOException, DenominationException {
    expect.expect(IOException.class);
    controller.loadDenominationMap(Constants.DENO_MAP_FILE + "abac");
  }

  /**
   * {@link CashDispenserController#loadDenominationMap(String)}
   * DenominationException Scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapDenominationException()
      throws IOException, DenominationException {
    expect.expect(DenominationException.class);
    controller.loadDenominationMap(Constants.INVALID_DATA_FILE);
  }
}
