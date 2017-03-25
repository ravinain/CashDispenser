package main;

import java.io.IOException;
import java.util.Scanner;

import org.dispenser.cash.controller.CashDispenserController;
import org.dispenser.cash.controller.impl.CashDispenserControllerImpl;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.utils.Constants;

/**
 * Client class.
 * @author cdacr
 */
public final class ClientMain {

  /** Don't allow to create instance as all methods static. */
  private ClientMain() {
  }

  /**
   * Entry point.
   * @param args
   *          input argument(optional)
   */
  public static void main(final String[] args) {
    String fileName = Constants.DENO_MAP_FILE;
    if (args != null && args.length > 0) {
      fileName = args[0];
    }

    final CashDispenserController controller =
        new CashDispenserControllerImpl();
    // Load denomination mapping.
    try {
      controller.loadDenominationMap(fileName);
      showInput(controller);
    } catch (IOException | DenominationException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Show Input options to user.
   * @param controller
   *          controller.
   */
  static void showInput(final CashDispenserController controller) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Options:");
    System.out.println("1. Withdraw");
    System.out.println("2. Add/Update Denomination");
    System.out.println("3. Available Cash");
    System.out.println("4. Exit");

    try {
      mainLoop: while (true) {
        System.out.print("Choose option:");
        final int input = scanner.nextInt();
        switch (input) {
        case 1:
          System.out.print("Enter Amount : ");
          controller.prepareCombination(scanner.nextInt());
          break;
        case 2:
          System.out.println(
              "Enter Denomination face value and count. "
                  + "E.g(50 (press enter) 5): ");
          controller.getDenominationMap().add(scanner.nextInt(),
              scanner.nextInt());
          System.out.println("Added successfully!");
          break;
        case 3:
          System.out.println(controller.getDenominationMap());
          break;
        case 4:
          break mainLoop;
        default:
          System.err.println("Invalid input!");
        }
      }
    } catch (final Exception ex) {
      System.err.println(ex.getMessage());
    } finally {
      if (scanner != null) {
        scanner.close();
        scanner = null;
      }
    }

  }

}
