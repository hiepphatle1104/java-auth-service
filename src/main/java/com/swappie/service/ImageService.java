package com.swappie.service;

import com.swappie.domain.entities.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    Image getImageById(String id);

    List<Image> getAllImagesById(String id);

    void deleteImageById(String id);
}
