package org.klozevitz.controllers;

import lombok.RequiredArgsConstructor;
import org.klozevitz.model.EmailParameters;
import org.klozevitz.services.interfaces.EmailSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final EmailSender emailSender;

    @PostMapping("/send")
    public ResponseEntity<?> sendActivationEmail(@RequestBody EmailParameters parameters) {
        emailSender.send(parameters);
        return ResponseEntity.ok().build();
    }
}
