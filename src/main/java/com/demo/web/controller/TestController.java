package com.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.web.dto.TestResultDto;
import com.demo.web.form.TestForm;
import com.demo.web.service.TestService;

/*
 * リクエストを受付、画面を返すクラス
 */

@Controller
public class TestController {
	
	@Autowired
	TestService _testService;
	
	/**
	 * 実装概要
	 *  アプリに初回アクセスされるメソッド
	 *  
	 * 実装内容
	 *  ・リクエスト：GETメソッドでURL「/request」で受け付ける
	 *  ・画面：test_request.htmlを返す（試験依頼画面）
	 */
	@GetMapping("/request")
	public String testRequest() {
		return "test_request";
	}
	
	/**
	 * 実装概要
	 *  試験依頼画面からアクセスされるメソッド
	 *  フォーム入力値を受取る
	 *  
	 * 実装内容
	 *  ・リクエスト：POSTメソッドでURL「/confirm」で受け付ける
	 *  ・入力画面データ：TestFormで「faultDevice」「contractType」を受取る
	 *  ・モデル呼び出し：TestServiceクラスにチケット番号を引数に呼び出す
	 *  ・返却画面データ：チケット番号を設定する（ticketNumber）
	 *  ・画面：test_confirm.htmlを返す（試験確認画面）
	 */
	@PostMapping("/confirm")
	public String testConfirm(@ModelAttribute TestForm testForm, Model model) {
		int ticketNumber = _testService.testRequest(testForm);
		
		// 登録失敗時
		if(ticketNumber == 0) {
			return "test_request_fault";
		}
		
		model.addAttribute("ticketNumber", ticketNumber);
		return "test_confirm";
	}

	
	/**
	 * 実装概要
	 *  試験確認画面からアクセスされるメソッド
	 *  リクエストURLパスからデータを受取る
	 *  
	 * 実装内容
	 *  ・リクエスト：GETメソッドでURL「/result/{ticket_number}」で受け付ける
	 *  ・入力画面データ：ticketNumberで「ticket_number」を受取る
	 *  ・モデル呼び出し：TestServiceクラスにチケット番号を引数にして呼び出す
	 *  ・返却画面データ：試験結果データを設定する（TestResultDtoクラス）
	 *  ・画面：test_result.htmlを返す（試験結果画面）
	 */
	
	// 追加必要
	// GETメソッドで test_resultを返す
	@GetMapping("/result/{ticket_number}")
	public String testResult(@PathVariable("ticket_number") int ticketNumber, Model model) {
		TestResultDto testResultDto = _testService.testResult(ticketNumber);
		model.addAttribute("testResult", testResultDto);
		return "test_result";
	}

}
