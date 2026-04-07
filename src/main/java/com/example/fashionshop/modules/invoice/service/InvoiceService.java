package com.example.fashionshop.modules.invoice.service;

import com.example.fashionshop.modules.invoice.dto.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    InvoiceResponse getByOrderId(Integer orderId);

    InvoiceResponse getById(Integer invoiceId);

    List<InvoiceResponse> getAllInvoices();
}
