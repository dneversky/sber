package dev.dneversky.sber.validator.impl;

import dev.dneversky.sber.validator.TextValidator;
import org.springframework.stereotype.Component;

@Component
public class BracketTextValidator implements TextValidator {

    @Override
    public boolean validate(String text) {
        if (text.isBlank()) return false;
        return checkBrackets(text);
    }

    private boolean checkBrackets(String text) {
        char[] chars = text.toCharArray();
        int openingBrackets = 0, closingBrackets = 0;
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            if (current == ')' && (openingBrackets == 0 || chars[i - 1] == '(')) {
                return false;
            } else if (current == '(') {
                openingBrackets++;
            } else if (current == ')') {
                closingBrackets++;
            }
        }
        return openingBrackets == closingBrackets;
    }
}
