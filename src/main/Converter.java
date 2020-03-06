
/**
 * The type Converter.
 */
public final class Converter {

  private boolean commaInserted = true;

  /**
   * The enum Word format.
   */
  public enum WordFormat {
    /**
     * Sentence case word format.
     */
    SENTENCE_CASE,
    /**
     * Title case word format.
     */
    TITLE_CASE,
  }

  /**
   * Instantiates a new Converter.
   */
  public Converter() {
    this(true);
  }

  /**
   * Instantiates a new Converter.
   *
   * @param commaInserted - boolean flag to indicate if comma should be inserted between words
   */
  public Converter(boolean commaInserted) {
    this.setCommaInserted(commaInserted);
  }

  /**
   * Return the boolean flag that indicates if comma should be inserted between words.
   *
   * @return the boolean
   */
  public boolean isCommaInserted() {
    return commaInserted;
  }

  /**
   * Sets the boolean flag that indicates if comma inserted between words.
   *
   * @param commaInserted - the comma inserted
   */
  public void setCommaInserted(boolean commaInserted) {
    this.commaInserted = commaInserted;
  }

  /**
   * A convenient method to format word(s) according the specified WordFormat.
   *
   * @param word -  the word
   * @param format - the format
   * @return the string
   */
  private String formatWord(String word, WordFormat format) {
    if (word.trim() == "")
      return word;
    else {

      String firstChar = word.substring(0, 1).toUpperCase();

      switch (format) {
        case SENTENCE_CASE:
          return firstChar + word.substring(1).toLowerCase();
        default:
          return firstChar + word.substring(1);
      }
    }
  }

  /**
   * Convert a number to words.
   *
   * @param number - the number to convert
   * @return the result of the conversion in word(s)
   */
  public Word numberToWord(double number) {
    return this.numberToWord(number+"", WordFormat.SENTENCE_CASE);
  }

  /**
   * Convert a number to words.
   *
   * @param number - the number to convert
   * @param format - the specified case format
   * @return the result of the conversion in word(s)
   */
  public Word numberToWord(double number, WordFormat format) {
    return this.numberToWord(number+"", format);
  }

  /**
   * Convert a number to words.
   *
   * @param number - the number to convert
   * @return the result of the conversion in word(s)
   */
  public Word numberToWord(String number) {
    return this.numberToWord(number, WordFormat.SENTENCE_CASE);
  }


  /**
   * Convert a number to words.
   *
   * @param number - the number to convert
   * @param format - the specified case format
   * @return the result of the conversion in word(s)
   */
  public Word numberToWord(String number, WordFormat format) {

    String[] conversion = new NumberToWordConverter()
            .convert(this.validateNumber(number), this.isCommaInserted());

    // Apply the specified of default WordFormat to the result(s) of the conversion
    return new Converter.Word(this.formatWord(conversion[0], format), this.formatWord(conversion[1], format));
  }


  private String validateNumber(String number) {
    // TODO: add regex to validate number

    return number.replaceAll("[,]", "");
  }


  /**
   * A POGO that returns the getters that return the conversion in word(s).
   */
  public static class Word {

    private String mantissa;
    private String characteristics;

    /**
     * Instantiates a new Word.
     *
     * @param characteristics - the characteristics or decimal part of the conversion, if any
     * @param mantissa - the mantissa or fractional part of the conversion, if any
     */
    public Word(String characteristics, String mantissa) {
      this.characteristics = characteristics;
      this.mantissa = mantissa;
    }

    /**
     * Gets mantissa or fractional part of the conversion in words.
     *
     * @return the mantissa conversion in words
     */
    public String getMantissaInWords() {
      return this.mantissa;
    }

    /**
     * Gets characteristics or decimal part of the in words.
     *
     * @return the characteristics conversion in words
     */
    public String getCharacteristicsInWords() {
      return this.characteristics;
    }
  }
}
