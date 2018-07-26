package br.com.ez.finances.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Message repository.
 */
@Component
public class ResourceBundle {

    private static MessageSource messageSource;

    @Autowired
    private ResourceBundle(MessageSource messageSource) {
        ResourceBundle.messageSource = messageSource;
    }

    /**
     * Gets a message from a bundle given yours key. If the message has arguments to be interpolated, pass them
     * via args parameter.
     *
     * @param key  message key.
     * @param args interpolation value (optional).
     */
    public static String getMessage(String key, String... args) {
        if (messageSource != null) return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());

        return null;
    }

}