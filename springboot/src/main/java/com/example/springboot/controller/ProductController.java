package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;




@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
		
		var productModel = new ProductModel();
		
		BeanUtils.copyProperties(productRecordDto, productModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> getAllProducts(){
		List<ProductModel>productList = productRepository.findAll();
		if(!productList.isEmpty()) {
			for(ProductModel product : productList) {
				UUID id = product.getIdProduct();
				product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}
	
	//Buscando o produto pelo ID
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id")UUID id) {
		Optional<ProductModel> product0 = productRepository.findById(id);
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not fount");
		}
		product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(product0.get());
		
	}
	
	//Alterando o produto pelo ID
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id")UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){
		Optional<ProductModel> product0 = productRepository.findById(id);
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not fount");
		}
		var productModel = product0.get();
		BeanUtils.copyProperties(productRecordDto, productModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
	}
	
	
	//Apagando o produto pelo ID
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id")UUID id){
		Optional<ProductModel> product0 = productRepository.findById(id);
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not fount");
		}
		productRepository.delete(product0.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfull");
	}
	
}
