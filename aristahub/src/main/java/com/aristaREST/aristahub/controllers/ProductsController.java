package com.aristaREST.aristahub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aristaREST.aristahub.entities.Product;
import com.aristaREST.aristahub.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private ProductService proServ;
	
	 /*
	 * -------------------------------------------------------	
	 * 					CONSTRUCTOR
	 * -------------------------------------------------------
	 */
	@Autowired
	public ProductsController(ProductService service) { this.proServ = service; }
	
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	@GetMapping("/list")
	public String listEmployees(Model modeler) 
	{	
		// get employees from database
		List<Product> listProducts = proServ.findAll();
		// add to the spring model
		modeler.addAttribute("products", listProducts);
		return "products/list-products";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model modeler) 
	{
		// create model attribute to bind form data
		Product newProd = new Product();
		//add new attribute to the model
		modeler.addAttribute("employee", newProd);
		//map to templates product form
		return "products/product-form";
	}
	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int product_id, Model modeler)
	{
		//get Product form the service with the id
		Product prod = this.proServ.findById(product_id);
		//set product as a model attribute to PRE-populate the form
		modeler.addAttribute("product", prod);
		//send over the form
		return "product/produt-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("product") Product newProd) {
		
		// save new product
		proServ.saveProduct(newProd);
		// use a redirect to prevent duplicate submissions
		return "redirect:/products/list";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int product_id)
	{
		//delete product
		this.proServ.deleteProduct(product_id);
		//redirect to the list
		return "redirect:/products/list";
	}
}
