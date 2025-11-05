package com.swappie.mapper;

import com.swappie.domain.entities.BatterySpec;
import com.swappie.domain.entities.Image;
import com.swappie.domain.entities.Product;
import com.swappie.domain.entities.VehicleSpec;
import com.swappie.dto.BatterySpecDTO;
import com.swappie.dto.ImageDTO;
import com.swappie.dto.ProductDTO;
import com.swappie.dto.VehicleSpecDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "images", source = "images")
    @Mapping(target = "vehicleSpec", source = "vehicleSpec")
    @Mapping(target = "batterySpec", source = "batterySpec")
    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ProductDTO dto, @MappingTarget Product entity);

    @AfterMapping
    default void linkChildren(@MappingTarget Product product) {
        // Set product for each image
        if (product.getImages() != null)
            product.getImages().forEach(image -> image.setProduct(product));

        // Set product for vehicle spec
        if (product.getVehicleSpec() != null)
            product.getVehicleSpec().setProduct(product);

        // Set product for battery spec
        if (product.getBatterySpec() != null)
            product.getBatterySpec().setProduct(product);
    }

    // Using for image
    Image toEntity(ImageDTO dto);

    ImageDTO toDTO(Image entity);

    // Using for vehicle specs
    VehicleSpec toEntity(VehicleSpecDTO dto);

    VehicleSpecDTO toDTO(VehicleSpec entity);

    // Using for battery spec
    BatterySpec toEntity(BatterySpecDTO dto);

    BatterySpecDTO toDTO(BatterySpec entity);
}
