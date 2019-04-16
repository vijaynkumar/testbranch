package com.seam.services.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.seam.messages.pojo.Message;
import com.seam.messages.utils.MapperUtils;
import com.seam.services.MessageReader;

@Service
public class MessageReaderImpl implements MessageReader {

	@Override
	public Message getLatestMessage(String subscriberId) {
		Message latestMessage = null;

		if (null == subscriberId) {
			return latestMessage;
		}

		try {
			// Read the message folder
			File folder = ResourceUtils.getFile("classpath:db/18-06-18");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {

				if (file.isFile()) {
					// Get the message node from the file
					JsonNode messageNode = JsonLoader.fromFile(file);

					// Convert the message node to the corresponding object
					Message message = MapperUtils.getMapper().convertValue(messageNode, Message.class);

					// Get the latest message
					if (message.getSenderId().equals(subscriberId)) {
						if (null == latestMessage
								|| (latestMessage.getCreatedAt().compareTo(message.getCreatedAt()) < 0)) {
							latestMessage = message;
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return latestMessage;
	}

	@Override
	public Message getFirstUnreadMessage(String subscriberId) {
		Message unreadMessage = null;

		if (null == subscriberId) {
			return unreadMessage;
		}

		try {
			// Read the message folder
			File folder = ResourceUtils.getFile("classpath:db/18-06-18");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {

				if (file.isFile()) {

					// Get the message node from the file
					JsonNode messageNode = JsonLoader.fromFile(file);

					// Convert the message node to the corresponding object
					Message message = MapperUtils.getMapper().convertValue(messageNode, Message.class);

					// Get first unread message
					// Not able to find unread because there is no base condition on which i can
					// find
					if (message.getSenderId().equals(subscriberId)) {
						if (null == unreadMessage) {
							unreadMessage = message;
						}

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return unreadMessage;
	}

}
