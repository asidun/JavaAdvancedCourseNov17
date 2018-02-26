package com.flowergarden.run;

import java.util.List;
import java.util.Scanner;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.composite.PriceComposite;
import com.flowergarden.store.StoreRefregerator;
import com.flowergarden.strategy.DiscountStrategy;
import com.flowergarden.strategy.SeasonDiscount;

public class Run {
	
	private DiscountStrategy discount;

	public static void main(String[] args) {

		System.out.print("Enter count of roses: ");
		Scanner sc = new Scanner(System.in);
		int roses = sc.nextInt();
		System.out.print("Enter count of chamomiles: ");
		int cham = sc.nextInt();
		sc.close();

		StoreRefregerator.getInstance().fill(roses, cham);
		MarriedBouquet bouquet = new MarriedBouquet();
		for (int i = 0; i < 5; i++) {
			bouquet.addFlower(StoreRefregerator.getInstance().getStore().get("rose").removeLast());
		}
		for (int i = 0; i < 2; i++) {
			bouquet.addFlower(StoreRefregerator.getInstance().getStore().get("chamomile").removeLast());
		}

		System.out.println("Price: " + bouquet.getPrice());
		Run run = new Run();
		run.setDiscount(new SeasonDiscount());
		System.out.println("Price with discount: "  + run.getDiscount().applyDiscount(bouquet.getPrice()));

	}
	
	public float getSum(List<PriceComposite> list){
		float total = 0;
		for (PriceComposite elenemt : list) {
			total += elenemt.getPrice();
		}
		return discount.applyDiscount(total);
		
	}

	public DiscountStrategy getDiscount() {
		return discount;
	}

	public void setDiscount(DiscountStrategy discount) {
		this.discount = discount;
	}

}
