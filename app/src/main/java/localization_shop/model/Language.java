package localization_shop.model;

import localization_shop.service.DatabaseService;
import java.util.*;

public class Language {
    private DatabaseService DBSI = DatabaseService.getInstance();

    private String currentLanguage = "en";
    private Map<String, Map<String, String>> languages = new HashMap<>();

    public Language() {
        languages.put(currentLanguage, DBSI.getTranslations(currentLanguage));
    }

    public void setLanguage(String languageCode) {
        currentLanguage = languageCode;
        if (!languages.containsKey(languageCode)) {
            languages.put(languageCode, DBSI.getTranslations(languageCode));
        }
    }

    public void setLanguage(String languageCode, Map<String, String> translations) {
        currentLanguage = languageCode;
        if (!languages.containsKey(languageCode)) {
            languages.put(languageCode, translations);
        }
    }

    public String getString(String key) {
        Map<String, String> translations = languages.get(currentLanguage);
        if (translations.containsKey(key))
            return translations.get(key);
        return languages.get("en").get(key);
    }
}
