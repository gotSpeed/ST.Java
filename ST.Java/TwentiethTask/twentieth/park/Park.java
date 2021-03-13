package twentieth.park;


import twentieth.plants.Plant;

import java.util.ArrayList;
import java.util.List;



public class Park {

    private List<Plant> mPlants;
    private int         mTotalSpaceOccupied;
    private int         mSummaryHeight;



    // region getters
    public List<Plant> getPlants() {

        return mPlants;
    }



    public int getTotalSpaceOccupied() {

        return mTotalSpaceOccupied;
    }



    public int getSummaryHeight() {

        return mSummaryHeight;
    }
    // endregion



    public void plantNew(Plant plant) {

        if (mPlants == null) {
            mPlants = new ArrayList<>();
        }

        mSummaryHeight += plant.getHeight();
        mTotalSpaceOccupied += plant.getOccupiedSpace();
        mPlants.add(plant);
    }

}
