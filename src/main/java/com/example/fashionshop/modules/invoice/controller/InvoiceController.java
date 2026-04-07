package com.example.fashionshop.modules.invoice.controller;

import com.example.fashionshop.common.response.ApiResponse;
import com.example.fashionshop.modules.invoice.dto.InvoiceResponse;
import com.example.fashionshop.modules.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/manage")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<List<InvoiceResponse>> getAllInvoices() {
        return ApiResponse.success("Invoices fetched successfully", invoiceService.getAllInvoices());
    }

    @GetMapping("/orders/{orderId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','STAFF','ADMIN')")
    public ApiResponse<InvoiceResponse> getByOrderId(@PathVariable Integer orderId) {
        return ApiResponse.success("Invoice fetched successfully", invoiceService.getByOrderId(orderId));
    }

    @GetMapping("/{invoiceId}")
    @PreAuthorize("hasAnyRole('CUSTOMER','STAFF','ADMIN')")
    public ApiResponse<InvoiceResponse> getById(@PathVariable Integer invoiceId) {
        return ApiResponse.success("Invoice fetched successfully", invoiceService.getById(invoiceId));
    }
}
