package eleventh.converter;



public class Converter {

    public static final double EXAMPLE = 153.5;



    public static String convertToRUB(double value) {

        StringBuilder builder = new StringBuilder(16);

        if (value < 0) {
            builder.append("-");
            value = Math.abs(value);
        }

        builder.append(convertIntegerPart(value));
        builder.append(" rub. ");
        builder.append(convertFractionalPart(value));
        builder.append(" kop.");

        return builder.toString();
    }



    public static String convertFractionalPart(double value) {

        value = value - (long) value;
        return String.valueOf(Math.round(value * 100));
    }



    public static String convertIntegerPart(double value) {

        long holder = (long) value;
        StringBuilder builder = new StringBuilder(8);

        if (holder == 0) {
            return "0";
        }

        for (int i = 1;
             holder != 0;
             i++, holder /= 10) {

            builder.append(holder % 10);

            // Every non-last-three
            if (i % 3 == 0 && holder / 10 != 0) {
                builder.append(" ");
            }
        }

        return builder.reverse().toString();
    }

}
