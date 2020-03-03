### Converter
Java implementation to convert a number or figure to word(s)


**How to use it**
```
The following demonstrates the possible scenarios of how to use the an instance of Converter 
```

**With comman (default)**
```Java
Converter converter = new Converter();
Converter.Word word = converter.numberToWord("12345678921.63"); Converter.WordFormat.TITLE_CASE (Default)

word.getCharacteristicsInWords();
Result:
// Twelve Billion, three Hundred and Forty-five Million, six Hundred and Seventy-eight Thousand, nine Hundred and Twenty-one

word.word2.getMantissaInWords()
Result: 
// Sixty-three
```

**Without comman**
```Java
Converter converter = new Converter(false);
Converter.Word word = converter.numberToWord("12345678921.63", Converter.WordFormat.TITLE_CASE);

word.getCharacteristicsInWords();
Result:
// Twelve Billion three Hundred and Forty-five Million six Hundred and Seventy-eight Thousand nine Hundred and Twenty-one

word.word2.getMantissaInWords()
Result: 
// Sixty-three
```

**To get words in Sentence Case**
```Java
Converter converter = new Converter(false);
Converter.Word word = converter.numberToWord("1234567.90", Converter.WordFormat.SENTENCE_CASE);

word.getCharacteristicsInWords();
Result:
// One million, two hundred and thirty-four thousand, five hundred and sixty-seven

word.word2.getMantissaInWords()
Result: 
// Ninety
```
