package bpdts.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NameFileServiceTest {

	@Autowired
	private NameFileService nameFileService;

	@Test
	void test() {
		assertNotNull(nameFileService.getUserList());
		assertNotEquals(0, nameFileService.getUserList().size());
	}

}
