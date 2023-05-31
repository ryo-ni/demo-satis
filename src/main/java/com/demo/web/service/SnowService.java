package com.demo.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.web.dto.TestResultDto;
import com.demo.web.entity.TestResultEntity;
import com.demo.web.repository.TestResultRepository;

@Service
public class SnowService {
	
	@Autowired
	TestResultRepository _testResultRepository;
	
	public TestResultDto testResult(int ticketNumber) {
		
		// チケット番号をキーに試験結果テーブルからレコード取得
		Optional<TestResultEntity> optionalTestResultEntity = _testResultRepository.findById(null);
		TestResultEntity testResultEntity = optionalTestResultEntity.get();
		
		// 試験データをDTOに詰め替え
		TestResultDto testResultDto = new TestResultDto();
		testResultDto.setTicketNumber(testResultEntity.getTicketNumber());
		testResultDto.setContractType(testResultEntity.getContractType());
		testResultDto.setFaultDevice(testResultEntity.getFaultDevice());
		testResultDto.setResult(testResultEntity.getResult());
		
		// DTO返却
		return testResultDto;
	}
	

}
