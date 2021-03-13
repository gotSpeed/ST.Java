package twentieth.main;


import twentieth.gardener.Gardener;
import twentieth.park.Park;



public class TaskMain {

    private static final String DEFAULT_SRC_FILE = "source.xml";
    private static final String FILEPATH_OPT     = "-p";



    public static void main(String[] args) {

        Park park;
        if (args.length > 0 && args[0].equals(FILEPATH_OPT)) {
            park = Gardener.fillNewPark(args[1]);
        }
        else {
            park = Gardener.fillNewPark(DEFAULT_SRC_FILE);
        }

        Gardener.saveParkInfo(park);
    }

}
