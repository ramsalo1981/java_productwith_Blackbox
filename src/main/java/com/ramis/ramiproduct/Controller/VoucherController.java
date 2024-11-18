package com.ramis.ramiproduct.Controller;

import com.ramis.ramiproduct.Repository.VoucherRepository;
import com.ramis.ramiproduct.entity.Voucher;
import com.ramis.ramiproduct.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public List<Voucher> getAllVouchers() {
        return voucherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable int id) {
        Voucher voucher = voucherService.findById(id);
        return voucher != null ? ResponseEntity.ok(voucher) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Voucher createVoucher(@RequestBody Voucher voucher) {
        return voucherService.save(voucher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable int id, @RequestBody Voucher voucherDetails) {
        Voucher updatedVoucher = voucherService.update(id, voucherDetails);
        return updatedVoucher != null ? ResponseEntity.ok(updatedVoucher) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable int id) {
        voucherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Search by code
    @GetMapping("/search/code")
    public List<Voucher> searchByCode(@RequestParam String code) {
        return voucherService.searchByCode(code);
    }

    // Search by discount
    @GetMapping("/search/discount")
    public List<Voucher> searchByDiscount(@RequestParam double discount) {
        return voucherService.searchByDiscount(discount);
    }
}
