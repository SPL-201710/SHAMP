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
}
