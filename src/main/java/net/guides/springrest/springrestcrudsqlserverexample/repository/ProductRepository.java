package net.guides.springrest.springrestcrudsqlserverexample.repository;

import net.guides.springrest.springrestcrudsqlserverexample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
