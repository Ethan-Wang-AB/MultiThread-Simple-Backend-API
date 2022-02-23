package com.livebarn.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livebarn.demo.domains.Success;
import com.livebarn.demo.services.FetchData;
import com.livebarn.demo.services.FetchDataPoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LivebarnApplicationTests {
    @Autowired
    private FetchData fetchData;
    
    @Autowired
    private Success success;
    
    @Autowired
    private ObjectMapper objectMapper;
    

	@Test
	void contextLoads() {
            
            assertThat(fetchData).isNotNull();
            assertThat(success).isNotNull();
            assertThat(objectMapper).isNotNull();
	}

}
