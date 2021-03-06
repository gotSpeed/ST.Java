package fourteenth.main;


import fourteenth.manager.DbManagerExt;



public class TaskMain {

    public static void main(String[] args) {

        DbManagerExt manager = new DbManagerExt();

        int ret = manager.establishConnection();
        if (ret != 0) {
            System.out.println(ret);
        }

        manager.parseArgsAndExecute(args);
        manager.selectAllExpensesAndPrint(manager.getTable());
    }

}
