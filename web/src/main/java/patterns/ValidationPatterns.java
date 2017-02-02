package patterns;

/**
 * Interface includes patterns for data validation
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface ValidationPatterns {

    String NAME = "[а-яА-ЯёЁa-zA-Z]+";
    String SURNAME = "[а-яА-ЯёЁa-zA-Z]+";
    String LOGIN = "[а-яА-ЯёЁa-zA-Z0-9-_.]+";
    String PASSWORD = "[а-яА-ЯёЁa-zA-Z0-9-_.]+";
    String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}


