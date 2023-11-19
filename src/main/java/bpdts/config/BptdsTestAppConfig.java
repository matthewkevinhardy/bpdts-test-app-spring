package bpdts.config;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import bpdts.model.User;

@Configuration
public class BptdsTestAppConfig {

	private static final Logger LOG = LoggerFactory.getLogger(BptdsTestAppConfig.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Value("classpath:names.json")
	private Resource namesFile;

	@Bean
	public List<User> userList() throws Exception {
		List<User> users = Collections
				.unmodifiableList(mapper.readerForListOf(User.class).readValue(namesFile.getFile()));
		LOG.info("Found users: " + users.size());
		return users;
	}

}