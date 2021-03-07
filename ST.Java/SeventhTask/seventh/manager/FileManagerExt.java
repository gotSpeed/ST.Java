package seventh.manager;


import sixth.manager.FileManager;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public class FileManagerExt extends FileManager {

    public static void getDirectoryContent(String dirPath) {

        File file = new File(dirPath);
        String[] contentPaths = file.list();

        if (contentPaths != null) {
            List<File> content = Arrays.stream(contentPaths)
                                       .map(path -> Paths.get(dirPath, path).toString())
                                       .map(File::new)
                                       .collect(Collectors.toList());

            List<File> dirs = content.stream()
                                     .filter(File::isDirectory)
                                     .collect(Collectors.toList());

            List<File> files = content.stream()
                                      .filter(File::isFile)
                                      .collect(Collectors.toList());

            System.out.println(
                String.format("\nAbsolute path: %s", dirPath)
            );

            System.out.println("\nDirs:");
            for (File dir : dirs) {
                System.out.println(
                    String.format("\t%s",
                                  file.toURI().relativize(dir.toURI()).toString())
                );
            }

            System.out.println("\nFiles:");
            for (File file_ : files) {
                System.out.println(
                    String.format("\t%s",
                                  file.toURI().relativize(file_.toURI()).toString())
                );
            }
        }
    }



    public static void getDirectoryContent() {

        String path = Paths.get("").toAbsolutePath().toString();
        getDirectoryContent(path);
    }

}
