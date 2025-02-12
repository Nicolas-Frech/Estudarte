package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void registrarProfessor(ProfessorDTO dto) {
        professorRepository.save(new ProfessorEntity(dto));
    }
}
