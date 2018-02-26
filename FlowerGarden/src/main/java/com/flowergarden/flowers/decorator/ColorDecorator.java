package com.flowergarden.flowers.decorator;

import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.properties.Freshness;

public class ColorDecorator implements Flower {
	
	private String color;
	private Flower flower;
	
	public ColorDecorator(Flower flower){
		this.flower = flower;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public Freshness getFreshness() {
		return flower.getFreshness();
	}

	@Override
	public float getPrice() {
		return flower.getPrice();
	}

	@Override
	public int getLenght() {
		return flower.getLenght();
	}

	@Override
	public Flower createFlower() {
		return flower.createFlower();
	}

}
