package com.ecom.shopping.service;

import com.ecom.shopping.repository.ProductsRepository;
import com.ecom.shopping.model.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class ProductService{

    @Autowired
    ProductsRepository productsRepository;

    public List<products> getProducts()
    {
        return productsRepository.findAll();
    }

    public products addProduct(products product)
    {
        return productsRepository.save(product);

    }
    public products getProductByID(int id)
    {
        return productsRepository.findByProductId(id);
    }
    public List<products> getProductByCategory(String category)
    {
        return productsRepository.findAllByCategory(category);
    }

    public String deleteProduct(int id) {
         productsRepository.deleteById(id);
        return "deleted";
    }
  public String deleteAll() {
      productsRepository.deleteAll();
      return "done";
  }
   public List<products> getProductByCategoryAndPrice(String category,double min,double max)
    {
        return productsRepository.findByCategoryAndPriceBetween(category,min,max);
    }
    public List<products> getProductByPrice(double min,double max)
    {
        return productsRepository.findByPriceBetween(min,max);
    }
    public products changeProductDetails(products products) {
        products oldProduct = productsRepository.findByProductId(products.getProductId());
        oldProduct.setProductId(products.getProductId());
        oldProduct.setName(products.getName());
        oldProduct.setPrice(products.getPrice());
        oldProduct.setCategory(products.getCategory());
        oldProduct.setDetails(products.getDetails());
        productsRepository.saveAndFlush(oldProduct);
        return oldProduct;
    }

    public Set<products> getSearchedData(String searchedItem) {
        List<products> productsList = productsRepository.findAll();
        Set<products> result = new HashSet<>();

        for(int i=0; i<productsList.size(); i++) {
            if(productsList.get(i).getName().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    productsList.get(i).getCategory().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    productsList.get(i).getDetails().toLowerCase().contains(searchedItem.toLowerCase())) {

                result.add(productsList.get(i));
            }
        }
        return result;
    }
}
