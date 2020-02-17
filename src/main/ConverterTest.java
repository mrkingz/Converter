public class ConverterTest {
  public static void main(String[] args) {
    Converter.Word word1 = new Converter().numberToWord("12345678921.63", Converter.WordFormat.TITLE_CASE);
    // Before the decimal point
    System.out.println(word1.getCharacteristicsInWords()); // Twelve Billion, three Hundred and Forty-five Million, six Hundred and Seventy-eight Thousand, nine Hundred and Twenty-one
    // After the decimal point
    System.out.println("Fractional/mantissa part: " +word1.getMantissaInWords()); // Sixty-three

    Converter.Word word2 = new Converter().numberToWord("123456"); // Converter.WordFormat.SENTENCE_CASE (default)
    // Before the decimal point
    System.out.println(word2.getCharacteristicsInWords()); // One hundred and twenty-three thousand, four hundred and fifty-six
    // After the decimal point
    System.out.println(word2.getMantissaInWords()); // null

    // Before the decimal point
    Converter.Word word3 = new Converter().numberToWord("0.98", Converter.WordFormat.TITLE_CASE);
    System.out.println(word3.getCharacteristicsInWords()); //Zero
    // After the decimal point
    System.out.println(word3.getMantissaInWords()); // Ninety-eight

    Converter.Word word4 = new Converter().numberToWord("0.0", Converter.WordFormat.TITLE_CASE);
    System.out.println(word4.getCharacteristicsInWords()); // Zero
    System.out.println(word4.getMantissaInWords()); // Zero

  }
}
