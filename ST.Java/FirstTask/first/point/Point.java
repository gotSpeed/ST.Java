package first.point;


import java.util.List;
import java.util.stream.Collectors;



public class Point {

    protected final List<Double> mVector;


    public Point(List<Double> vector) {

        mVector = vector;
    }


    public Double[] getVectorAsArray() {

        return mVector.toArray(new Double[0]);
    }


    @Override
    public String toString() {

        List<String> vectorString =
            mVector.stream().map(Object::toString).collect(Collectors.toList());
        return String.join("; ", vectorString);
    }

}
