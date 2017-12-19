package com.flowergarden.db;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.exception.BouquetException;
import com.flowergarden.exception.FlowerException;
import com.flowergarden.exception.FlowerNotFoundException;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.properties.FreshnessInteger;

@RunWith(MockitoJUnitRunner.class) 
public class MarriedBouquetDAOTests {	
	
	@Mock
	Connection mockedConn;
	@Mock
	PreparedStatement mockedPreparedStatement;
	@Mock
	ResultSet mockedResultSet;
	@Mock
	FlowerDAO mockedFlowerDAO;
	
	@Test
	public void getMarriedBouquetTest() throws SQLException, FlowerNotFoundException, FlowerException{
		//Given
		when(mockedResultSet.getInt("id")).thenReturn(1);
		when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
		
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedConn.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
		
		when(mockedFlowerDAO.getFlowerById(1)).thenReturn(new GeneralFlower(10, 12.2f, new FreshnessInteger(1)));
		
		BouquetDAO bouquetDAO = new BouquetDAO(mockedConn);
		bouquetDAO.setFlowerDAO(mockedFlowerDAO);
		//When
		MarriedBouquet bouquet = null;
		try {
			bouquet = bouquetDAO.getMarriedBouquet(1);
		} catch (BouquetException e) {
			e.printStackTrace();
		}
		
		
		//Then		
		verify(mockedResultSet, atLeast(2)).next();
		verify(mockedResultSet).getInt("id");
		verify(mockedFlowerDAO).getFlowerById(1);
		assertEquals(132.2f, bouquet.getPrice(), 0.01);
		
	}

}
