package com.flowergarden.db;

import java.sql.Connection;
import java.util.List;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;

public class BouquetDAO {
	
	private Connection conn;
	
	public BouquetDAO(Connection conn) {
		this.conn = conn;
	}

	public MarriedBouquet getMarriedBouquet(int bouquetId) {
		MarriedBouquet marrideBouqet = new MarriedBouquet();
		
		conn.prepareStatement("select * from flower where bouquet_id = ?");
		
		marrideBouqet.addFlower(flower);
		return marrideBouqet;
	}

}
