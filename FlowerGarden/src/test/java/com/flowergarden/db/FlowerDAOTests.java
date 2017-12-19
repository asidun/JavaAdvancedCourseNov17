package com.flowergarden.db;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.flowergarden.exception.FlowerException;
import com.flowergarden.exception.FlowerNotFoundException;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

@RunWith(MockitoJUnitRunner.class) 
public class FlowerDAOTests {
	
	@Mock
	Connection mockedConn;
	@Mock
	PreparedStatement mockedPreparedStatement;
	@Mock
	ResultSet mockedResultSet;
	
	FlowerDAO flowerDAO;

	@Test
	public void getRoseByIdTest() throws SQLException, FlowerNotFoundException, FlowerException{
		//Given
		when(mockedResultSet.getString("name")).thenReturn("rose");
		when(mockedResultSet.getBoolean("spike")).thenReturn(true);
		
		//When
		Rose flower = (Rose) flowerDAO.getFlowerById(1);
		
		//Then
		assertEquals(10, flower.getLenght());
		assertEquals(new FreshnessInteger(1), flower.getFreshness());
		assertEquals(12.2f, flower.getPrice(), 0.1);
		assertTrue(flower.getSpike());
	}
	
	@Test
	public void getChamomileByIdTest() throws SQLException, FlowerNotFoundException, FlowerException{
		//Given
		when(mockedResultSet.getString("name")).thenReturn("chamomile");
		when(mockedResultSet.getInt("petals")).thenReturn(5);
		
		//When
		Chamomile flower = (Chamomile) flowerDAO.getFlowerById(1);
		
		//Then
		assertEquals(10, flower.getLenght());
		assertEquals(new FreshnessInteger(1), flower.getFreshness());
		assertEquals(12.2f, flower.getPrice(), 0.1);
		assertEquals(5, flower.getPetals());
	}
	
	@Test(expected = FlowerNotFoundException.class)
	public void getRoseByIdExceptionTest() throws SQLException, FlowerNotFoundException, FlowerException{
		//Given
		when(mockedResultSet.getString("name")).thenReturn("badflower");
		
		//When
		flowerDAO.getFlowerById(1);
	}

	@Before
	public void init() throws SQLException {
		when(mockedResultSet.getInt("lenght")).thenReturn(10);
		when(mockedResultSet.getInt("freshness")).thenReturn(1);
		when(mockedResultSet.getFloat("price")).thenReturn(12.2f);
		when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
		
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedConn.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
		
		flowerDAO = new FlowerDAO(mockedConn);
	}
}
