package eighth.manager;



import eighth.interfaces.Measurer;

import java.time.LocalTime;



public class StringManager {

    public static final int    DEFAULT_COUNT  = 1024;
    public static final String DEFAULT_STRING = "Hello, world!";



    /** @noinspection StringConcatenationInLoop */
    public static String concat(String repeatableStr, int count) {

        count = Math.max(0, count);
        String dest = repeatableStr;

        for (int i = 0; i < count; i++) {
            dest += repeatableStr;
        }

        return dest;
    }



    /** @noinspection StringRepeatCanBeUsed */
    public static String concatWithStringBuilder(String repeatableStr, int count) {

        count = Math.max(0, count);
        StringBuilder builder = new StringBuilder(repeatableStr);

        for (int i = 0; i < count; i++) {
            builder.append(repeatableStr);
        }

        return builder.toString();
    }



    public static <T, CT> void measureExecutionTime(Measurer<T, CT> func,
                                                    T param1,
                                                    CT count) {

        long startTime = System.nanoTime();

        func.repeater(param1, count);

        String execTime = LocalTime.ofNanoOfDay(
            System.nanoTime() - startTime
        ).toString();

        System.out.println(
            String.format("\n\nExecution time: %s", execTime)
        );
    }

}
