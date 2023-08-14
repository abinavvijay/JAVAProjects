package com.infovision.canteen.service;

import java.util.UUID;

import com.infovision.canteen.exception.RevenueException;

public interface RevenueService {

	double dayRevenue(UUID restId) throws RevenueException;

	double weekRevenue(UUID restId)throws RevenueException;

	double monthRevenue(UUID restId)throws RevenueException;

	double dayAllRestRevenue()throws RevenueException;

	double weekAllRestRevenue()throws RevenueException;

	double monthAllRestRevenue()throws RevenueException;

}
