package eighteenth.main;


import eighteenth.parser.PointsParser;
import eighteenth.point.Point;

import java.util.List;



public class TaskMain {

    private static final String PATH_PARAM_KEY = "-p";
    private static final String DEFAULT_PATH   = "EighteenthTask\\source.xml";



    public static void main(String[] args) {

        PointsParser parser = new PointsParser();
        List<Point> points;

        if (args.length == 2 && args[0].equals(PATH_PARAM_KEY)) {
            points = parser.parsePointsFromFile(args[1]);
        }
        else {
            points = parser.parsePointsFromFile(DEFAULT_PATH);
        }

        for (Point p : points) {
            System.out.println(p);
        }
    }

}
