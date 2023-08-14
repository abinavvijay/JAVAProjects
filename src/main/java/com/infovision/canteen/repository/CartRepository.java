package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.cart.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>{

}
