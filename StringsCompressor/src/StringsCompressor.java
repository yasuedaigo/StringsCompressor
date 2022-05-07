public class StringsCompressor {
    public static void main(String[] args) {
        String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
        System.out.println(text);
        StringsExpander.execution();
    }

    public static final int FIRSTINDEX = 0;
    public static final int START_COUNT = 1;

    private static String encode(String text) {
        StringBuilder buildedText = new StringBuilder(text);
        StringBuilder encodeText = new StringBuilder();
        int sameCount = START_COUNT;
        prepareFarstString(buildedText, encodeText);
        while (!isBlank(buildedText)) {
            String topString = getStringForTop(buildedText);
            if (topString.equals(getStringForLast(encodeText))) {
                sameCount++;
                continue;
            }
            if (sameCount > START_COUNT) {
                encodeText.append(sameCount);
            }
            sameCount = START_COUNT;
            encodeText.append(topString);
        }
        return encodeText.toString();
    }

    private static String getStringForTop(StringBuilder sb) {
        String topString = firstString(sb);
        deleteFirstString(sb);
        return topString;
    }

    public static boolean isBlank(StringBuilder sb) {
        return sb.length() == 0;
    }

    private static String getStringForLast(StringBuilder sb) {
        String lastString = sb.substring(sb.length() - 1, sb.length());
        return lastString;
    }

    private static void prepareFarstString(StringBuilder buildedText, StringBuilder encodeText) {
        if (isBlank(encodeText)) {
            encodeText.append(getStringForTop(buildedText));
        }
    }

    public static String firstString(StringBuilder sb) {
        return sb.substring(0, 1);
    }

    public static void deleteFirstString(StringBuilder sb) {
        sb.delete(0, 1);
    }
}