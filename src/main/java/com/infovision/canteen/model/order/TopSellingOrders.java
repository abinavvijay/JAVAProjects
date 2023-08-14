package com.infovision.canteen.model.order;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TopSellingOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID topsellId;
	
	
	private UUID itemId;
	
	private long count;
	
	private LocalDate date;

	public TopSellingOrders(UUID restItem, long count) {
		super();
		this.itemId = restItem;
		this.count = count;
	}
	
	

}
