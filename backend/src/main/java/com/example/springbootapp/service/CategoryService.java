package com.example.springbootapp.service;

import com.example.springbootapp.dto.CategoryDTO;
import com.example.springbootapp.model.Category;
import com.example.springbootapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setCreatedAt(category.getCreatedAt());
        return dto;
    }
}