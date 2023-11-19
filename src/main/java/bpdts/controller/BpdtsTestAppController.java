package bpdts.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import bpdts.model.User;

@RestController
@RequestMapping("bpdts-test-app")
public class BpdtsTestAppController {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private List<User> userList;

	@GetMapping(path = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
	public String instructions() {

		return "{\"todo\": \"Build an API which calls this API, and returns people who are listed as either living in \"\r\n"
				+ "                        \"London, or whose current coordinates are within 50 miles of London. Push the answer to Github,\"\r\n"
				+ "                        \" and send us a link.\"}";
	}

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ObjectNode> users() throws JsonProcessingException {
		List<ObjectNode> list = userList.stream().map(u -> {
			ObjectNode jsonNode = mapper.valueToTree(u);
			jsonNode.remove("city");
			return jsonNode;
		}).collect(Collectors.toList());

		return list;
	}

	@GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userById(@PathVariable(value = "id") Long id) {
		return userList.stream().filter(u -> u.getId() == id).findFirst()
				.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
				.orElse(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/city/{city}/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ObjectNode> usersByCity(@PathVariable(value = "city") String city) {
		return userList.stream().filter(u -> u.getCity().equals(city)).map(u -> {
			ObjectNode jsonNode = mapper.valueToTree(u);
			jsonNode.remove("city");
			return jsonNode;
		}).collect(Collectors.toList());
	}
}
