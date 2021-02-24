package ru.geekbrains.hw4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.hw4.exceptions.NoSuchIdException;
import ru.geekbrains.hw4.models.Product;
import ru.geekbrains.hw4.repositories.ProductRepo;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product getById(int id){
        if(productRepo.getProductByID(id).isPresent())
            return productRepo.getProductByID(id).get();
        else
            throw new NoSuchIdException("NO PRODUCT WITH SUCH ID");
    }

    public List<Product> getAll(){
        return productRepo.getAllProducts();
    }

    public Product addOrUpdate(Product product) {
        return productRepo.addOrUpdate(product);
    }

    public void removeProductById(int id){
        productRepo.removeByID(id);
    }

}
