package ninth.main;


import ninth.manager.StringManagerExt;

import java.util.List;



public class TaskMain {

    public static void main(String[] args) {

        List<Integer> list = StringManagerExt.getHexValuesFromString(
            "Some hex value: 0xFf. And there are more: 0xaBc1, 0X(corrupted) and 0X14"
        );

        for (Integer value : list) {
            System.out.println(value);
        }

        System.out.println("\n");

        list = StringManagerExt.getHexValuesFromString(
            "0xc. asdc0xnas0XAB, 0X(corrupted) and 0X15"
        );

        for (Integer value : list) {
            System.out.println(value);
        }
    }

}
