package eighth.main;


import eighth.manager.StringManager;



public class TaskMain {

    public static void main(String[] args) {

        StringManager.measureExecutionTime(
            StringManager::concatWithStringBuilder,
            StringManager.DEFAULT_STRING,
            StringManager.DEFAULT_COUNT
        );

        StringManager.measureExecutionTime(
            StringManager::concat,
            StringManager.DEFAULT_STRING,
            StringManager.DEFAULT_COUNT
        );


        StringManager.measureExecutionTime(
            StringManager::concatWithStringBuilder,
            "StringManager.DEFAULT_STRING",
            8192
        );

        StringManager.measureExecutionTime(
            StringManager::concat,
            "StringManager.DEFAULT_STRING",
            8192
        );
    }

}
