package com.ramis.ramiproduct.Repository;

import com.ramis.ramiproduct.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
    List<Voucher> findByCodeContainingIgnoreCase(String code);

    List<Voucher> findByDiscount(double discount);
}
