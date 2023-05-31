package com.demo.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.web.api.SnowApi;
import com.demo.web.dto.SnowApiRequestDto;
import com.demo.web.dto.TestResultDto;
import com.demo.web.entity.TestRequestEntity;
import com.demo.web.entity.TestResultEntity;
import com.demo.web.form.TestForm;
import com.demo.web.repository.TestRequestRepository;
import com.demo.web.repository.TestResultRepository;

@Service
public class TestService {

	@Autowired
	private TestRequestRepository _testRequestRepository;

	@Autowired
	private TestResultRepository _testResultRepository;

	@Autowired
	private SnowApi _SNOWApi;

	// SNOW連携成功のステータスコード
	private final int SNOW_SUCCESS_CODE = 201;
	
	// 試験依頼の登録失敗時のチケット番号
	private final int FAULT_TICKET_NUMBER = 0;

	public int testRequest(TestForm testForm) {
		
		
		// 故障機器と契約タイプが空でないことをチェック
		if(testForm.getFaultDevice().equals("") || testForm.getContractType().equals("")) {
			// 登録失敗時
			return FAULT_TICKET_NUMBER;
		}
		

		// 試験依頼テーブル用のエンティティを生成
		TestRequestEntity testRequestEntity = new TestRequestEntity();
		testRequestEntity.setFaultDevice(testForm.getFaultDevice());
		testRequestEntity.setContractType(testForm.getContractType());

		// 入力をDB登録（ticket_numberが自動採番）
		_testRequestRepository.save(testRequestEntity);
		
		// チケット番号を返す
		return testRequestEntity.getTicketNumber();
	}

	// 追加必要
	public TestResultDto testResult(int ticketNumber) {

		// チケット番号をキーに試験依頼テーブルからレコード取得
		Optional<TestRequestEntity> optinalTestRequestEntity = _testRequestRepository.findById(ticketNumber);
		TestRequestEntity testRequestEntity = optinalTestRequestEntity.get();

		// 試験結果テーブルに登録する用のエンティティ生成
		TestResultEntity testResultEntity = new TestResultEntity();
		testResultEntity.setTicketNumber(testRequestEntity.getTicketNumber());
		testResultEntity.setContractType(testRequestEntity.getContractType());
		testResultEntity.setFaultDevice(testRequestEntity.getFaultDevice());

		// 契約タイプから試験結果を判定
		switch (testRequestEntity.getContractType()) {
		case "契約タイプA":
			testResultEntity.setResult("正常");
			break;
		case "契約タイプB":
			testResultEntity.setResult("異常");
			break;
		// 一致しなかった場合
		default:
			testResultEntity.setResult("異常");
		}

		// 判定後、試験結果テーブルにレコード登録する
		_testResultRepository.save(testResultEntity);

		// SNOWに連携する
		SnowApiRequestDto sNOWApiRequestDto = new SnowApiRequestDto();
		sNOWApiRequestDto.setTicketNumber(testResultEntity.getTicketNumber());
		sNOWApiRequestDto.setFaultDevice(testResultEntity.getContractType());
		sNOWApiRequestDto.setContractType(testResultEntity.getFaultDevice());
		sNOWApiRequestDto.setResult(testResultEntity.getResult());

		// SNOW連携結果の成功・失敗を判定
		int statusCode = _SNOWApi.execSNOWApi(sNOWApiRequestDto);

		// 画面に返す用のDTO
		TestResultDto testResultDto = new TestResultDto();

		// 連携成功した場合、DTOにデータを詰めて返却
		if (statusCode == SNOW_SUCCESS_CODE) {
			testResultDto.setTicketNumber(testResultEntity.getTicketNumber());
			testResultDto.setContractType(testResultEntity.getContractType());
			testResultDto.setFaultDevice(testResultEntity.getFaultDevice());
			testResultDto.setResult(testResultEntity.getResult());
		}

		return testResultDto;

	}
}
