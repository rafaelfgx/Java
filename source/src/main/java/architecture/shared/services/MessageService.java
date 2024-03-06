package architecture.shared.services;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public String get(final String message, String... args) {
        var source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source.getMessage(message, args, message, LocaleContextHolder.getLocale());
    }
}
