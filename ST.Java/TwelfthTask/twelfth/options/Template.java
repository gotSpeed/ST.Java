package twelfth.options;


public enum Template {

    EN("\nGreetings to %s! Glad to see you, partner.\n"),
    RU("\nПриветствуем, %s! Чувствуйте себя как дома!\n"),
    BEL("\nВітаем, %s! Спадзяемся, што адчуваеце сябе найлепшым чынам!\n");



    Template(String template) {

        mTemplate = template;
    }



    public String getTemplate() {

        return mTemplate;
    }



    private String mTemplate;
}
