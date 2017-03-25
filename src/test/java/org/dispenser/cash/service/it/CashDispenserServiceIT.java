package org.dispenser.cash.service.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Map;

import org.dispenser.cash.exception.CashDispenseException;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.service.CashDispenserService;
import org.dispenser.cash.service.impl.CashDispenserServiceImpl;
import org.dispenser.cash.utils.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link CashDispenserService} Test.
 * @author cdacr
 */
public class CashDispenserServiceIT {

  /** CashDispenserService instance. */
  private CashDispenserService service = null;

  /** Expected exception rule. */
  @Rule
  public ExpectedException expect = ExpectedException.none();

  /**
   * Initialization before executing test case.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    service = new CashDispenserServiceImpl();
  }

  /**
   * Release resources after executing test case.
   * @throws Exception
   *           Exception
   */
  @After
  public final void tearDown() throws Exception {
    service = null;
  }

  /**
   * {@link CashDispenserService#loadDenominationMap(String)}.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMap()
      throws IOException, DenominationException {
    service.loadDenominationMap(Constants.DENO_MAP_FILE);
    final DenominationMap denominationMap = service.getDenominationMap();
    assertNotNull(denominationMap);
    assertThat(denominationMap.getDenominationCount(50), is(4));
    assertThat(denominationMap.getDenominationCount(20), is(6));
  }

  /**
   * {@link CashDispenserService#loadDenominationMap(String)} Exception
   * scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapException()
      throws IOException, DenominationException {
    expect.expect(IOException.class);
    service.loadDenominationMap(Constants.DENO_MAP_FILE + "abac");
  }

  /**
   * {@link CashDispenserService#loadDenominationMap(String)}
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
    service.loadDenominationMap("Invalid_Data.properties");
  }

  /**
   * {@link CashDispenserService#prepareCombination(int)}.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   * @throws CashDispenseException
   *           CashDispenseException
   */
  @Test
  public final void testPrepareCombination()
      throws IOException, DenominationException, CashDispenseException {
    service.loadDenominationMap(Constants.DENO_MAP_FILE);
    final Map<Integer, Integer> output = service.prepareCombination(110);
    assertNotNull(output);
    assertThat(output.get(50), is(1));
    assertThat(output.get(20), is(3));
  }

  /**
   * {@link CashDispenserService#prepareCombination(int)} CashDispenseException
   * scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   * @throws CashDispenseException
   *           CashDispenseException
   */
  @Test
  public final void testPrepareCombinationCashDispenseException()
      throws IOException, DenominationException, CashDispenseException {
    service.loadDenominationMap(Constants.DENO_MAP_FILE);
    expect.expect(CashDispenseException.class);
    service.prepareCombination(30);
  }

}
