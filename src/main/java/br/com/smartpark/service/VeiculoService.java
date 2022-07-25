package br.com.smartpark.service;

import br.com.smartpark.model.Veiculo;

import java.text.ParseException;
import java.util.List;

public interface VeiculoService {

    Veiculo checkinVeiculo(Veiculo veiculo) throws ParseException;
    Veiculo checkoutVeiculo(Veiculo veiculo) throws ParseException;
    List<Veiculo> findAll();
    List<Veiculo> findAllEstacionados();
    String vagasDisponiveisJson();
}
