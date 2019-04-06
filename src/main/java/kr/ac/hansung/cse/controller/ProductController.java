package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller // 자동적으로 bean을
public class ProductController { // controller -> service -> dao
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();

		// product.jsp에서 products를 사용할 때 키값 설정
		model.addAttribute("products", products);

		return "product"; // view's logical name
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) {

		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);

		return "viewProduct";
	}
	
	/*
	 * @RequestMapping(value="/viewProduct", method=RequestMethod.POST) public
	 * String viewProductPost(Product product) {
	 * 
	 * if(!productService.getProduct(product))
	 * System.out.println("view product cannot be done");
	 * 
	 * return "redirect:/viewProduct"; }
	 */
}
