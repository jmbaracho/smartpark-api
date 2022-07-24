package br.com.smartpark.controller;

import br.com.smartpark.model.Veiculo;
import br.com.smartpark.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api")
@EnableSwagger2
public class RelatorioController {

    private final VeiculoService veiculoService;

    @Autowired
    public RelatorioController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping(value = "/relatorio-diario", produces = "application/json")
    public List<Veiculo> relatorioVeiculosEstacionadosNoDia() {return veiculoService.findAll(); }

    @GetMapping(value = "/relatorio-estacionados", produces = "application/json")
    public List<Veiculo> relatorioVeiculosEstacionados() {
        return veiculoService.findAllEstacionados();
    }

    @GetMapping(value = "/quantidade-vagas-disponiveis", produces = "application/json")
    public long quantidadeVagasDisponiveis() {
        return veiculoService.vagasDisponiveis();
    }

}
