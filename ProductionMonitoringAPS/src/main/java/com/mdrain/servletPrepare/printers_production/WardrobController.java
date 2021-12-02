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
		
		HttpSession session = req.getSession();
		session.removeAttribute("wardrobe_display_free_body");
		session.removeAttribute("wardrobe_display_free_head");
			
		ArrayList<Wardrobe> wardrobeCollection = SetObjectInfo.getWardrobeInfoCollectionFromDatabase();
		ArrayList<String> freeWardrobe         = new ArrayList<String>();
		ArrayList<String> freeWardrobeHead     = new ArrayList<String>();
		Tables tables                          = new Tables();
		
		for (int i = 0; i < wardrobeCollection.size(); i++) {
			
			if (wardrobeCollection.get(i).getWardrobeName().equals("")) {
				freeWardrobe.add(wardrobeCollection.get(i).getWardrobeNumber());
				freeWardrobeHead.add("Номер гардеробче");
			}
		}	
		
		String tableHead = tables.createTableHead(freeWardrobeHead);
		String tableBody = tables.createTableString(freeWardrobe);
		session.setAttribute("wardrobe_display_free_body", tableBody);
		session.setAttribute("wardrobe_display_free_head", tableHead);
		
		req.getRequestDispatcher("wardrob_info.jsp").forward(req, resp);
	}
	
	
	public static void displayWardrobInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session                      = req.getSession();
		session.removeAttribute("wardrobe_display_free_body");
		session.removeAttribute("wardrobe_display_free_head");
		Tables tables                            = new Tables();
		
		ArrayList<Wardrobe> wardrobeCollection   = SetObjectInfo.getWardrobeInfoCollectionFromDatabase();
		ArrayList<Object> wardrobeInfoCollection = new ArrayList<Object>();
		ArrayList<String> wardrobeInfoHead       = new ArrayList<String>();
		
		wardrobeInfoHead.add("Номер на гардеробче");
		wardrobeInfoHead.add("Име на ползвател");
		
		for (int i = 0; i < wardrobeCollection.size(); i++) {
			wardrobeInfoCollection.add(wardrobeCollection.get(i).getWardrobeNumber());
			wardrobeInfoCollection.add(wardrobeCollection.get(i).getWardrobeName());
		}
		
		String tableHead = tables.createTableHead(wardrobeInfoHead);
		String tableBody = tables.createTableBodyStringObject(wardrobeInfoHead, wardrobeInfoCollection);
		
		session.setAttribute("wardrobe_display_free_body", tableBody);
		session.setAttribute("wardrobe_display_free_head", tableHead);
		
		req.getRequestDispatcher("wardrob_info.jsp").forward(req, resp);
		
	}
}
