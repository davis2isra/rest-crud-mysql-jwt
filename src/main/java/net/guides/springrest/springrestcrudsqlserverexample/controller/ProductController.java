package net.guides.springrest.springrestcrudsqlserverexample.controller;

import net.guides.springrest.springrestcrudsqlserverexample.exception.ResourceNotFoundException;
import net.guides.springrest.springrestcrudsqlserverexample.model.AppUser;
import net.guides.springrest.springrestcrudsqlserverexample.model.AppUserAuth;
import net.guides.springrest.springrestcrudsqlserverexample.model.Product;
import net.guides.springrest.springrestcrudsqlserverexample.repository.ProductRepository;
import net.guides.springrest.springrestcrudsqlserverexample.service.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SecurityManager securityManager;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("Producto no encontrado para el id: " + productId));

        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value="id") Long productId,
                                                 @Valid @RequestBody Product productDetails)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("Producto no encontrado para el id: " + productId));

        product.setId(productDetails.getId());
        product.setProductName(productDetails.getProductName());
        product.setProductCode(productDetails.getProductCode());
        product.setPrice(productDetails.getPrice());
        product.setReleaseDate(productDetails.getReleaseDate());
        product.setDescription(productDetails.getDescription());
        product.setStarRating(productDetails.getStarRating());
        product.setImageUrl(productDetails.getImageUrl());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value="id") Long productId)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ResourceNotFoundException("Producto no encontrado para el id: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<AppUserAuth> login(@RequestBody AppUser user) {
        AppUserAuth auth = null;

        auth = securityManager.validateUser(user);
        if(auth.isIsAuthenticated()) {
            return ResponseEntity.ok(auth);
        } else {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }


}
