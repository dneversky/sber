package dev.dneversky.sber.validator.impl;

import dev.dneversky.sber.validator.TextValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BracketTextValidator.class)
class BracketTextValidatorTest {

    @Autowired
    private TextValidator validator;

    @Test
    void checkBrackets_should_return_true_when_all_is_fine() {
        String text1 = "Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями.";
        String text2 = "Вчера я отправился в поход в лес (это мое (любимое) место для отдыха вместе с друзьями).";
        assertThat(validator.validate(text1)).isTrue();
        assertThat(validator.validate(text2)).isTrue();
    }

    @Test
    void checkBrackets_should_return_false_when_bracket_pair_is_missing() {
        String text1 = "Вчера я отправился в поход в лес (это мое любимое место для отдыха вместе с друзьями.";
        String text2 = "Вчера я отправился в поход в лес это мое любимое место для отдыха вместе с друзьями).";
        assertThat(validator.validate(text1)).isFalse();
        assertThat(validator.validate(text2)).isFalse();
    }

    @Test
    void checkBrackets_should_return_false_when_brackets_contain_empty_text() {
        String text1 = "Вчера я отправился в поход в лес ().";
        String text2 = "Вчера я отправился в поход в лес (это мое () место для отдыха вместе с друзьями).";
        assertThat(validator.validate(text1)).isFalse();
        assertThat(validator.validate(text2)).isFalse();
    }

    @Test
    void checkBrackets_should_return_false_for_other_scenarios() {
        assertThat(validator.validate(")(")).isFalse();
        assertThat(validator.validate("(")).isFalse();
        assertThat(validator.validate(")")).isFalse();
        assertThat(validator.validate("")).isFalse();
    }
}