package com.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.web.dto.TestResultDto;
import com.demo.web.service.SnowService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * ServiceNowからREST APIを受け付けるクラス
 */

@RestController
public class SnowController {

	@Autowired
	SnowService _snowService;
	
	// クエリパラメータでチケット番号を受取る
	/**
	 * 実装概要
	 *  ServiceNowからアクセスされるメソッド
	 *  クエリパラメータでデータを受取る
	 *  
	 * 実装内容
	 *  ・リクエスト：GETメソッドでURL「/snow_result」で受け付ける
	 *  ・入力画面データ：ticketNumberで「=?ticketNumber」を受取る
	 *  ・モデル呼び出し：SnowServiceクラスにチケット番号を引数に呼び出す
	 *  ・返却データ変換：DtoクラスをJSONに変換する
	 *  ・返却データ：チケット番号を設定する（ticketNumber）
	 *  ・画面：test_confirm.htmlを返す（試験確認画面）
	 */
	@GetMapping("/snow_result")
	public String snow(@RequestParam int ticketNumber) {

		// レスポンスデータ用の変数
		String responseJson = "";

		// 例外処理開始
		try {

			// 試験結果を取得
			TestResultDto TestResultDto = _snowService.testResult(ticketNumber);

			// DtoをJSONに変換
			ObjectMapper mapper = new ObjectMapper();
			responseJson = mapper.writeValueAsString(TestResultDto);

			// 例外発生時
		} catch (Exception e) {
			// エラーメッセージをコンソール出力
			System.out.println(e.getMessage());

			// try処理終了時 
		} finally {
			// 処理終了をコンソール出力
			System.out.println("snow終了");
		}

		// JSONデータを返す
		return responseJson;
	}

}
