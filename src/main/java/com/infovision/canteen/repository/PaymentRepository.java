package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.employee.Profile;
import com.infovision.canteen.model.payment.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID>{

}
