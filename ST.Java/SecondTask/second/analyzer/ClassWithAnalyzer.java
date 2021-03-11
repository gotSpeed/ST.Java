package second.analyzer;


import second.anotations.Transaction;

import java.lang.reflect.Method;
import java.util.Random;



public class ClassWithAnalyzer {

    public boolean analyzeSelf(String methodName) {

        Method[] declaredMethods = getClass().getDeclaredMethods();
        Method targetMethod = null;

        for (Method m : declaredMethods) {
            if (m.getName().equals(methodName)) {
                targetMethod = m;
            }
        }

        if (targetMethod != null) {
            return targetMethod.isAnnotationPresent(Transaction.class);
        }
        else {
            return false;
        }
    }



    public static void startTransaction(boolean isAnnotated) {

        if (isAnnotated) {
            System.out.println("Transaction has started");
        }
    }



    public static void endTransaction(boolean isAnnotated) {

        if (isAnnotated) {
            System.out.println("Transaction has ended");
        }
    }



    public void printRandomInteger() {

        boolean isAnnotated =
            analyzeSelf("printRandomInteger");
        startTransaction(isAnnotated);

        Random rand = new Random();
        System.out.println(rand.nextInt(10000));

        endTransaction(isAnnotated);
    }



    @Transaction
    public void concatStringAndPrint(String src, String... args) {

        boolean isAnnotated = analyzeSelf("concatStringAndPrint");
        startTransaction(isAnnotated);

        StringBuilder builder = new StringBuilder(src);
        for (String arg : args) {
            builder.append(arg);
        }

        System.out.println(builder.toString());

        endTransaction(isAnnotated);
    }

}
