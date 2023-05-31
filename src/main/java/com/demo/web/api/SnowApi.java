package com.demo.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.web.dto.SnowApiRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SnowApi {

	@Value("${snow.rest.url}")
	private String _snowUrl;

	@Value("${snow.rest.user}")
	private String _snowUser;

	@Value("${snow.rest.pass}")
	private String _snowPass;

	/**
	 * API「SNOW連携」
	 * 
	 */
	public int execSNOWApi(SnowApiRequestDto sNOWApiRequestDto) {

		RestTemplate restTemplate = new RestTemplate();

		int statusCode = 0;

		try {
			// HttoHeader生成
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Basic認証情報
			headers.setBasicAuth(_snowUser, _snowPass);
			
			// DtoをJSONに変換しPOSTデータにセット
			ObjectMapper mapper = new ObjectMapper();
			String requestJson = mapper.writeValueAsString(sNOWApiRequestDto);

			// Basic認証情報とPOSTデータをHttpEntityに設定する
			HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);

			// API接続URLを設定
			String url_path = "/api/now/table/u_auto_test_113web";
			String url = _snowUrl + url_path;

			// 外部APIを実行する
			ResponseEntity<String> rtn = null;
			rtn = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

			statusCode = rtn.getStatusCodeValue();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());

		} finally {
			System.out.println("処理終了");

		}
		return statusCode;
	}
}