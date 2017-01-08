package org.midagecrisis.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:**/applicationContext-mvc.xml"})
@Test
public abstract class AbstractControllerTestSupport extends AbstractTestNGSpringContextTests {

    protected static MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @BeforeClass
    public void superBeforeMethod() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	/**
	 * do get method.
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String doGet(String url) throws Exception{
		logger.debug("Get url:" + url);
		MockHttpServletRequestBuilder httpBuilder = MockMvcRequestBuilders.get(url).characterEncoding("UTF-8");
		httpBuilder.header("X-Live-Gamer-RequestId", UUID.randomUUID().toString());
		httpBuilder.header("X-Live-Gamer-CallerSystemId", "v3");
		
		MvcResult result = mockMvc
				.perform(httpBuilder)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		String content = result.getResponse().getContentAsString();
		logger.debug("Get response:" + content);
		return content;
	}
	
	/**
	 * do post method.
	 * @param payload
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String doPost(String payload, String url) throws Exception{

		logger.debug("Post url:" + url+", content:"+ payload);
		MockHttpServletRequestBuilder httpBuilder = post(url).content(payload).characterEncoding("UTF-8");
		if(payload.startsWith("{") || payload.startsWith("[")){
			httpBuilder = httpBuilder.contentType(MediaType.APPLICATION_JSON);
		}else{
			httpBuilder = httpBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED);
		}
		httpBuilder.header("X-Live-Gamer-RequestId", UUID.randomUUID().toString());
		httpBuilder.header("X-Live-Gamer-CallerSystemId", "v3");
		
		MvcResult addResult = mockMvc
				.perform(httpBuilder)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		String content = addResult.getResponse().getContentAsString();
		logger.debug("Post response:" + content);

		return content;
	}

}
