package com.example.aula_backend02.service;

import com.example.aula_backend02.dto.PessoaDto;
import com.example.aula_backend02.mapper.PessoaMapper;
import com.example.aula_backend02.model.Pessoa;
import com.example.aula_backend02.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public void save(PessoaDto pessoaDto) {
        Pessoa entity = PessoaMapper.INSTANCE.toModel(pessoaDto);
        pessoaRepository.save(entity);
    }

    public ResponseEntity<?> getPessoa(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PessoaMapper.INSTANCE.toDto(pessoa.get()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pessoa não encontrada!");
    }

    public ResponseEntity<?> getAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaDto> dtos = pessoas.stream()
                .map(p -> PessoaMapper.INSTANCE.toDto(p))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<?> update(Long id, PessoaDto pessoaDto) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            Pessoa entity = pessoa.get();
            entity.setNome(pessoaDto.getNome());
            entity.setCpf(pessoaDto.getCpf());
            entity.setEndereco(pessoaDto.getEndereco());
            pessoaRepository.save(entity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PessoaMapper.INSTANCE.toDto(entity));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pessoa não encontrada!");
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Pessoa removida com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pessoa não encontrada!");
    }
}