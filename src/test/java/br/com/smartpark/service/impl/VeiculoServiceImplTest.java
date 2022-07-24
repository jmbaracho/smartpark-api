package br.com.smartpark.service.impl;

import br.com.smartpark.model.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class VeiculoServiceImplTest {

    @Autowired
    private VeiculoServiceImpl veiculoServiceImpl;

    @Test
    void checkinVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("QYU6G33");
        veiculo.setMarca("Hyundai");
        veiculo.setModelo("Creta");
        veiculo.setCor("Branca");
        veiculoServiceImpl.checkinVeiculo(veiculo);
    }

    @Test
    void checkoutVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("QYU6G33");
        veiculo.setMarca("Hyundai");
        veiculo.setModelo("Creta");
        veiculo.setCor("Branca");
        veiculoServiceImpl.checkoutVeiculo(veiculo);
    }


    @Test
    void findAll() {
        veiculoServiceImpl.findAll();
    }

    @Test
    void findAllEstacionados() {
        veiculoServiceImpl.findAllEstacionados();
    }

    @Test
    void vagasDisponiveis() {
        veiculoServiceImpl.vagasDisponiveis();
    }

    @Test
    void validarDadosVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("QYU6G33");
        veiculo.setMarca("Hyundai");
        veiculo.setModelo("Creta");
        veiculo.setCor("Branca");
        veiculoServiceImpl.validarDadosVeiculo(veiculo);
    }

    @Test
    void validarPlaca() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("QYU6G33");
        veiculoServiceImpl.validarPlaca(veiculo.getPlaca());
    }

    @Test
    void validarStatusVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("QYU6G33");
        veiculo.setMarca("Hyundai");
        veiculo.setModelo("Creta");
        veiculo.setCor("Branca");
        veiculoServiceImpl.validarStatusVeiculo(veiculo);
    }
}