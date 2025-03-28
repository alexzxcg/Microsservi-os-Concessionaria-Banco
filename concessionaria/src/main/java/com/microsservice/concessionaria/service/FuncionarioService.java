package com.microsservice.concessionaria.service;

import com.microsservice.concessionaria.domain.funcionario.Funcionario;
import com.microsservice.concessionaria.domain.funcionario.FuncionarioDTO;
import com.microsservice.concessionaria.domain.funcionario.FuncionarioDetalhadoDTO;
import com.microsservice.concessionaria.exception.funcionario.FuncionarioException;
import com.microsservice.concessionaria.exception.funcionario.FuncionarioNaoEncontradoException;
import com.microsservice.concessionaria.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public FuncionarioDetalhadoDTO criarFuncionario(@Valid FuncionarioDTO dto) {
        // Converte o DTO em Funcionario, usando o construtor da classe Funcionario
        Funcionario funcionario = new Funcionario(dto);

        // Salva e transforma o Funcionario em FuncionarioDetalhadoDTO usando lambda
        return Optional.of(funcionarioRepository.save(funcionario))
                .map(FuncionarioDetalhadoDTO::new)
                .orElseThrow(() -> new FuncionarioException("Erro ao criar Funcionario")); // Lança exceção se não for encontrado
    }

    public List<FuncionarioDetalhadoDTO> listarFuncionarios() {
        return funcionarioRepository.findAll().stream()
                .map(FuncionarioDetalhadoDTO::new)
                .collect(Collectors.toList());
    }

    public FuncionarioDetalhadoDTO buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(FuncionarioDetalhadoDTO::new)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(id));  // Lança exceção se não encontrado
    }
}