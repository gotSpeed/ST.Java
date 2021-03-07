package third.main;


import third.NameMark;
import third.manager.MarksManager;

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

        List<NameMark> list = MarksManager.randomizeMarks(Arrays.asList(names));

        System.out.println(list);

        MarksManager.removeLowMarks(list);

        System.out.println(list);

    }

}
