package calculator;

public class Calculator {

    private static final String COMMA = ",";
    private static final String COLON = ":";
    private static final String NEW_LINE = "\n";
    private static final String SLASH = "//";

    public int calculate(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

        String[] splitStr = split(str);
        return calculateResult(splitStr);
    }



    public String[] split(String str) {

//            Matcher m = Pattern.compile("//(.)\n(.*)").matcher(str);
//            if (m.find()) {
//                String customDelimiter = m.group(1);
//                return m.group(2).split(customDelimiter);
//            }

        if (str.startsWith(SLASH)) {
            String numbers = str.substring(str.indexOf(NEW_LINE) + 1);
            String delimiter = str.substring(2, str.indexOf(NEW_LINE));
            return numbers.split(delimiter);
        }

        return str.split(COLON +"|" + COMMA);

    }

    private int calculateResult(String[] splitStr) {
        int result = 0;
        for (String s : splitStr) {
            result = add(result, Integer.parseInt(s.trim()));
        }
        return result;
    }

    private int add(int i, int j) {
        if (i < 0 || j < 0) {
            throw new RuntimeException("음수는 입력할 수 없습니다.");
        }
        return i + j;
    }

}
