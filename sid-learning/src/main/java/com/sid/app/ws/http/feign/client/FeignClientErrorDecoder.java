package com.sid.app.ws.http.feign.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		System.out.println(">>>>>>>>>>>>>"+methodKey+" >>"+response.status());
		switch (response.status()) {
		case 404:
			if (methodKey.contains("getUserContacts"))
				return new ResponseStatusException(HttpStatus.NOT_FOUND, response.reason());
			break;

		default:
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
		}

		return new Exception("Internal Error");
	}

}
