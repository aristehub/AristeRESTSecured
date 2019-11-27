package com.aristaREST.aristahub.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//spring imports
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//arista
import com.aristaREST.aristahub.entities.Product;
import com.aristaREST.aristahub.services.ProductServiceImpo;

//packages
import java.util.List;
import java.lang.RuntimeException;

@RestController
@RequestMapping("/api")
public class ProductRestController
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private ProductServiceImpo productServ;
	//private ProductService productServ;
	/*
	 * -------------------------------------------------------	
	 * 					CONSTRUCTOR
	 * -------------------------------------------------------
	 */
	//Inject product DAO
	@Autowired
	public ProductRestController(ProductServiceImpo ps)
	{
		productServ = ps;
	}
	
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	//expose products in the web
	@GetMapping("/products")
	public List<Product> findAll()
	{
		//delegate find all to the DAO
		return productServ.findAll();
	}
	//GET method single product
	@GetMapping("/product/{product_id}")
	public Product getProduct(@PathVariable int product_id)
	{
		Product prod = productServ.findById(product_id);
		//empty case
		if(prod == null) { throw new RuntimeException("Product id not found - " + product_id); }
		//return statement
		return prod;
	}
	//add new product, POST method..
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product prod)
	{
		//JST in case they pass an id in JSON id=0
		prod.setProduct_id(0);
		productServ.saveProduct(prod);
		return prod;
	}
	//Update PUT method back a response echo rest client
	@PutMapping("/product")
	public Product updateProd(@RequestBody Product prod)
	{
		productServ.saveProduct(prod);
		return prod;
	}
	//DELETE method request...
	@DeleteMapping("/product/{product_id}")
	public String deleteProduct(@PathVariable int product_id)
	{
		Product prod = productServ.findById(product_id);
		//Throw exception empty
		if(prod == null) { throw new RuntimeException("The product does not exist.." + product_id); }
		productServ.deleteProduct(product_id);
		return "removed";
	}
}