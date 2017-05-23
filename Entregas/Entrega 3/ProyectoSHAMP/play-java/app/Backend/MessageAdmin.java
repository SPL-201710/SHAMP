package Backend;

import models.*;
import forms.*;
import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import play.Play;
import java.text.SimpleDateFormat; 
import java.util.Date;  
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.*;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import dto.MessageCreationDto;
import dto.MessageDto;

import java.util.Random;

public final class MessageAdmin 
{
	public static List<Message> getUnresponseMessages(User user_to)
	{
		List<Message> messages = null;
		
		messages = Message.find.where().eq("message_to", user_to.user_id).eq("message_status", 1).findList();
			
		return messages;
	}
	
	public static List<Message> getResponsedMessages(User user_to)
	{
		List<Message> messages = null;
		
		messages = Message.find.where().eq("message_from", user_to.user_id).eq("message_status", 1).findList();
		
		return messages;
	}
	
	public static List<MessageDto> getUnresponsedMessages(long user_id) throws Exception
	{
		User tempUser =  User.find.where().eq("user_id", user_id).findUnique();
		if(tempUser != null)
		{
			List<Message> messages =  Message.find.where().eq("message_to", tempUser.user_id).eq("message_status", 1).findList();
			List<MessageDto> response = new ArrayList<MessageDto>();
			for(int i = 0; i< messages.size();i++)
			{
				MessageDto tempDto = new MessageDto();
				tempDto.setCreation_date(messages.get(i).creation_date);
				tempDto.setMessage_content(messages.get(i).message_content);
				User tempFrom = User.find.where().eq("user_id", messages.get(i).message_from).findUnique();
				tempDto.setMessage_from(tempFrom.user_mail);
				tempDto.setMessage_id(messages.get(i).message_id);
				tempDto.setMessage_subject(messages.get(i).message_subject);
				User tempTo =  User.find.where().eq("user_id", messages.get(i).message_to).findUnique();
				tempDto.setMessage_to(tempTo.user_mail);
				tempDto.setParent_message(messages.get(i).parent_message);
				response.add(tempDto);
				
			}
			return response;
		}
		else
		{
			throw new Exception("Datos de sesion errados");
		}
	}
	
	public static void sendMessage(long user_from, long user_to, String subject, String content)
	{
		User tempUserFrom = User.find.where().eq("user_id", user_to).findUnique();
		User tempUserTo = User.find.where().eq("user_id", user_to).findUnique();
		
		Message tempMessage = new Message();
		tempMessage.message_status = 1;
		tempMessage.message_from = tempUserFrom.user_id;
		tempMessage.message_to = tempUserTo.user_id;
		tempMessage.creation_date = new Date();
		tempMessage.message_content = content;
		tempMessage.message_subject = subject;
		tempMessage.parent_message = -1;
		tempMessage.save();
	}
	
	public static MessageDto sendMessage(MessageCreationDto tempCreation, long user_id) throws Exception
	{
		User tempUser = User.find.where().eq("user_id", user_id).findUnique();
		if(tempUser != null)
		{
			Message tempMessage = new Message();
			tempMessage.creation_date =  new Date();
			tempMessage.message_content = tempCreation.getMessage_content();
			tempMessage.message_from = tempUser.user_id;
			tempMessage.message_status = 1;
			tempMessage.message_subject = tempCreation.getMessage_subject();
			User tempTo = User.find.where().eq("user_mail", tempCreation.getMessage_to()).findUnique();
			tempMessage.message_to = tempTo.user_id;
			tempMessage.parent_message = tempCreation.getParent_message();
			tempMessage.save();
			
			if(tempMessage.parent_message != -1)
			{
				Message parentMessage =  Message.find.where().eq("message_id", tempMessage.parent_message).findUnique();
				parentMessage.message_status = 2;
				parentMessage.update();
				
			}
			
			MessageDto tempDto = new MessageDto();
			tempDto.setCreation_date(tempMessage.creation_date);
			tempDto.setMessage_content(tempMessage.message_content);
			User tempFrom = User.find.where().eq("user_id",tempUser.user_id).findUnique();
			tempDto.setMessage_from(tempFrom.user_mail);
			tempDto.setMessage_to(tempTo.user_mail);
			tempDto.setMessage_id(tempMessage.message_id);
			tempDto.setMessage_subject(tempMessage.message_subject);
			tempDto.setParent_message(tempMessage.parent_message);
			
			
			
			return tempDto;
			
		}
		else
		{
			throw new Exception("Error de credenciales de acceso");
		}
		
	}
	
	public static Message getMessage(long id)
	{
		Message tempMessage = Message.find.where().eq("message_id", id).findUnique();
		return tempMessage;
	}
	
	public static void deleteMessage(long message_id)
	{
		Message tempMessage = MessageAdmin.getMessage(message_id);
		if(tempMessage != null)
		{
			tempMessage.message_status = 3;
			tempMessage.update();
		}
		
	}
	
	public static void sendResponse(long message_id, String content)
	{
		
		Message tempMessageOrginal = Message.find.where().eq("message_id", message_id).findUnique();
		
		
		Message tempMessage = new Message();
		tempMessage.message_status = 1;
		tempMessage.message_from = tempMessageOrginal.message_to;
		tempMessage.message_to = tempMessageOrginal.message_from;
		tempMessage.creation_date = new Date();
		tempMessage.message_content = content;
		tempMessage.message_subject = "Re:" + tempMessageOrginal.message_subject;
		tempMessage.parent_message = tempMessageOrginal.message_id;
		tempMessage.save();
		
		tempMessageOrginal.message_status = 2;
		tempMessageOrginal.update();
	}
}
