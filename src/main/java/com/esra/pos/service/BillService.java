package com.esra.pos.service;

import com.esra.pos.model.Bill;
import com.esra.pos.repository.BillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill markAsPaid(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fatura/Hesap bulunamadı!"));
        bill.setIsPaid(true);
        return billRepository.save(bill);
    }
}