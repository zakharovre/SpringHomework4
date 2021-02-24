package ru.geekbrains.hw4.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.hw4.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;


@Component
public class ProductRepo {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "Apple", 50));
        products.add(new Product(2, "Orange", 100));
        products.add(new Product(3, "Peach", 75));
        products.add(new Product(4, "Banana", 25));
        products.add(new Product(5, "Grape", 650));
    }

    public Product addOrUpdate(Product p) {
        if (p.getId() > 0) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == p.getId()) {
                    products.set(i, p);
                    return p;
                }
            }
        }
        int newId = products.stream().mapToInt(Product::getId).max().orElse(0) + 1;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductByID(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void removeByID(int id) {
        products.removeIf(p -> p.getId() == id);
    }

}
