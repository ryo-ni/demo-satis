package com.demo.web.form;


/**
 * 試験依頼画面の入力値を受取るフォームクラス
 *
 */
public class TestForm {
	
	private String faultDevice;
	private String contractType;

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
