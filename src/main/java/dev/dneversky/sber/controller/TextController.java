package dev.dneversky.sber.controller;

import dev.dneversky.sber.reqeust.TextRequest;
import dev.dneversky.sber.response.ApiResponse;
import dev.dneversky.sber.validator.TextValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class TextController {

    @Autowired
    private TextValidator textValidator;

    @PostMapping("/checkBrackets")
    public ApiResponse checkBrackets(@RequestBody TextRequest textRequest) {
        return new ApiResponse(textValidator.validate(textRequest.text()));
    }
}
