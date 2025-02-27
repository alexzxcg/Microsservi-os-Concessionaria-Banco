package com.microsservice.concessionaria.domain.carro;

import com.microsservice.concessionaria.domain.veiculo.Categoria;
import com.microsservice.concessionaria.domain.veiculo.Veiculo;
import com.microsservice.concessionaria.domain.veiculo.VeiculoFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CarroFactory extends VeiculoFactory {

    private String motor;

    // Mapeamento de motores para categorias
    private static final Map<Set<String>, Categoria> categoriaMap = Map.of(
            Set.of("1.0", "1.4", "1.6", "2.0"), Categoria.CARRO_DE_PASSEIO,
            Set.of("V6", "V8", "V10", "V12", "V16"), Categoria.SPORT,
            Set.of("ELETRICO"), Categoria.ELETRICO
    );

    public CarroFactory(String marca, String modelo, Integer ano, BigDecimal preco, String motor) {
        super(marca, modelo, ano, preco); // Passa os parâmetros comuns para a classe base
        this.motor = motor;
    }

    @Override
    public Veiculo criarVeiculo() {
        Categoria categoria = definirCategoriaDoVeiculo();
        return new Carro(marca, modelo, ano, preco, motor, categoria);
    }

    @Override
    protected Categoria definirCategoriaDoVeiculo() {
        String motorNormalizado = motor.trim().toUpperCase();

        return categoriaMap.entrySet().stream()
                .filter(c -> c.getKey().contains(motorNormalizado))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Motor do carro não é compatível com nenhuma categoria: " + motor));
    }
}
