package com.playlife.unitTest;

import java.net.URL;

import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.annotation.DependsOn;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class User_Register_Server{
	public WebClient webClient;
	public WebRequest webRequest;

	@Before
	public void before() throws Exception{
		webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
		webRequest = new WebRequest(new URL("http://playlife.no-ip.org:8080/PlayTheLife/register"), HttpMethod.GET);
	}
	
	@After
	public void after() throws Exception{
		webClient.closeAllWindows();
	}
	
	private void test_Main(String email, String password, String username, String expectedResult) throws Exception {
		HtmlPage page = webClient.getPage(webRequest);
		HtmlForm form = page.getFormByName("form_register");
		form.getInputByName("email").setValueAttribute(email);
		form.getInputByName("password").setValueAttribute(password);
		form.getInputByName("username").setValueAttribute(username);
		HtmlPage user_result = form.getElementById("bn_submit").click();
		JSONObject obj = JSONObject.fromObject(user_result.getWebResponse().getContentAsString().toString());
		assertEquals(obj.get("status").toString(), expectedResult);
	}
	
	@Test
	public void test_RegisterCorrect() throws Exception {
		test_Main("test@test.com", "12345", "", "ok");
	}
	
	@DependsOn(value="test_RegisterCorrect")
	@Test
	public void test_RegisterWrong_duplicate() throws Exception {
		test_Main("test1@test.com", "12345", "", "ok");
		test_Main("test1@test.com", "12345", "", "error");
	}
	
	@Test
	public void test_RegisterWrong_email() throws Exception {
		test_Main("testtest.com", "12345", "", "error");
	}
	
	@Test
	public void test_RegisterWrong_email1() throws Exception {
		test_Main("@.", "12345", "", "error");
	}
	
	@Test
	public void test_RegisterWrong_password() throws Exception {
		test_Main("test123@test.com", "1234", "", "error");
	}
	
	@Test
	public void test_RegisterWithUsername() throws Exception {
		test_Main("anothertest@test.com", "12345", "username", "ok");
	}
	
}
