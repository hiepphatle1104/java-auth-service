package com.swappie.service.impl;

import com.swappie.domain.entities.Image;
import com.swappie.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Image getImageById(String id) {
        return null;
    }

    @Override
    public List<Image> getAllImagesById(String id) {
        return List.of();
    }

    @Override
    public void deleteImageById(String id) {

    }
}
