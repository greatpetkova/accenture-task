package utils;

public class PriceHelper {
    public static float getAsFloat(String price) {
        return Float.parseFloat(price.replace("$", ""));
    }
}
