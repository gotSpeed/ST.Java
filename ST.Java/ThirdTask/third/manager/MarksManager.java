package third.manager;


import third.misc.NameMark;

import java.util.*;



public class MarksManager {

    private static final int DEFAULT_THRESHOLD    = 4;
    private static final int DEFAULT_RATING_SCALE = 10;
    private static final int DEFAULT_OFFSET       = 1;



    protected MarksManager() {}



    public static List<NameMark> randomizeMarks(List<String> names,
                                                int ratingScale,
                                                int offset) {

        if (ratingScale < 1) {
            ratingScale = DEFAULT_RATING_SCALE;
        }
        if (offset < 0 || offset >= ratingScale) {
            offset = DEFAULT_OFFSET;
        }

        Random rand = new Random();

        List<NameMark> marks = new ArrayList<>(names.size());
        ListIterator<String> namesIter = names.listIterator();
        ListIterator<NameMark> marksIter = marks.listIterator();

        while (namesIter.hasNext()) {
            marksIter.add(
                new NameMark(
                    namesIter.next(),
                    Math.abs(rand.nextInt() % ratingScale) + offset
                )
            );
        }

        return marks;
    }



    public static List<NameMark> randomizeMarks(List<String> names, int ratingScale) {

        return randomizeMarks(names, ratingScale, DEFAULT_OFFSET);
    }



    public static List<NameMark> randomizeMarks(List<String> names) {

        return randomizeMarks(names, DEFAULT_RATING_SCALE, DEFAULT_OFFSET);
    }



    public static void removeLowMarks(List<NameMark> list, int threshold) {

        if (threshold < DEFAULT_OFFSET || threshold > DEFAULT_RATING_SCALE - 1) {
            threshold = DEFAULT_THRESHOLD;
        }

        ListIterator<NameMark> iter = list.listIterator();

        NameMark tmp;
        while (iter.hasNext()) {
            tmp = iter.next();
            if (tmp.getMark() < threshold) {
                iter.remove();
            }
        }
    }



    public static void removeLowMarks(List<NameMark> list) {

        removeLowMarks(list, DEFAULT_THRESHOLD);
    }

}
