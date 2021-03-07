package fourth.manager;


import third.manager.MarksManager;
import third.misc.NameMark;

import java.util.List;
import java.util.ListIterator;



public class MarksManagerExt extends MarksManager {

    public static NameMark findMaxMark(List<NameMark> list) {

        ListIterator<NameMark> iter = list.listIterator();

        NameMark max = new NameMark("", 0);
        NameMark cur;
        while (iter.hasNext()) {
            cur = iter.next();
            if (max.getMark() < cur.getMark()) {
                max = cur;
            }
        }

        return max;
    }

}
