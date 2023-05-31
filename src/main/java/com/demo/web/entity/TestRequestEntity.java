package com.demo.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_request")
public class TestRequestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_number")
	private int ticketNumber;
	@Column(name = "fault_device")
	private String faultDevice;
	@Column(name = "contranct_type")
	private String contractType;
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getFaultDevice() {
		return faultDevice;
	}
	public void setFaultDevice(String faultDevice) {
		this.faultDevice = faultDevice;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	

}