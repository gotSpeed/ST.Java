package thirteenth.main;


import thirteenth.manager.DbManager;
import thirteenth.struct.ExpensesQueryStruct;

import java.util.List;



public class TaskMain {

    public static void main(String[] args) {

        DbManager manager = new DbManager();

        int ret = manager.establishConnection();
        if (ret != 0) {
            System.out.println(ret);
        }

        List<ExpensesQueryStruct> rows = manager.getExpenses(
            new String[] {
                "payments",
                "payments2"
            },
            10000
        );

        for (ExpensesQueryStruct struct : rows) {

            System.out.println(
                String.format("\nId: %d\nReceiver: %s\nDate: %s\nAmount: %d\n",
                              struct.id,
                              struct.receiver,
                              struct.timestamp,
                              struct.amount)
            );
        }
    }

}
