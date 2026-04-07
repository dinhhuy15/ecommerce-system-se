package com.example.fashionshop.modules.invoice.repository;

import com.example.fashionshop.modules.invoice.entity.Invoice;
import com.example.fashionshop.modules.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByOrder(Order order);
}
