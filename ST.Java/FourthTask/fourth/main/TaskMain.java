package fourth.main;


import fourth.manager.MarksManagerExt;
import third.misc.NameMark;

import java.util.Arrays;
import java.util.List;



public class TaskMain {

    public static void main(String[] args) {

        String[] names = new String[] {
            "Ivan",
            "Kondratiy",
            "Bob",
            "Rob",
            "Keith",
            "Mark",
            "Valentin",
            "Greg",
            "Grigory",
            "Hue",
            "Pat",
            "Kate",
            "Jesus",
            "Colin",
            "Turner",
            "Gary",
            "Stephanie",
            "Ratibor"
        };

        List<NameMark> list = MarksManagerExt.randomizeMarks(Arrays.asList(names));

        MarksManagerExt.removeLowMarks(list);

        System.out.println(list);
        System.out.println(MarksManagerExt.findMaxMark(list));

        list = MarksManagerExt.randomizeMarks(
            Arrays.asList(names),
            8,
            0
        );

        MarksManagerExt.removeLowMarks(list);

        System.out.println(list);
        System.out.println(MarksManagerExt.findMaxMark(list));
    }

}
