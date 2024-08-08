package be.com.api.reduct.controller;


import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import be.com.api.reduct.models.EmpresaModel;
import be.com.api.reduct.repositories.EmpresaRepository;

@RestController
public class EmpresaController {
		
	@Autowired
	EmpresaRepository empresaRepository;
	
	@GetMapping("/empresas")
	public ResponseEntity<List<EmpresaModel>> getAllEmpresas(){
		List<EmpresaModel>empresaList = empresaRepository.findAll();
		if(!empresaList.isEmpty()) {
			for(EmpresaModel empresa : empresaList) {
				BigInteger id = empresa.getId();
				//empresa.add(linkTo(methodOn(EmpresaController.class).getOneEmpresa(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(empresaList);
	}
	
}
