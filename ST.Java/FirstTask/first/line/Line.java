package first.line;


import first.point.Point;

import java.util.*;



public class Line {

    private Point mStartPoint;
    private Point mEndPoint;

    private double mLength = -1;


    private Line(List<Double> firstPointCoords, List<Double> secondPointCoords) {

        super();
        mStartPoint = new Point(firstPointCoords);
        mEndPoint   = new Point(secondPointCoords);
    }


    private double getLength() {

        if (mLength < 0) {
            Double[] startPointVector = mStartPoint.getVectorAsArray();
            Double[] endPointVector = mEndPoint.getVectorAsArray();

            double tmpSum = 0.0;

            // Both vectors' sizes are supposed to be equal.
            for (int i = 0; i < startPointVector.length; i++) {

                tmpSum += Math.pow(endPointVector[i] - startPointVector[i], 2.0);
            }

            mLength = Math.sqrt(tmpSum);
        }

        return mLength;
    }


    @Override
    public String toString() {

        return String.format("Start point vector: S(%s)\n", mStartPoint.toString()) +
               String.format("End point vector: E(%s)\n", mEndPoint.toString()) +
               String.format("Length: %f\n", getLength());
    }


    // region line builder
    public static class Builder {

        private List<Double> mStartPointCoords;
        private List<Double> mEndPointCoords;
        private List<Double> mEditablePoint;


        public Builder createStartPoint() {

            if (mStartPointCoords == null) {
                mStartPointCoords = new ArrayList<>();
            }
            mEditablePoint = mStartPointCoords;
            return this;
        }


        public Builder createEndPoint() {

            if (mEndPointCoords == null) {
                mEndPointCoords = new ArrayList<>();
            }
            mEditablePoint = mEndPointCoords;
            return this;
        }


        public Builder addCoord(Double coord) {

            if (mEditablePoint != null) {
                mEditablePoint.add(coord);
            }
            return this;
        }


        public Builder setVector(Double... vector) {

            if (mEditablePoint != null) {
                Collections.addAll(mEditablePoint, vector);
            }
            return this;
        }


        public Builder setVector(List<Double> vector) {

            if (mEditablePoint != null) {
                mEditablePoint.addAll(vector);
            }
            return this;
        }


        public Line build() {

            equalizeDimensions();

            Line line = new Line(mStartPointCoords, mEndPointCoords);

            mStartPointCoords =
            mEndPointCoords   =
            mEditablePoint    = null;

            return line;
        }


        /*
         * If dimensions of points are unequal, fills the
         * less sized with 0's.
         */
        private void equalizeDimensions() {

            int diff = mStartPointCoords.size() - mEndPointCoords.size();

            Double[] appendix = new Double[Math.abs(diff)];
            Arrays.fill(appendix, 0.0);

            if (diff < 0) {
                mStartPointCoords.addAll(
                    Arrays.asList(appendix)
                );
            }
            else if (diff > 0) {
                mEndPointCoords.addAll(
                    Arrays.asList(appendix)
                );
            }
        }

    }
    // endregion

}
