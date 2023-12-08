package bpdts.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bpdts.model.User;

@Service
public class NameFileService {
	private static final Logger LOG = LoggerFactory.getLogger(NameFileService.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ApplicationContext appContext;

	// @Value("classpath:names.json")
	// private Resource namesFile;

	private List<User> users;

	@PostConstruct
	private void mapUserList() throws IOException {
		Resource namesFile = appContext.getResource("classpath:names.json");

		users = Collections.unmodifiableList(mapper.readerForListOf(User.class).readValue(namesFile.getFile()));
		LOG.info("Found users: " + users.size());
	}

	public List<User> getUserList() {
		return users;
	}
}
