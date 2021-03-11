package nineteenth.main;


import nineteenth.formatter.Formatter;



public class TaskMain {

    private static final String XSL_PATH = "NineteenthTask\\transform.xsl";
    private static final String XML_PATH = "NineteenthTask\\source.xml";



    public static void main(String[] args) {

        Formatter formatter = new Formatter();
        formatter.formatXmlToHtml(XSL_PATH, XML_PATH);
    }

}
