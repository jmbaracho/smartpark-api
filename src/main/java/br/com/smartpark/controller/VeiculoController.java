package br.com.smartpark.controller;

import br.com.smartpark.model.Veiculo;
import br.com.smartpark.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
@EnableSwagger2
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping("/checkin")
    public Veiculo checkin(@RequestBody Veiculo veiculo) throws ParseException {
        return veiculoService.checkinVeiculo(veiculo);
    }

    @PostMapping("/checkout")
    public Veiculo checkout(@RequestBody Veiculo veiculo) throws ParseException {
        return veiculoService.checkoutVeiculo(veiculo);
    }



}
