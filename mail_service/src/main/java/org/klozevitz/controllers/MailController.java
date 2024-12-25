package org.klozevitz.controllers;

import lombok.RequiredArgsConstructor;
import org.klozevitz.dto.MailParameters;
import org.klozevitz.services.interfaces.EmailSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final EmailSender mailSender;

    @PostMapping("/send")
    public ResponseEntity<?> sendActivationEmail(@RequestBody MailParameters parameters) {
        mailSender.send(parameters);
        return ResponseEntity.ok().build();
    }
}
