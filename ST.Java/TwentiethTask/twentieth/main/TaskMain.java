package twentieth.main;


import twentieth.park.Park;



public class TaskMain {

    private static final String DEFAULT_IN_FILE=  "plants.xml";



    public static void main(String[] args) {

        Park park = Park.getInstance();

        park.fillPark(DEFAULT_IN_FILE);
        park.saveParkInfo();
    }

}
