package bpdts.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bpdts.model.User;

@Service
public class NameFileService {
	private static final Logger LOG = LoggerFactory.getLogger(NameFileService.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Value("classpath:names.json")
	private Resource namesFile;

	private List<User> users;

	@PostConstruct
	private void mapUserList() throws IOException {
		users = Collections.unmodifiableList(mapper.readerForListOf(User.class).readValue(namesFile.getFile()));
		LOG.info("Found users: " + users.size());
	}

	public List<User> getUserList() {
		return users;
	}
}
