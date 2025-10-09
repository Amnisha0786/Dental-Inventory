package com.example.springbootapp.controller;

import com.example.springbootapp.dto.CostAnalysisDTO;
import com.example.springbootapp.dto.LowStockSummaryDTO;
import com.example.springbootapp.dto.StockValueDTO;
import com.example.springbootapp.dto.UsageReportDTO;
import com.example.springbootapp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/usage")
    public ResponseEntity<UsageReportDTO> getUsageReport() {
        UsageReportDTO report = reportService.getUsageReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/costs")
    public ResponseEntity<CostAnalysisDTO> getCostAnalysis() {
        CostAnalysisDTO analysis = reportService.getCostAnalysis();
        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/low-stock-summary")
    public ResponseEntity<LowStockSummaryDTO> getLowStockSummary() {
        LowStockSummaryDTO summary = reportService.getLowStockSummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/stock-value")
    public ResponseEntity<StockValueDTO> getTotalStockValue() {
        StockValueDTO value = reportService.getTotalStockValue();
        return ResponseEntity.ok(value);
    }
}