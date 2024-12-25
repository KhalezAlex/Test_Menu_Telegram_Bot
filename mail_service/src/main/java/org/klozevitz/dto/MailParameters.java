package org.klozevitz.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MailParameters {
    private String id;
    private String emailTo;
}
