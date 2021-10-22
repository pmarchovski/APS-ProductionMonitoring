package com.mdrain.servletPrepare.printers_production;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mdrain.logic.SetObjectInfo;
import com.mdrain.logic.Tables;
import com.mdrain.objects.Wardrobe;

public class WardrobController {

	
	public static void displayEmptyWardrob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArrayList<Wardrobe> wardrobeCollection = SetObjectInfo.getWardrobeInfoCollectionFromDatabase();
		ArrayList<String> freeWardrobe = new ArrayList<String>();
		ArrayList<String> freeWardrobeHead = new ArrayList<String>();
		HttpSession session = req.getSession();
		Tables table = new Tables();
		
		for (int i = 0; i < wardrobeCollection.size(); i++) {
			
			if (wardrobeCollection.get(i).getWardrobeName().equals("")) {
				freeWardrobe.add(wardrobeCollection.get(i).getWardrobeNumber());
				freeWardrobeHead.add("Номер гардеробче");
			}
		}	
		
		String tableHead = table.createTableHead(freeWardrobeHead);
		String tableBody = table.createTableString(freeWardrobe);
		session.setAttribute("wardrobe_display_free_body", tableBody);
		session.setAttribute("wardrobe_display_free_head", tableHead);
		
		req.getRequestDispatcher("wardrob_info.jsp").forward(req, resp);
	}
}
