package com.ramis.ramiproduct.service;

import com.ramis.ramiproduct.Repository.VoucherRepository;
import com.ramis.ramiproduct.entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {
    private VoucherRepository voucherRepository;

    public List<Voucher> findAll(){
        return voucherRepository.findAll();
    }

    public Voucher findById(int id){
        return voucherRepository.findById(id).orElse(null);
    }

    public Voucher save(Voucher voucher){
        return voucherRepository.save(voucher);
    }

    public Voucher update(int id, Voucher voucherDetails) {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if (voucher != null) {
            voucher.setCode(voucherDetails.getCode());
            voucher.setDiscount(voucherDetails.getDiscount());
            voucher.setExpireDate(voucherDetails.getExpireDate());
            return voucherRepository.save(voucher);
        }
        return null; // or throw an exception
    }

    public void deleteById(int id) {
        voucherRepository.deleteById(id);
    }

    // Search by code
    public List<Voucher> searchByCode(String code) {
        return voucherRepository.findByCodeContainingIgnoreCase(code);
    }

    // Search by discount
    public List<Voucher> searchByDiscount(double discount) {
        return voucherRepository.findByDiscount(discount);
    }
}
