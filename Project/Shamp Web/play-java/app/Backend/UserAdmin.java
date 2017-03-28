package Backend;

import models.*;
import forms.*;
import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import java.text.SimpleDateFormat; 
import java.util.Date;  
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.*;

public final class UserAdmin{
    
    
    public static String registerUser(Form<SignUpForm> signUpForm)
    {
        String response = "";
        try
        {
            User user = new User();
            user.user_mail = signUpForm.field("mail").value();
            user.user_password = signUpForm.field("password").value();
            user.user_first_name = signUpForm.field("first_name").value();
            user.user_last_name = signUpForm.field("last_name").value();
            user.user_type = 1;
            user.user_status = 1;
            user.save();
            
            UserBilling userBilling = new UserBilling();
            userBilling.user_id = user;
            userBilling.user_country = signUpForm.field("user_country").value();
            userBilling.user_city = signUpForm.field("user_city").value();
            userBilling.user_address = signUpForm.field("user_address").value();
            userBilling.phone_number =  signUpForm.field("phone_number").value();
            userBilling.user_credit_card = signUpForm.field("credit_card").value();
            userBilling.name_card = signUpForm.field("name_card").value();
            Date tempExpirationDate= new SimpleDateFormat("dd/MM/yyyy").parse(signUpForm.field("expiration_date").value());
            userBilling.expiration_date = tempExpirationDate;
            userBilling.cvv = signUpForm.field("cvv").value();
            userBilling.billing_status = 1;
            userBilling.save();
            
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    public static String registerArtist(Form<SignUpArtistForm> signUpArtistForm, File tempFile,String fileExtension)
    {
        String response = "";
        try
        {
            User user = new User();
            user.user_mail = signUpArtistForm.field("mail").value();
            user.user_password = signUpArtistForm.field("password").value();
            user.user_first_name = signUpArtistForm.field("first_name").value();
            user.user_last_name = signUpArtistForm.field("last_name").value();
            user.user_type = 2;
            user.user_status = 1;
            user.save();
            
            ArtistProfile artistProfile = new ArtistProfile();
            artistProfile.profile_status = 1;
            artistProfile.user_id = user;
            artistProfile.user_country = signUpArtistForm.field("user_country").value();
            artistProfile.user_city = signUpArtistForm.field("user_city").value();
            artistProfile.user_address = signUpArtistForm.field("user_address").value();
            artistProfile.phone_number =  signUpArtistForm.field("phone_number").value();
            
            artistProfile.user_profile = signUpArtistForm.field("user_profile").value();
            artistProfile.user_image_path = "profileImage/"+user.user_id+"."+fileExtension;
            artistProfile.save();
            FileUtils.moveFile(tempFile, new File("public/profileImage", user.user_id+"."+fileExtension));
            
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    
    
    
    public static User loginUser(Form<SignInForm> signInForm)
    {
        User response;
        User tempUser = User.find.where().eq("user_mail", signInForm.field("mail").value()).findUnique();
            
            if(tempUser != null)
            {
                if(tempUser.user_password.equals(signInForm.field("password").value()) && tempUser.user_status == 1)
                {
                    
                    response = tempUser;
                }
                else
                {
                    response = null;
                }
            }
            else
            {
                response = null;
            }
            
        return response;
    }
    
    public static User getUser(String token,long userId)
    {
        User response = null;
        response = User.find.where().eq("user_id",userId).findUnique();
        return response;
        
    }
    
    
    public static List<UserOptions> getUserGeneralOptions(String token,long userId)
    {
        List<UserOptions> response = null;
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        if(tempUser != null)
        {
            int userType = tempUser.user_type;
            response = UserOptions.find.where().eq("user_type_option",userType).eq("user_object_option",1).findList();
        }
        
        return response;
        
    }
    
    public static List<UserOptions> getUserStampOptions(String token,long userId)
    {
        List<UserOptions> response = null;
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        if(tempUser != null)
        {
            int userType = tempUser.user_type;
            response = UserOptions.find.where().eq("user_type_option",userType).eq("user_object_option",2).findList();
        }
        
        return response;
        
    }
    
    public static List<UserOptions> getUserArtistOptions(String token,long userId)
    {
        List<UserOptions> response = null;
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        if(tempUser != null)
        {
            int userType = tempUser.user_type;
            response = UserOptions.find.where().eq("user_type_option",userType).eq("user_object_option",3).findList();
        }
        return response;
    }
    
    public static UserBilling getUserBilling(String token, User user)
    {
         List<UserBilling> userBilling = UserBilling.find.where().eq("user_id",user).eq("billing_status",1).findList();
         UserBilling tempBilling = null;
         if(userBilling.size()>0)
         {
             tempBilling = userBilling.get(0);
         }
         return tempBilling;
    }
    
    public static String changeUserPassword(String token, long userId, Form<ChangePasswordForm> changePasswordForm)
    {
        String response = "";
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        if(tempUser.user_password.equals(changePasswordForm.field("oldPassword").value()))
        {
            tempUser.updatePassword(changePasswordForm.field("newPassword").value());
            tempUser.update();
            
        }
        else
        {
            response = "El password actual no coincide";
        }
        
        return response;
        
    }
    
    public static String updateUserProfile(String token, long userId, Form<UpdateUserForm> updateUserForm)
    {
        String response = "";
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        //try
        //{
            List<UserBilling> userBilling2 = UserBilling.find.where().eq("user_id",tempUser).eq("billing_status",1).findList();
            userBilling2.get(0).billing_status = 2;
            userBilling2.get(0).update();
            
            UserBilling userBilling = new UserBilling();
            userBilling.user_id = tempUser;
            userBilling.billing_status = 1;
            Date tempExpirationDate =  null;
            try
            {
                tempExpirationDate= new SimpleDateFormat("dd/MM/yyyy").parse(updateUserForm.field("expiration_date").value());
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            
            userBilling.updateBilling(updateUserForm.field("user_country").value(),
            updateUserForm.field("user_city").value(),
            updateUserForm.field("phone_number").value(),
            updateUserForm.field("user_address").value(),
            updateUserForm.field("credit_card").value(),
            updateUserForm.field("name_card").value(),
            tempExpirationDate,
            updateUserForm.field("cvv").value()
            );
            
            userBilling.save();
        //}
        //catch (Exception ex)
        //{
            //response = ex.getMessage() + ex.getCause().toString() + ex.getStackTrace().toString();
        //}
        return response;
    }
    
    public void deleteUser()
    {
        
    }
    

}