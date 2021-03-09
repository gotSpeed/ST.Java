package twelfth.main;


import twelfth.greeter.Greeter;



public class TaskMain {

    public static void main(String[] args) {

        Greeter greeter = new Greeter(args);
        greeter.greetUser();
    }

}
