import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * The type Number to word converter.
 */
public final class NumberToWordConverter {

  private final int DECIMAL_PLACE = 2;
  private final int MAX_RANGE = 18;

  private String[] th = {
          "","Thousand", "Million", "Billion", "Trillion", "Quadrillion"
  };
  private String[] dg = {
          "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
  };
  private String[] tn = {
          "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
  };
  private String[] tw = {
          "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
  };

  /**
   * A convinent method to convert the given number to array of integers.
   *
   * @param number - the number
   * @return the int [ ]
   */
  private int[] numberToArrayOfInt(String number) {
    return Arrays.stream(number.split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
  }

  /**
   * Convert a number to word.
   *
   * @param number - the number
   * @param isCommaInserted - the is comma inserted
   * @return an array containing the decimal and fractional path of the number
   */
  public String[] convert(String number, boolean isCommaInserted) {

    number = number.replaceAll("[,]", "");

    String mantissa = "";
    String characteristics = "";

    if (number.contains(".")) {
      String[] result = this.roundTo(number).split("[.]");

      characteristics = this.convertCharacteristicsToWords(result[0], isCommaInserted);
      mantissa = this.convertMantissaTowords(result[1]);
    } else {
      characteristics = this.convertCharacteristicsToWords(number, isCommaInserted);
    }

    return new String[]{ characteristics, mantissa };
  }

  private String roundTo(String number) {
    return new BigDecimal(number)
            .setScale(this.DECIMAL_PLACE, RoundingMode.HALF_UP)
            .toPlainString();
  }

  /**
   * Gets characteristics in words.
   *
   * @param characteristics - the characteristics
   * @param isCommaInserted - the is comma inserted
   * @return the characteristics in words
   */
  private String convertCharacteristicsToWords(String characteristics, boolean isCommaInserted) {

    String word = "";
    final int[] characteristicsArray = this.numberToArrayOfInt(characteristics);
    final int length = characteristicsArray.length;

    if (length > this.MAX_RANGE)
      throw new IllegalArgumentException("Number is to large");
    else {

      boolean skip = false;

      for (int i = 0; i < length; i++) {
        if ((length - i) % 3 == 2) {
          if (characteristicsArray[i] == 1) {
            word += this.tn[characteristicsArray[++i]] + " ";
            skip = false;
          } else if (characteristicsArray[i] != 0) {
            word += this.tw[characteristicsArray[i] - 2] + (characteristicsArray[i + 1] > 0 ? "-" : " ");
            skip = false;
          }
        } else if (characteristicsArray[i] != 0) {
          word += this.dg[characteristicsArray[i]].toLowerCase() + " ";
          if ((length - i) % 3 == 0) {
            word += "Hundred " + (characteristicsArray[i + 1] == 0 && characteristicsArray[i + 2] == 0 ? "" : "and ");
          }

          skip = false;
        }

        if ((length - i) % 3 == 1) {
          if (!skip) {
            word += this.th[(length - i - 1) / 3];
            if ((length - i) > 3)
              word += (Integer.parseInt(characteristics.substring(i + 1)) > 0 && isCommaInserted) ? ", " : " ";

            skip = true;
          }
        }
      }
    }

    return word.trim().equals("") ? "Zero" : word.trim();
  }

  /**
   * Converts the mantissa or fractional part of the number, if any, to words.
   *
   * @param mantissa - the mantissa
   * @return the mantissa in words
   */
  private String convertMantissaTowords(String mantissa) {

    String word = "";

    final int[] mantissaArray = this.numberToArrayOfInt(mantissa);
    final int length = mantissaArray.length;

    for (int i = 0; i < length; i++) {

      if (mantissaArray[i] == 1)
        word += this.tn[mantissaArray[++i]] + " ";
      else if (mantissaArray[i] != 0)
        word += this.tw[mantissaArray[i] - 2]
                +((mantissaArray[++i] != 0 ? "-" : " ") +this.dg[mantissaArray[i]].toLowerCase());
      else
        word += this.dg[mantissaArray[i]];
    }

    return word.equals("") ? "Zero" : word.trim();
  }
}
