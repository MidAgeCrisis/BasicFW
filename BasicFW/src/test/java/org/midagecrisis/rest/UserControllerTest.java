package org.midagecrisis.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UserControllerTest extends AbstractControllerTestSupport {

	@Test
	public void testGetPricePointWithBokuShouldWork() throws Exception {

    	String endpoint = "/api/v3/findUser?userId=1";
    	MvcResult result = mockMvc
    		.perform(get(endpoint))
    		.andExpect(status().isOk())
    		.andReturn();
    	String content = result.getResponse().getContentAsString();
    	Assert.assertTrue(content.contains("A"));
    }

}
