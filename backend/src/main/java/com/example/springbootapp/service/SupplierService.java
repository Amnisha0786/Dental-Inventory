package com.example.springbootapp.service;

import com.example.springbootapp.dto.SupplierDTO;
import com.example.springbootapp.exception.ResourceNotFoundException;
import com.example.springbootapp.model.Supplier;
import com.example.springbootapp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());
        
        Supplier saved = supplierRepository.save(supplier);
        return convertToDTO(saved);
    }
    
    public SupplierDTO updateSupplier(Integer id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
        
        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());
        
        Supplier updated = supplierRepository.save(supplier);
        return convertToDTO(updated);
    }
    
    private SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setEmail(supplier.getEmail());
        dto.setPhone(supplier.getPhone());
        dto.setCreatedAt(supplier.getCreatedAt());
        return dto;
    }
}