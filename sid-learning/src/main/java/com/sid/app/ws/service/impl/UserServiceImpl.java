package com.sid.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sid.app.ws.data.UserEntity;
import com.sid.app.ws.data.UsersRepository;
import com.sid.app.ws.http.feign.client.UserContactClient;
import com.sid.app.ws.service.UserService;
import com.sid.app.ws.shared.UserContactDto;
import com.sid.app.ws.shared.UserDto;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserContactClient userContactClient;

	@Autowired
	private Environment env;

	@Override
	public UserDto createUser(UserDto userDto) {
		ModelMapper mapp = new ModelMapper();
		mapp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userDto.setUserId(UUID.randomUUID().toString());
		UserEntity userEntity = mapp.map(userDto, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userRepository.save(userEntity);
		return mapp.map(userEntity, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		ModelMapper mapp = new ModelMapper();
		mapp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = mapp.map(userEntity, UserDto.class);

		/**
		 * String contactServiceUrl =
		 * String.format(env.getProperty("contact.service.url"), userId);
		 * ResponseEntity<List<UserContactDto>> contactServiceResponse =
		 * restTemplate.exchange(contactServiceUrl, HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<UserContactDto>>() { });
		 **/

		logger.info("Calling user contacts API for user id " + userId);
		List<UserContactDto> contacts = userContactClient.getUserContacts(userId);
		userDto.setUserContacts(contacts);

		return userDto;
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(userEntity, UserDto.class);
	}
}