package com.dogtiger.challus.controller;

import com.dogtiger.challus.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;
    @GetMapping("/api/letters")
    public ResponseEntity<?> getLetters() {
        return ResponseEntity.ok(letterService.getLetters());
    }
}
