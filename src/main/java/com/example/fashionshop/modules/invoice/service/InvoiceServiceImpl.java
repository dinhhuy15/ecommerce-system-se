package com.example.fashionshop.modules.invoice.service;

import com.example.fashionshop.common.exception.BadRequestException;
import com.example.fashionshop.common.exception.ResourceNotFoundException;
import com.example.fashionshop.common.mapper.InvoiceMapper;
import com.example.fashionshop.common.util.SecurityUtil;
import com.example.fashionshop.modules.invoice.dto.InvoiceResponse;
import com.example.fashionshop.modules.invoice.entity.Invoice;
import com.example.fashionshop.modules.invoice.repository.InvoiceRepository;
import com.example.fashionshop.modules.order.entity.Order;
import com.example.fashionshop.modules.order.repository.OrderRepository;
import com.example.fashionshop.modules.user.entity.User;
import com.example.fashionshop.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public InvoiceResponse getByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        ensureAccess(order);

        Invoice invoice = invoiceRepository.findByOrder(order)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    public InvoiceResponse getById(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        ensureAccess(invoice.getOrder());
        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    private void ensureAccess(Order order) {
        User user = getCurrentUser();
        if (user.getRole().name().equals("CUSTOMER") && !order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You cannot access this invoice");
        }
    }

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Current user not found"));
    }
}
