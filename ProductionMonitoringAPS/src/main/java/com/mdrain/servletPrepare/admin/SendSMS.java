package com.mdrain.servletPrepare.admin;

import com.messente.ApiClient;
import com.messente.ApiException;
import com.messente.api.OmniMessageCreateSuccessResponse;
import com.messente.api.Omnimessage;
import com.messente.api.OmnimessageApi;
import com.messente.api.Viber;
import com.messente.auth.HttpBasicAuth;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viber.bot.Request;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;

import scala.Responder;

public class SendSMS {

	
	public static void bootstrapSendSms(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
     
		ApiClient apiClient = new ApiClient();

        HttpBasicAuth basicAuth = (HttpBasicAuth) apiClient.getAuthentication("basicAuth");
        basicAuth.setUsername("fba8d9d4ae6c4958a6145d2646192aa8");
        basicAuth.setPassword("133082cc3eb545bebc8e028ef18f2100");

        Viber viber = new Viber();
        viber.text("hello viber");
        viber.sender("Petar");
      

        OmnimessageApi apiInstance = new OmnimessageApi(apiClient);
        Omnimessage omnimessage = new Omnimessage();
        omnimessage.setMessages(Arrays.asList(viber));
        omnimessage.setTo("+359879135451");
        

        try {
            OmniMessageCreateSuccessResponse result = apiInstance.sendOmnimessage(omnimessage);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println(e.getResponseBody());
        }
		
        res.sendRedirect("send.jsp");
        
	}
	
	public static void botExample(HttpServletRequest req, HttpServletResponse res) {
	    ViberBot bot = new ViberBot(new BotProfile("SampleBot", "http://viber.com/avatar.jpg"), "4e55a77754a7e155-58d8e849708d2043-8e1492a9ac2c4bd");
	   
	     System.out.println(bot.getAccountInfo());
	    bot.onMessageReceived((event, message, response) -> response.send(message));
	    bot.setWebhook("petarmarchovski");
	   
	   

	    // somewhere else in your web server of choice:

	    bot.onTextMessage("(hi|hello)", (event, message, response) -> response.send("Hi " + event.getSender().getName()));
	   
	}
	
}
