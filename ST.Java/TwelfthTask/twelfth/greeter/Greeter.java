package twelfth.greeter;



import twelfth.options.Language;
import twelfth.options.Template;

import java.util.Arrays;
import java.util.List;



public class Greeter {

    private static final String LANGUAGE_OPT_NAME = "-lang";
    private static final String COUNTRY_OPT_NAME  = "-co";
    private static final String DEFAULT_COUNTRY   = "World";
    private static final String DEFAULT_LANGUAGE  = "EN";

    private String   mCountry;
    private Template mTemplate;



    public Greeter() {}



    public Greeter(String[] args) {

        setOptions(args);
    }



    public void setOptions(String[] args) {

        List<String> opts = Arrays.asList(args);

        int langOptIndex = opts.indexOf(LANGUAGE_OPT_NAME);
        Language lang = resolveLang(
            langOptIndex > -1 ?
            opts.get(langOptIndex + 1) :
            DEFAULT_LANGUAGE
        );

        mTemplate = resolveTemplate(lang);

        int countryOptIndex = opts.indexOf(COUNTRY_OPT_NAME);
        mCountry = countryOptIndex > -1 ?
                   opts.get(countryOptIndex + 1) :
                   DEFAULT_COUNTRY;
        if (mCountry.startsWith("-")) {
            mCountry = DEFAULT_COUNTRY;
        }
    }



    public void greetUser() {

        System.out.print(
            String.format(mTemplate.getTemplate(), mCountry)
        );
    }



    private Language resolveLang(String value) {

        switch (value.toUpperCase()) {
            case "RU":
                return Language.RU;
            case "BEL":
                return Language.BEL;
            default:
                return Language.EN;
        }
    }



    private Template resolveTemplate(Language value) {

        switch (value) {
            case RU:
                return Template.RU;
            case BEL:
                return Template.BEL;
            default:
                return Template.EN;
        }
    }

}
