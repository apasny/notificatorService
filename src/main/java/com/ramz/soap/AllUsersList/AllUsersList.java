package com.ramz.soap.AllUsersList;

import java.util.ArrayList;
import java.util.List;

import com.ramz.soap.Model.User;
import com.ramz.soap.SOAP.Command;
import com.ramz.soap.SOAP.CommandImplService;
import com.ramz.soap.SOAP.SetOfUser;

public class AllUsersList {
	
	public List<User> getAllUsers() {
		
		CommandImplService command = new CommandImplService();
		Command command1 = command.getCommandImplPort();
		SetOfUser users = command1.getAllUsersAndGroups();
		List<com.ramz.soap.SOAP.User> userItems = users.getItem();
		List<User> userList = new ArrayList<>();

		for (com.ramz.soap.SOAP.User user : userItems) {
			userList.add(new User(user.getChatId(), user.getFullName(), user.getGroup()));
		}

		return userList;
	}
}
