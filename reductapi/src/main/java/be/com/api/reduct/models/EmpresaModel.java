package be.com.api.reduct.models;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="empresa")
public class EmpresaModel extends RepresentationModel<EmpresaModel> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private BigInteger Id;
	
	@Column(name="FANTASIA")
	private String Fantasia;
	
	@Column(name="RAZAO")
	private String Razao;
	
	@Column(name="CNPJ")
	private String Cnpj;
	
	@Column(name="CIDADE")
	private String Cidade;
	
	@Column(name="MATRIZ_ID")
	private BigInteger MatrizId;

	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger id) {
		Id = id;
	}

	public String getFantasia() {
		return Fantasia;
	}

	public void setFantasia(String fantasia) {
		Fantasia = fantasia;
	}

	public String getRazao() {
		return Razao;
	}

	public void setRazao(String razao) {
		Razao = razao;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public BigInteger getMatrizId() {
		return MatrizId;
	}

	public void setMatrizId(BigInteger matrizId) {
		MatrizId = matrizId;
	}
	
	
	
	
}
