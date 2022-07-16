package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName(", | : 를 구분자로 사용하여 문자열을 분리한다.")
    void separate() {
        String[] split = calculator.split("1,2:3");

        assertThat(split.length).isEqualTo(3);
    }

    @Test
    @DisplayName("커스텀 구분자를 사용하여 문자열을 분리한다.")
    void separateWithCustom() {
        String[] split = calculator.split("//,.\n1,.2,.3");

        assertThat(split.length).isEqualTo(3);
    }

    @Test
    @DisplayName("문자열에 있는 숫자들의 합을 반환한다.")
    void add() {
        int result1 = calculator.calculate("//,.\n1,.2,.3");
        int result2 = calculator.calculate("523,23:294");

        assertThat(result1).isEqualTo(6);
        assertThat(result2).isEqualTo(840);
    }

    @Test
    @DisplayName("문자열에 아무것도 입력되는 값이 없을 경우 0을 반환한다.")
    void emptyString() {
        int result1 = calculator.calculate("");
        int result2 = calculator.calculate(null);

        assertThat(result1).isEqualTo(0);
        assertThat(result2).isEqualTo(0);
    }


    @Test
    @DisplayName("숫자 하나만 주어지면 해당 숫자를 반환한다.")
    void singleString() {
        int result = calculator.calculate("1");
        assertThat(result).isEqualTo(1);
    }


    @Test
    @DisplayName("문자열에 음수가 있을 경우 Runtime Exception 던진다.")
    void runtimeException() {
        assertThatThrownBy(() -> calculator.calculate("-1,2,4"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("문자열에 숫자가 아닌 값이 있을 경우 Runtime Exception 던진다.")
    void isNotNumber() {
        assertThatThrownBy(() -> calculator.calculate("a,2,4"))
                .isInstanceOf(NumberFormatException.class);
    }

}
