package br.com.smartpark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String marca;
    private String modelo;
    private String placa;
    private String cor;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrada;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaSaida;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSaida;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo statusVeiculo;

}
