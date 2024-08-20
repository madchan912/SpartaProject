package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.ProductDetailResponseDto;
import com.sparta.ecommerce.dto.ProductResponseDto;
import com.sparta.ecommerce.entity.Product;
import com.sparta.ecommerce.entity.ProductDetail;
import com.sparta.ecommerce.repository.ProductDetailRepository;
import com.sparta.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    //전체 상품 목록을 조회하는 메서드
    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductResponseDto dto = new ProductResponseDto();
            dto.setPrdId(product.getProductId());
            dto.setPrdTitle(product.getTitle());
            dto.setPrdPrice(product.getPrice());
            dto.setPrdCtg(product.getCategory());
            return dto;
        }).collect(Collectors.toList());
    }

    // 특정 상품의 상세 정보를 조회 하는 메서드
    public ProductDetailResponseDto getProductDetail(long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        Optional<ProductDetail> productDetailOpt = productDetailRepository.findById(productId);

        if (!productOpt.isPresent() || !productDetailOpt.isPresent()) {
            throw new RuntimeException("상품 정보를 찾을 수 없습니다.");
        }

        Product product = productOpt.get();
        ProductDetail productDetail = productDetailOpt.get();

        ProductDetailResponseDto responseDto = new ProductDetailResponseDto();
        responseDto.setProductId(product.getProductId());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategory(product.getCategory());
        responseDto.setDetailTitle(productDetail.getDetailTitle());
        responseDto.setDetailPicture(productDetail.getDetailPicture());
        responseDto.setQuantity(productDetail.getQuantity());

        return responseDto;
    }
}