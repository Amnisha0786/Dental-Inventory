package com.example.springbootapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SupplierDTO {
    private Integer id;
    
    @NotBlank(message = "Supplier name is required")
    private String name;
    
    @Email(message = "Invalid email format")
    private String email;
    
    private String phone;
    private LocalDateTime createdAt;
    
    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}