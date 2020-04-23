package com.sid.app.ws.http.feign.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sid.app.ws.shared.UserContactDto;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "contact-ws", fallbackFactory = UserContactClientFallbackFactory.class)
public interface UserContactClient {

	@GetMapping("/contacts/users/{userId}")
	public List<UserContactDto> getUserContacts(@PathVariable String userId);
}

@Component
class UserContactClientFallbackFactory implements FallbackFactory<UserContactClient> {

	@Override
	public UserContactClient create(Throwable cause) {
		return new UserContactClientFallback(cause);
	}

}

class UserContactClientFallback implements UserContactClient {

	private Throwable throwable;
	private Logger logger = Logger.getLogger(UserContactClientFallback.class.getName());

	public UserContactClientFallback(Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public List<UserContactDto> getUserContacts(String userId) {
		if (throwable instanceof Throwable) {
			logger.warning("Feign Exception ->>>>>> "+throwable.getMessage() +" "+throwable.getClass().getName());
		}
		return new ArrayList<>();
	}

}