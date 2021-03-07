package seventh.main;


import seventh.manager.FileManagerExt;



public class TaskMain {

    public static void main(String[] args) {

        if (args.length == 0) {
            FileManagerExt.getDirectoryContent();
            FileManagerExt.getDirectoryContent("D:\\Themes");
        }
        else {
            FileManagerExt.getDirectoryContent(args[0]);
        }
    }

}
