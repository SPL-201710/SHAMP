package controllers;

import play.mvc.*;

import views.html.*;

import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import forms.*;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
import models.*;
import Backend.*;

public class MessageController extends Controller  
{
	public Result adminMessages()
	{
		String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            if(tempUser.user_type == 2)
            {
            	List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            	List<Message> messagesResponsed = MessageAdmin.getResponsedMessages(tempUser);
            	List<Message> messagesUnResponse = MessageAdmin.getUnresponseMessages(tempUser);
            	return ok(adminMessages.render(messagesUnResponse,messagesResponsed,null,token,tempUser.user_first_name+" "+ tempUser.user_last_name,tempOptions,"adminMessages"));
            	
            	
            	
            }
            else
            {
            	return redirect("/");
            }
            
    
        }
        else
        {
            return redirect("/");
        }
	}
    
	public Result viewResponseMessage(long id)
	{
		String token = session("token");
        String userId = session("user_id");
        
        if(token != null)
        {
        	User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
        	if(tempUser.user_type == 2)
            {
        		ResponseMessageForm responseMessageForm = new ResponseMessageForm();
        		List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
        		Message tempMessage = MessageAdmin.getMessage(id);
        		responseMessageForm.message_from = tempUser.user_mail;
        		responseMessageForm.message_to = tempMessage.getUserFrom().user_mail;
        		responseMessageForm.message_subject = "Re: "+ tempMessage.message_subject;
        		
        		Form<ResponseMessageForm> tempForm = Form.form(ResponseMessageForm.class).fill(responseMessageForm);
        		
        		return ok(response_message.render(id,tempForm,null,token, tempUser.user_first_name,tempOptions,"adminMessages"));
        						
        		
            }
        	else
        	{
        		return redirect("/");
        	}
        	
        }
        
        return redirect("/");
	}
	
	
	public Result responseMessage(long id)
	{
		
		String token = session("token");
        String userId = session("user_id");
        
        if(token != null)
        {
        	User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            if(tempUser.user_type == 2)
            {
            	Form<ResponseMessageForm> responseMessageForm = Form.form(ResponseMessageForm.class).bindFromRequest();
            	String from = responseMessageForm.field("message_from").value();
            	String to = responseMessageForm.field("message_to").value();
            	String subject = responseMessageForm.field("message_subject").value();
            	String content =  responseMessageForm.field("message_content").value();
            	MessageAdmin.sendResponse(id, content);
            	List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            	List<Message> messagesResponsed = MessageAdmin.getResponsedMessages(tempUser);
            	List<Message> messagesUnResponse = MessageAdmin.getUnresponseMessages(tempUser);
            	return ok(adminMessages.render(messagesUnResponse,messagesResponsed,"Mensaje enviado con exito",token,tempUser.user_first_name+" "+ tempUser.user_last_name,tempOptions,"adminMessages"));
            }
            else
            {
            	return redirect("/");
            }
        }
        
        return redirect("/");
		
	}
	
	public Result deleteMessage(long id)
	{
		String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
        	User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
        	if(tempUser != null && tempUser.user_type == 2)
        	{
        		List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
        		MessageAdmin.deleteMessage(id);
        		List<Message> unresponseMessage = MessageAdmin.getUnresponseMessages(tempUser);
        		List<Message> responsedMessage = MessageAdmin.getResponsedMessages(tempUser);
        		
        		return ok(adminMessages.render(unresponseMessage,responsedMessage,"Mensaje eliminado con exito.",token,tempUser.user_first_name+" "+ tempUser.user_last_name,tempOptions,"adminMessages"));
        		
        	}
        }
       
        return redirect("/");

        
        
	}
	
	public Result viewUnresponsedMessage(long id)
	{
		String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
        	User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
        	if(tempUser.user_type == 2)
        	{
        		Message tempMessage = MessageAdmin.getMessage(id);
        		List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
        		
        		return ok(viewUnresponseMessage.render(tempMessage,token,tempUser.user_first_name,tempOptions,"viewMessageUnresponse"));
        	
        	}
        	else
        	{
        		return redirect("/");
        	}
        }
        
        return redirect("/");
	}
	
	public Result viewResponsedMessage(long id)
	{
		String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
        	User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
        	if(tempUser.user_type == 2)
        	{
        		Message tempMessage = MessageAdmin.getMessage(id);
        		Message tempMessageOrg =MessageAdmin.getMessage(tempMessage.parent_message);
        		List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
        		
        		return ok(viewResponsedMessage.render(tempMessageOrg,tempMessage,token,tempUser.user_first_name,tempOptions,"viewMessageUnresponse"));
        	
        	}
        	else
        	{
        		return redirect("/");
        	}
        }
        
        return redirect("/");
	}
}
