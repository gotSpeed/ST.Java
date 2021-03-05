package first.main;


import first.line.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class TaskMain {

    public static void main(String[] args) {

        // Example #1
        Line.Builder builder = new Line.Builder();
        Line line = builder.createStartPoint()
                           .setVector(3.0, 4.0)
                           .createEndPoint()
                           .setVector(-2.0, -1.0)
                           .build();

        System.out.println(line.toString());


        // Example #2
        line = builder.createEndPoint()
                      .addCoord(4.0)
                      .addCoord(-3.8)
                      .createStartPoint()
                      .addCoord(4.65)
                      .build();

        System.out.println(line.toString());


        // Example #3
        List<Double> example = new ArrayList<>(3);
        example.addAll(
            Arrays.asList(3.4, 5.7, 0.7)
        );

        line = builder.createEndPoint()
                      .setVector(example)
                      .createStartPoint()
                      .setVector(15.3, 0.2)
                      .build();

        System.out.println(line.toString());
    }

}
