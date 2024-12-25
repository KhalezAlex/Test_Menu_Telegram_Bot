package org.klozevitz.services.interfaces;

import org.klozevitz.dto.MailParameters;

public interface EmailSender {
    void send(MailParameters mailParameters);
}
