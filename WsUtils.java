package model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dao.MessageBean;
import dao.UserBean;

public class WsUtils {
	public static final String URL_SERVER = "http://192.168.20.22:8080/chatWebServiceServer/rest/MonChatWebService";
	public static final String URL_SEND_MESSAGE = URL_SERVER + "/sendMessage";
	public static final String URL_GET_MESSAGES = URL_SERVER + "/getMessages";
	public static final String URL_ADD_USER = URL_SERVER + "/addUser";
	public static final String URL_GET_USERS = URL_SERVER + "/getUsers";
	public static final Gson GSON = new Gson();

	// *******************************************************************************
	// Envoie le message au Web Service @sendMessage et affiche la r�ponse de la
	// requete ds la console
	// ********************************************************************************
	public static void sendMessage(MessageBean message) throws Exception {
		// on parse l'objet "Message" en json ( type String)
		String messageJson = GSON.toJson(message);
		// on fait la requ�te en Post et on capte l'objet "Reponse"
		String jsonReqResult = OkHttpUtils.sendPostOkHttpRequest(URL_SEND_MESSAGE, messageJson);
		System.out.println(jsonReqResult);

	}

	// *******************************************************************************
	// R�cup�re et retourne tous les Messages via le Web Service @getMessages
	// ********************************************************************************
	public static ArrayList<MessageBean> getMessages() throws Exception {
		// on fait la requ�te pour r�cup�rer le Json contenant la liste de tous
		// les messages
		String jsonArrayListMessages = OkHttpUtils.sendGetOkHttpRequest(URL_GET_MESSAGES);
		System.out.println("json de l'ArrayList de tous les messages, re�u : " + jsonArrayListMessages);
		// on parse le json retourn� par la requ�te en ArrayList d'objets
		// "Message" (! on ajoute un typage � la classe des Type autoris�s en
		// 2nd param�tre de la methode "fromJson"
		ArrayList<MessageBean> messagesArrayList = GSON.fromJson(jsonArrayListMessages,
				new TypeToken<ArrayList<MessageBean>>() {
				}.getType());
		// on retourne la liste des objets "Messages"
		return messagesArrayList;
	}

	// *******************************************************************************
	// Envoie le user au Web Service /addUser et affiche la r�ponse de la
	// requete ds la console
	// ********************************************************************************
	public static void addUser(UserBean user) throws Exception {
		String userJson = GSON.toJson(user);
		String jsonReqResult = OkHttpUtils.sendPostOkHttpRequest(URL_ADD_USER, userJson);
		System.out.println(jsonReqResult);
	}

	// *******************************************************************************
	// R�cup�re et retourne tous les Users via le Web Service @getUsers
	// ********************************************************************************
	public static ArrayList<UserBean> getUsers() throws Exception {
		String jsonArrayListUsers = OkHttpUtils.sendGetOkHttpRequest(URL_GET_USERS);
		System.out.println("json de l'ArrayList de tous les users, re�u : " + jsonArrayListUsers);
		ArrayList<UserBean> usersArrayList = GSON.fromJson(jsonArrayListUsers, new TypeToken<ArrayList<UserBean>>() {
		}.getType());
		return usersArrayList;
	}
}
