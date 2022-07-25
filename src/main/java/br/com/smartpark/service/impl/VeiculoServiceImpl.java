package br.com.smartpark.service.impl;

import br.com.smartpark.model.StatusVeiculo;
import br.com.smartpark.model.Veiculo;
import br.com.smartpark.repository.VeiculoRepository;
import br.com.smartpark.service.VeiculoService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public Veiculo checkinVeiculo(Veiculo veiculo) {
        if (vagasDisponiveis() > 0) {
            LocalTime horaChekin = LocalTime.now();
            LocalDate dataChekin = LocalDate.now();

            validarDadosVeiculo(veiculo);
            validarPlaca(veiculo.getPlaca());
            validarStatusVeiculo(veiculo);

            veiculo.setHoraEntrada(horaChekin);
            veiculo.setDataEntrada(dataChekin);
            veiculo.setStatusVeiculo(StatusVeiculo.ESTACIONADO);

            try {
                veiculoRepository.save(veiculo);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao gravar registro do veículo.");
            }
            return veiculo;
        } else {
            throw new RuntimeException("Não há mais vagas disponiveis.");
        }

    }

    @Override
    public Veiculo checkoutVeiculo(Veiculo veiculo) {

        LocalTime horaCheckout = LocalTime.now();
        LocalDate dataCheckout = LocalDate.now();

        List<Veiculo> veiculosEstacionados = veiculoRepository.findByPlaca(veiculo.getPlaca());
        Veiculo result = veiculosEstacionados
                .stream()
                .filter(el -> el.getStatusVeiculo().equals(StatusVeiculo.ESTACIONADO))
                .findAny()
                .orElse(null);

        if (result == null) {
            throw new RuntimeException("Veículo não está estacionado.");
        }

        result.setHoraSaida(horaCheckout);
        result.setDataSaida(dataCheckout);
        result.setStatusVeiculo(StatusVeiculo.RETIRADO);

        try {
            veiculoRepository.save(result);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar registro do veículo.");
        }

        return result;

    }

    @Override
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @Override
    public List<Veiculo> findAllEstacionados() {
        List<Veiculo> veiculosEstacionados = veiculoRepository.findAll();
        return veiculosEstacionados
                .stream()
                .filter(el -> el.getStatusVeiculo().equals(StatusVeiculo.ESTACIONADO))
                .collect(Collectors.toList());
    }

    @Override
    public String vagasDisponiveisJson() {
        long vagas = vagasDisponiveis();
        JSONObject vagasToJson = new JSONObject();
        vagasToJson.put("vagasDisponiveis", vagas);
        String json_string = vagasToJson.toString();

        return json_string;
    }

    public long vagasDisponiveis() {
        long vagasTotal = 7;
        List<Veiculo> veiculosEstacionados = veiculoRepository.findAll();
        long result = veiculosEstacionados
                .stream()
                .filter(el -> el.getStatusVeiculo().equals(StatusVeiculo.ESTACIONADO))
                .count();
        return vagasTotal - result;
    }

    public void validarDadosVeiculo(Veiculo veiculo) {
        if (veiculo.getPlaca().equals("")
                || veiculo.getCor().equals("")
                || veiculo.getMarca().equals("")
                || veiculo.getModelo().equals("")) {
            throw new RuntimeException("Informar placa, marca, cor e modelo do veículo.");
        }
    }

    public void validarPlaca(String placa) {
        if (placa.length() != 7) {
            throw new RuntimeException("Verificar placa informada. Quantitdade incorreta.");
        }
    }

    public void validarStatusVeiculo(Veiculo veiculo) {
        List<Veiculo> veiculosEstacionados = veiculoRepository.findByPlaca(veiculo.getPlaca());
        Veiculo result = veiculosEstacionados
                .stream()
                .filter(el -> el.getStatusVeiculo().equals(StatusVeiculo.ESTACIONADO))
                .findAny()
                .orElse(null);
        if (result != null) {
            throw new RuntimeException("Veículo está estacionado.");
        }
    }
}
