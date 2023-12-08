package bpdts.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bpdts.model.User;

@Service
public class NameFileService {
	private static final Logger LOG = LoggerFactory.getLogger(NameFileService.class);

	private ObjectMapper mapper = new ObjectMapper();

	private List<User> users;

	@PostConstruct
	private void mapUserList() throws IOException {
		File namesFile = new ClassPathResource("names.json").getFile();

		users = Collections.unmodifiableList(mapper.readerForListOf(User.class).readValue(namesFile));
		LOG.info("Found users: " + users.size());
	}

	public List<User> getUserList() {
		return users;
	}
}
