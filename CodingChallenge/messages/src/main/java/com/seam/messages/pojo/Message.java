package com.seam.messages.pojo;

import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The message object class
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Message {

	public String senderId;

	public String body;
	
	@JsonDeserialize(using = DateHandler.class)
	public Date createdAt;

	public JsonNode readAt; // Unread Message will have value as 0 and the key is the reciever id

}
