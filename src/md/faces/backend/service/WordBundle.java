package md.faces.backend.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WordBundle {

    private final ResourceBundle rb;

    public WordBundle(String lang) {
        Locale locale = Locale.of("eng");
        if (lang.equals("de")) {
            locale = Locale.of("de");
        }

        this.rb = ResourceBundle.getBundle("words", locale);
    }

    public String get(String key) {
        String str;
        try {
            str = rb.getString(key.toLowerCase());
        } catch (MissingResourceException | ClassCastException e) {
            str = key;
        } catch (Exception e) {
            str = "<<empty>>";
        }
        return str;
    }
}
