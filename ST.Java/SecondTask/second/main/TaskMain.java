package second.main;


import second.analyzer.ClassWithAnalyzer;



public class TaskMain {

    public static void main(String[] args) {

        ClassWithAnalyzer obj = new ClassWithAnalyzer();
        obj.concatStringAndPrint("Start", ", mid", ", end");
        obj.printRandomInteger();
    }

}
