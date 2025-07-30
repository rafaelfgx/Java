package com.company.architecture.shared.services;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private static final ResourceBundleMessageSource source = new ResourceBundleMessageSource();

    public MessageService() {
        source.setBasename("messages");
    }

    public static String get(final String message, String... args) {
        return source.getMessage(message, args, message, LocaleContextHolder.getLocale());
    }
}
