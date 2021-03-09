package eleventh.main;


import eleventh.converter.Converter;



public class TaskMain {

    public static void main(String[] args) {

        System.out.println(Converter.convertToRUB(Converter.EXAMPLE));
        System.out.println(Converter.convertToRUB(1451.42124));
        System.out.println(Converter.convertToRUB(-904241.41552));
        System.out.println(Converter.convertToRUB(0.745));
    }

}
