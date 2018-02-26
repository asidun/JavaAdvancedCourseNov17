package com.flowergarden.strategy;

public class ClientDiscount implements DiscountStrategy {

	@Override
	public float applyDiscount(float price) {
		return (float) (price*0.95);
	}

}
