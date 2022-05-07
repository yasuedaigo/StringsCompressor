public class StringsExpander {

    public static void execution() {
        String text = decode("A5B10CD9E2F5G");
        System.out.println(text);
    }

    public static final int FIRSTINDEX = 0;

    private static String decode(String text) {
        StringBuilder buildedText = new StringBuilder(text);
        StringBuilder decodeText = new StringBuilder();
        while (!StringsCompressor.isBlank(buildedText)) {
            String targetString = getOneStringFromTop(buildedText);
            int targetInt = getIntFromTop(buildedText);
            decodeText.append(makeText(targetString, targetInt));
        }
        return decodeText.toString();
    }

    private static String makeText(String targetString, int number) {
        if (number == 0) {
            return targetString;
        }
        StringBuilder text = new StringBuilder();
        for(int i=0;i<number;i++){
            text.append(targetString);
        }
        return text.toString();
    }

    private static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getOneStringFromTop(StringBuilder sb) {
        String topString = StringsCompressor.firstString(sb);
        StringsCompressor.deleteFirstString(sb);
        return topString;
    }

    private static int getIntFromTop(StringBuilder sb) {
        if (!topIsNumber(sb)) {
            return FIRSTINDEX;
        }
        StringBuilder numberString = new StringBuilder();
        while (topIsNumber(sb)) {
            numberString.append(getOneStringFromTop(sb));
        }
        return Integer.parseInt(numberString.toString());
    }

    private static boolean topIsNumber(StringBuilder sb) {
        if (StringsCompressor.isBlank(sb)) {
            return false;
        }
        return isNumber(sb.substring(0, 1));
    }
}