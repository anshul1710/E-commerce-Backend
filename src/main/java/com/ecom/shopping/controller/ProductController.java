package com.ecom.shopping.controller;
import com.ecom.shopping.model.products;
import com.ecom.shopping.repository.ProductsRepository;
import com.ecom.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class
ProductController
{
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductService productService;

    @GetMapping(path = "/all")
    public List<products> getAll()
    {
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public products getProduct(@PathVariable("id") int id)
    {
        return productService.getProductByID(id);
    }

    @GetMapping("/product-detail/{category}")
    public List<products> getProduct(@PathVariable("category") String category)
   {
       List<products> list =  productService.getProductByCategory(category);
        return productService.getProductByCategory(category);
    }
    @PostMapping(path = "/enter", consumes = "application/json", produces = "application/json")
    public products addProduct(@RequestBody products product)
    {
       return productService.addProduct(product);
    }

    @GetMapping("/product-del/{id}")
    public String deleteProduct(@PathVariable("id") int id)
    {
        return productService.deleteProduct(id);
    }

    @GetMapping("/del")
    public String deleteAll()
    {
        return productService.deleteAll();
    }

    @GetMapping("/products-cat/{category}/{min}/{max}")
    public List<products>getProductsByPriceAndCategory(@PathVariable("category") String category,@PathVariable("min") double min,@PathVariable("max") double max)
    {

        return productService.getProductByCategoryAndPrice(category,min,max);
    }

    @GetMapping("/all-Products/{min}/{max}")
    public List<products>getProductsByPrice(@PathVariable("min") double min,@PathVariable("max") double max)
    {
        return productService.getProductByPrice(min,max);
    }
    @PostMapping("/editProduct")
    public products editUsers(@RequestBody products products)
    {
        return productService.changeProductDetails(products);
    }
}
