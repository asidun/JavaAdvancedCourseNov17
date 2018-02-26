package com.flowergarden.flowers;

import com.flowergarden.composite.PriceComposite;
import com.flowergarden.properties.Freshness;

public interface Flower<T> extends PriceComposite {
	Freshness<T> getFreshness();
	float getPrice();
	int getLenght();
	Flower<T> createFlower();
}
