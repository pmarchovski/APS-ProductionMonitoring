package com.mdrain.logic;

import java.util.ArrayList;

import com.mdrain.objects.Users;

public class PictureGenerate {

	public static String returnPictureOK() {

		String pictureQuery = "<img src=" + "\"pictures/" + "pngOkResized.png\"" + ">";

		return pictureQuery;
	}

	public static String returnPictureNOK() {

		String pictureQuery = "<img src=" + "\"pictures/" + "pngNok_resized.png\"" + ">";

		return pictureQuery;
	}

	public static String returnPictureAttention() {

		String pictureQuery = "<img src=" + "\"pictures/" + "png_attention_resized.png\"" + ">";

		return pictureQuery;
	}
	
	public static String returnPictureAttentionSecond() {

		String pictureQuery = "<img src=" + "\"pictures/" + "png_attention_second_resized.png\"" + ">";

		return pictureQuery;
	}
	
	
	public static String returnAvatar(String avatar) {

		//<img src="img_avatar2.png" class="avatar">
		
		//
		
		String pictureQuery = "<img src=" + "\"pictures/avatars/" + avatar + "\"" + "class=" + "\"avatar\"" + ">";

		return pictureQuery;
	}
	
	public static String getAvatar(String ownerName) {
		
		ArrayList<Users> usersCollection = SetObjectInfo.getUsersFromDataBase();
		String userAvatar = "";
		
		for (int i = 0; i < usersCollection.size(); i++) {
			if (ownerName.equals(usersCollection.get(i).getFullName())) {
				userAvatar = usersCollection.get(i).getAvatar();
			}
		}
		
		userAvatar = PictureGenerate.returnAvatar(userAvatar);
		
		return userAvatar;
	}
	
	
}
