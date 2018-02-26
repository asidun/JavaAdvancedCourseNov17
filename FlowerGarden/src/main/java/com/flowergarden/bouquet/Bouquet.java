package com.flowergarden.bouquet;
import java.util.Collection;

import com.flowergarden.composite.PriceComposite;

public interface Bouquet<T> extends PriceComposite {
	float getPrice();
	void addFlower(T flower);
	Collection<T> searchFlowersByLenght(int start, int end);
	void sortByFreshness();
	Collection<T> getFlowers();
}
