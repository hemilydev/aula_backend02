package com.example.aula_backend02.mapper;

import com.example.aula_backend02.dto.PessoaDto;
import com.example.aula_backend02.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaDto toDto(Pessoa p);
    Pessoa toModel(PessoaDto p);
}