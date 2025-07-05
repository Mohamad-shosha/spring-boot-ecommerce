package com.shosha.ecommerce.service.mapper;



import com.shosha.ecommerce.dto.ProductCategoryDTO;
import com.shosha.ecommerce.entity.Product;
import com.shosha.ecommerce.entity.ProductCategory;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends EntityMapper<ProductCategoryDTO, ProductCategory> {


    @Override
    @Mapping(target = "productIds", expression = "java( toProductIdSet(category.getProducts()) )")
    ProductCategoryDTO toDto(ProductCategory category);

    @Override
    @Mapping(target = "products", expression = "java( fromIdSet(dto.getProductIds()) )")
    ProductCategory toEntity(ProductCategoryDTO dto);


    default Set<Long> toProductIdSet(Set<Product> products) {
        return products == null ? null :
                products.stream().map(Product::getId).collect(Collectors.toSet());
    }

    default Set<Product> fromIdSet(Set<Long> ids) {
        return ids == null ? null :
                ids.stream().map(this::productFromId).collect(Collectors.toSet());
    }

    default Product productFromId(Long id) {
        if (id == null) return null;
        Product p = new Product();
        p.setId(id);
        return p;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget ProductCategory entity, ProductCategoryDTO dto);
}

