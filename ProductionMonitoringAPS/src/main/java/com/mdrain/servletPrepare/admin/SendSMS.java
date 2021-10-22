package com.mdrain.servletPrepare.admin;

import com.viber.bot.Request;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;



public class SendSMS {

	
	public static void bootstrapSendSms() {
		
     
		 ViberBot bot = new ViberBot(new BotProfile("SampleBot", "http://viber.com/avatar.jpg"), "YOUR_AUTH_TOKEN_HERE");
		    bot.onMessageReceived((event, message, response) -> response.send(message));

		    // somewhere else in your web server of choice:
		    bot.incoming(Request.fromJsonString("..."));
		
	}
	
}
