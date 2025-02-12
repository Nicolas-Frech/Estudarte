package br.com.estudarte.api.domain.professor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    @Test
    public void naoDeveCadastrarProfessorComCnpjInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Professor("momo", "34345654/2345-90", "", "", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Professor("momo", "34.345.654/234590", "", "", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Professor("momo", "34345654234590", "", "", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Professor("momo", "", "", "", null));
    }

}