package br.com.estudarte.api.domain.aluno;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    public void naoDeveCadastrarAlunoComCpfErrado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aluno("Momo", "12345678900", "", "", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aluno("Momo", "123.456789-00", "", "", null));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aluno("Momo", "", "", "", null));
    }

}