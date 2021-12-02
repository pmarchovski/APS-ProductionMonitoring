package com.mdrain.servletPrepare.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Translate {

	public static void translate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		

		String myKey = "AIzaSyD4r2wA9zIQvZtWYCtfdqNSDDcDaZProaw";
		
		// Instantiates a client
	    com.google.cloud.translate.Translate translate = TranslateOptions.newBuilder().setApiKey(myKey).build().getService();

	    // The text to translate
	    String text = req.getParameter("translator_input_text");

	    // Translates some text into Russian
	    Translation translation =
	        translate.translate(
	            text, TranslateOption.sourceLanguage("en"), TranslateOption.targetLanguage("ru"));

	    System.out.printf("Text: %s%n", text);
	    System.out.printf("Translation: %s%n", translation.getTranslatedText());
	    
	    session.setAttribute("translated_text", translation.getTranslatedText());
	    
	    resp.sendRedirect("translate.jsp");
	    
	  }
}
