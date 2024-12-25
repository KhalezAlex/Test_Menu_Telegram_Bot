package org.klozevitz.services.interfaces;

import org.klozevitz.model.EmailParameters;

public interface EmailSender {
    void send(EmailParameters parameters);
}
