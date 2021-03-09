package fifteenth.main;


import fifteenth.manager.DbManagerExt2;
import fourteenth.manager.DbManagerExt;



public class TaskMain {

    public static void main(String[] args) {

        DbManagerExt manager = new DbManagerExt2();

        int ret = manager.establishConnection();
        if (ret != 0) {
            System.out.println(ret);
        }

        manager.parseArgsAndExecute(args);
        manager.selectAllExpensesAndPrint(manager.getTable());
    }

}
