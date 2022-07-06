package com.ramz.soap.NotificatorService;

import com.ramz.soap.AllUsersList.AllUsersList;
import com.ramz.soap.Model.User;

import com.ramz.soap.SOAP.RouterServiceImplService;
import com.ramz.soap.TrackedChatId.TrackChatId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notificator {

	public static void main(String[] args) {

		RouterServiceImplService routerServiceImplService = new RouterServiceImplService();
		AllUsersList allUsersList = new AllUsersList();
		TrackChatId trackChatId = new TrackChatId();

		List<String> trackedChatIdList = trackChatId.getTrackedList();
		List<User> allUsers = allUsersList.getAllUsers();
		List<String> untrackedUsersNamesList = new ArrayList<>();

		Map<String, String> allChatIdMap = convertArrayListToHashMap(allUsers);

		for (Map.Entry<String, String> entry : allChatIdMap.entrySet()) {
			if (!trackedChatIdList.contains(entry.getKey())) {
				untrackedUsersNamesList.add(entry.getValue());
			}
		}

		String message = String.join("\n", untrackedUsersNamesList);
		routerServiceImplService.getRouterServiceImplPort().sendNotification(message);

		System.out.println(untrackedUsersNamesList);
	}

	private static HashMap<String, String> convertArrayListToHashMap(List<User> arrayList) {
		HashMap<String, String> hashMap = new HashMap<>();
		for (User user : arrayList) {
			hashMap.put(user.getChatId(), user.getFullName());
		}
		return hashMap;
	}
}
