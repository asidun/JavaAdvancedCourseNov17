package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.flowergarden.properties.FreshnessInteger;

@XmlRootElement
public class Rose extends GeneralFlower {
	
	@XmlElement
	private boolean spike;
	
	public Rose(boolean spike, int lenght, float price, FreshnessInteger fresh){
		super(lenght, price, fresh);
		this.spike = spike;
		
	}
	public Rose(){
		
	}
	
	public static Rose getInstanceSample(){
		return getInstanceWithFreshness(1);
	}
	
	public static Rose getInstanceWithFreshness(int freshness){
		return new Rose(true, 10, 22, new FreshnessInteger(freshness));
	}
	
	public boolean getSpike(){
		return spike;
	}
	
	@Override
	public Rose createFlower() {
		return new Rose(true, 10, 22, new FreshnessInteger(1));
	}
	


}
