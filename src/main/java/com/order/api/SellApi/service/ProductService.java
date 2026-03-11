package com.order.api.SellApi.service;

import com.order.api.SellApi.domain.Product;
import com.order.api.SellApi.dto.request.ProductRequestDTO;
import com.order.api.SellApi.dto.response.ProductApiResponseDTO;
import com.order.api.SellApi.dto.response.ProductResponseDTO;
import com.order.api.SellApi.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductService {
    private static final String API_FAKE_STORE = "https://fakestoreapi.com";
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate){
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }
    @Transactional
    public ProductResponseDTO save(ProductRequestDTO dto){
        Product newProduct =
                Product.builder()
                        .title(dto.nome())
                        .price(dto.preco())
                        .description(dto.descricao())
                        .stock(dto.estoque())
                        .imagePath(dto.pathImage())
                        .build();
        Product createdProduct = productRepository.save(newProduct);
        return new ProductResponseDTO(createdProduct);
    }
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for update"));

        product.setTitle(dto.nome());
        product.setPrice(dto.preco());
        product.setDescription(dto.descricao());
        product.setStock(dto.estoque());
        product.setImagePath(dto.pathImage());

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(updatedProduct);
    }
    public ProductResponseDTO findById(Long id){
        return new ProductResponseDTO(
                productRepository.findById(id).orElseThrow( () -> new RuntimeException("Product not found")));
    }
    public Page<ProductResponseDTO> list(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductResponseDTO::new);
    }
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Could not delete: Product not found.");
        }
        productRepository.deleteById(id);
    }

    public List<ProductApiResponseDTO> getApiProducts(){
        String urlProducts = API_FAKE_STORE+"/products";
        ProductApiResponseDTO[] apiProducts = restTemplate.getForObject(urlProducts, ProductApiResponseDTO[].class);
        if(!Objects.isNull(apiProducts)){
            return Arrays.stream(apiProducts).toList();
        }
        throw new RuntimeException("No products found from external API");
    }
    @Transactional
    public List<ProductResponseDTO> fetchSaveApiProducts(){
        try{
            List<ProductApiResponseDTO> apiProducts = getApiProducts();
            List<Product> productsToSave = new ArrayList<>();
            for(ProductApiResponseDTO dto : apiProducts){
                Product product = Product.
                        builder()
                        .title(dto.title())
                        .description(dto.description())
                        .price(dto.price())
                        .imagePath(dto.image())
                        .stock(new Random().nextInt())
                        .idProductApi(dto.id())
                        .build();
                productsToSave.add(product);
            }
            return productRepository.saveAll(productsToSave).stream().map(ProductResponseDTO::new ).toList();
        }catch (Exception e){
            throw new RuntimeException("Error in fetch and save api products");
        }
    }
}
