package com.flowergarden.strategy;

public class SeasonDiscount implements DiscountStrategy {

	@Override
	public float applyDiscount(float price) {
		return (float) (price*0.8);
	}

}
