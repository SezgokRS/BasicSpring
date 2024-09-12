package com.simple_form.controller;


import com.simple_form.service.TextService;
import org.springframework.stereotype.Controller;

@Controller
public class TextController {
    private TextService textService;

    //autowire
    public TextController(TextService textService) {
        this.textService = textService;
    }
}
