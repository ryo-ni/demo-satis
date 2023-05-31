package com.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResultDto {
	
	@JsonProperty("u_ticket_number")
	private int ticketNumber;
	@JsonProperty("u_fault_device")
	private String faultDevice;
	@JsonProperty("u_contranct_type")
	private String contractType;
	@JsonProperty("u_result")
	private String result;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
