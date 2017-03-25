package org.dispenser.cash.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.dispenser.cash.exception.CashDispenseException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.service.CashDispenserService;
import org.dispenser.cash.service.impl.CashDispenserServiceImpl;
import org.dispenser.cash.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.powermock.reflect.Whitebox;

/**
 * Test class for {@link CashDispenserService}.
 * @author cdacr
 */
@RunWith(Parameterized.class)
@PrepareForTest({CashDispenserServiceImpl.class, FileUtils.class})
public class CashDispenserServiceTest {

  /** CashDispenserService instance. */
  private CashDispenserService service;

  /** Initialize PowerMockito. */
  @Rule
  public PowerMockRule rule = new PowerMockRule();

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

  /** Amount. */
  @Parameter(0)
  public int amount;
  /** Expected First Key. */
  @Parameter(1)
  public int expectedFirstKey;
  /** Expected First Value. */
  @Parameter(2)
  public int expectedFirstValue;
  /** Expected Second Key. */
  @Parameter(3)
  public int expectedSecondKey;
  /** Expected Second Value. */
  @Parameter(4)
  public int expectedSecondValue;
  /** Amount for negative scenario. */
  @Parameter(5)
  public int amount2;

  /**
   * Test data.
   * @return Collection of test data
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {{110, 50, 1, 20, 3, 30}, {120, 50, 2, 20, 1, 10},
            {130, 50, 1, 20, 4, 125}, {140, 50, 2, 20, 2, 155},
            {90, 50, 1, 20, 2, 175}, {250, 50, 3, 20, 5, 215}});
  }

  /**
   * Test method for {@link CashDispenserService#prepareCombination(int)}.
   * @throws CashDispenseException
   *           CashDispenseException
   */
  @Test
  public final void testPrepareCombination() throws CashDispenseException {
    final DenominationMap denoMap = new DenominationMap();
    denoMap.add(50, 4);
    denoMap.add(20, 6);
    Whitebox.setInternalState(service, DenominationMap.class, denoMap);

      final Map<Integer, Integer> combMap = service.prepareCombination(amount);
      assertNotNull(combMap);
      assertThat(combMap.get(expectedFirstKey).intValue(),
          is(expectedFirstValue));
      assertThat(combMap.get(expectedSecondKey).intValue(),
          is(expectedSecondValue));
  }

  /**
   * Test method for {@link CashDispenserService#prepareCombination(int)}
   * Exception scenario.
   * @throws CashDispenseException
   *           CashDispenseException
   */
  @Test(expected = CashDispenseException.class)
  public final void testPrepareCombinationException()
      throws CashDispenseException {
    final DenominationMap denoMap = new DenominationMap();
    denoMap.add(50, 4);
    denoMap.add(20, 6);
    Whitebox.setInternalState(service, DenominationMap.class, denoMap);

    service.prepareCombination(amount2);
  }

}
