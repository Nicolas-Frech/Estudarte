package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorEntity registrarProfessor(ProfessorDTO dto) {
        if(professorRepository.existsByCnpj(dto.cnpj())) {
            throw new IllegalArgumentException("CNPJ já registrado!");
        } else {
            ProfessorEntity professor = new ProfessorEntity(dto);
            professorRepository.save(professor);
            return professor;
        }
    }
}
