package com.flowergarden.properties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class FreshnessInteger implements Freshness<Integer>, Comparable<FreshnessInteger> {

	@XmlElement
	private Integer freshness;
	
	@Override
	public Integer getFreshness() {
		return freshness;
	}
	
	public FreshnessInteger(Integer freshness){
		this.freshness = freshness;
	}
	
	public FreshnessInteger(){
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FreshnessInteger other = (FreshnessInteger) obj;
		if (freshness == null) {
			if (other.freshness != null)
				return false;
		} else if (!freshness.equals(other.freshness))
			return false;
		return true;
	}

	@Override
	public int compareTo(FreshnessInteger o) {
		if (freshness > o.getFreshness()) return 1;
		if (freshness < o.getFreshness()) return -1;
		return 0;
	}

}
