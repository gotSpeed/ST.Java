package tenth.main;


import tenth.manager.StringManagerExt2;

import java.util.List;



public class TaskMain {

    public static void main(String[] args) {

        String filepath = args.length > 0 ?
                          args[0] :
                          StringManagerExt2.DEFAULT_IN_FILE;

        List<String> formattedStrings = StringManagerExt2.readFileAndFormatTags(
            filepath,
            StringManagerExt2.PARAGRAPH_TAG_REGEX
        );

        int exitCode = StringManagerExt2.writeToFile(
            StringManagerExt2.DEFAULT_OUT_FILE,
            formattedStrings
        );

        if (exitCode != 0) {
            System.out.println(exitCode);
        }

        String src = "<p id=\"classy\">Some text here</p>";
        String res = StringManagerExt2.findTagsAndSimplify(
            src,
            StringManagerExt2.PARAGRAPH_TAG_REGEX
        );
        System.out.println(res);
    }

}
