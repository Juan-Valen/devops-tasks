package localization_shop.modelTest;

import localization_shop.model.Language;
import localization_shop.service.DatabaseService;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class LanguageTest {
    private DatabaseService mockDB;
    private Language language;

    @BeforeEach
    void setUp() {
        // Mock the singleton DatabaseService
        mockDB = Mockito.mock(DatabaseService.class);

        // Override the singleton instance using reflection
        try {
            var instanceField = DatabaseService.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, mockDB);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Mock translations
        when(mockDB.getTranslations("en")).thenReturn(
                Map.of("hello", "Hello", "bye", "Goodbye"));

        when(mockDB.getTranslations("fi")).thenReturn(
                Map.of("hello", "Hei"));

        language = new Language();
    }

    @Test
    void testGetStringFromDefaultLanguage() {
        assertEquals("Hello", language.getString("hello"));
    }

    @Test
    void testFallbackToEnglish() {
        language.setLanguage("fi"); // Finnish only has "hello"
        assertEquals("Goodbye", language.getString("bye")); // fallback to English
    }

    @Test
    void testSwitchLanguage() {
        language.setLanguage("fi");
        assertEquals("Hei", language.getString("hello"));
    }

    @Test
    void testUnknownKeyReturnsEnglishFallback() {
        assertEquals("Goodbye", language.getString("bye"));
    }

    @Test
    void testLanguageIsLoadedOnlyOnce() {
        language.setLanguage("fi");
        language.setLanguage("fi");

        // getTranslations("fi") should be called only once
        verify(mockDB, times(1)).getTranslations("fi");
    }
}
