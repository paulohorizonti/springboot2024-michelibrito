package be.com.api.reduct.dtos;

import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;

public record EmpresaRecordDto(String Fantasia, @NotBlank String Razao, @NotBlank String Cnpj, @NotBlank String Cidade, BigInteger MatrizId) {

}
