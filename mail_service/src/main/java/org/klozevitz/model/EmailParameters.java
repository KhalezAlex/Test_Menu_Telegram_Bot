package org.klozevitz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmailParameters {
    private String id;
    private String emailTo;
}
