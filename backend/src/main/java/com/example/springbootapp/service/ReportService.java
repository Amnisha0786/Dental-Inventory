package com.example.springbootapp.service;

import com.example.springbootapp.dto.CostAnalysisDTO;
import com.example.springbootapp.dto.LowStockSummaryDTO;
import com.example.springbootapp.dto.StockValueDTO;
import com.example.springbootapp.dto.UsageReportDTO;
import com.example.springbootapp.repository.InventoryRepository;
import com.example.springbootapp.repository.ProductRepository;
import com.example.springbootapp.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReportService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private StockMovementRepository stockMovementRepository;
    
    @Autowired
    private StockMovementService stockMovementService;
    
    public UsageReportDTO getUsageReport() {
        UsageReportDTO report = new UsageReportDTO();
        report.setTotalProducts((int) productRepository.count());
        report.setTotalMovements((int) stockMovementRepository.count());
        report.setRecentMovements(stockMovementService.getAllMovements(null, null, 10));
        return report;
    }
    
    public CostAnalysisDTO getCostAnalysis() {
        CostAnalysisDTO analysis = new CostAnalysisDTO();
        
        List<Object[]> results = inventoryRepository.findAll().stream()
                .map(inv -> new Object[]{
                        inv.getProduct().getCategory().getName(),
                        inv.getCurrentStock(),
                        inv.getProduct().getUnitCost()
                })
                .collect(Collectors.toList());
        
        Map<String, BigDecimal> categoryValues = new HashMap<>();
        BigDecimal totalValue = BigDecimal.ZERO;
        
        for (Object[] row : results) {
            String category = (String) row[0];
            Integer stock = (Integer) row[1];
            BigDecimal cost = (BigDecimal) row[2];
            BigDecimal value = cost.multiply(new BigDecimal(stock));
            
            categoryValues.merge(category, value, BigDecimal::add);
            totalValue = totalValue.add(value);
        }
        
        analysis.setTotalInventoryValue(totalValue);
        analysis.setValueByCategory(categoryValues);
        
        return analysis;
    }
    
    public LowStockSummaryDTO getLowStockSummary() {
        LowStockSummaryDTO summary = new LowStockSummaryDTO();
        
        List<Object[]> lowStockItems = inventoryRepository.findLowStockItems().stream()
                .map(inv -> new Object[]{
                        inv.getProduct().getCategory().getName(),
                        1
                })
                .collect(Collectors.toList());
        
        Map<String, Integer> categoryCount = new HashMap<>();
        
        for (Object[] row : lowStockItems) {
            String category = (String) row[0];
            categoryCount.merge(category, 1, Integer::sum);
        }
        
        summary.setTotalLowStockItems(lowStockItems.size());
        summary.setLowStockByCategory(categoryCount);
        
        return summary;
    }
    
    public StockValueDTO getTotalStockValue() {
        StockValueDTO valueDTO = new StockValueDTO();
        
        BigDecimal total = inventoryRepository.findAll().stream()
                .map(inv -> inv.getProduct().getUnitCost()
                        .multiply(new BigDecimal(inv.getCurrentStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        valueDTO.setTotalValue(total);
        valueDTO.setItemCount((int) inventoryRepository.count());
        
        return valueDTO;
    }
}