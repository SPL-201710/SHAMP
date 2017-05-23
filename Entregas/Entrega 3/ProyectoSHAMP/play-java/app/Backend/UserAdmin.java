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
import play.Play;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.Random;
import dto.*;

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
    
    public static Session registerUser(CustomerDto user) throws Exception
    {
    	Session tempSession = new Session();
    	try
    	{
    		User tempUser = User.find.where().eq("user_mail", user.getEmail()).findUnique();
    		if(tempUser!= null)
    		{
    			throw new Exception ("Ya existe un usuario con ese email registrado");
    		}
    		else
    		{
    			tempUser = new User();
            	tempUser.active = true;
            	tempUser.creation_date = new Date();
            	tempUser.user_first_name = user.getUsername();
            	tempUser.user_last_name = user.getSurname();
            	tempUser.user_mail =user.getEmail();
            	tempUser.user_password = user.getPassword();
            	tempUser.base_datos = user.getBase_datos();
            	tempUser.user_status = 1;
            	tempUser.user_type = 1;
            	tempUser.username = user.getUsername();
            	tempUser.save();
            	
            	UserBilling tempBilling = new UserBilling();
            	
            	tempBilling.billing_status = 1;
            	tempBilling.cvv = user.getCvv();
            	Date tempExpirationDate= new SimpleDateFormat("dd/MM/yyyy").parse(user.getExpiration_date());
            	tempBilling.expiration_date = tempExpirationDate;
            	tempBilling.name_card = user.getName_card();
            	tempBilling.phone_number = user.getPhone_number();
            	tempBilling.user_address = user.getUser_address();
            	tempBilling.user_city = user.getCity();
            	tempBilling.user_country = user.getCountry();
            	tempBilling.user_credit_card = user.getUser_credit_card();
            	tempBilling.user_id = tempUser;
            	tempBilling.save();
            	
            	UserDto tempUserDto = new UserDto();
            	tempUserDto.setEmail(tempUser.user_mail);
            	tempUserDto.setId(tempUser.user_id);
            	tempUserDto.setName(tempUser.user_first_name);
            	tempUserDto.setSurname(tempUser.user_last_name);
            	tempUserDto.setUsername(tempUser.username);
            	
            	tempSession.setUser(tempUserDto);
            	tempSession.setUserBilling(tempBilling);
            
    		}
        	
        	
    	}
    	catch (Exception ex)
    	{
    		throw new Exception ("Se ha presentado un error al registrar el usuario: "+ex.getMessage());
    	}
    	
    	
    	
    	
    	return tempSession;
    }
    
    public static String disableUser(long id)
    {
        String response = "";
        User tempUser = User.find.where().eq("user_id",id).findUnique();
        if(tempUser != null)
        {
            tempUser.user_status =2;
            tempUser.active = false;
            tempUser.creation_date = new Date();
            tempUser.update();
            
            if(tempUser.user_type == 2)
            {
                List<Stamp> listStamp = Stamp.find.where().eq("user_id",tempUser).findList();
                for(int i = 0; i<listStamp.size();i++)
                {
                    Stamp tempStamp = listStamp.get(i);
                    tempStamp.active = false;
                    tempStamp.stamp_status =2;
                    tempStamp.creation_date = new Date();
                    tempStamp.update();
                }
            }
            
        }
        else
        {
            response = "El usuario no existe";    
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
            user.active = true;
            user.creation_date = new Date();
            user.name = signUpArtistForm.field("first_name").value() + signUpArtistForm.field("last_name").value();
            user.username = signUpArtistForm.field("mail").value();
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
            artistProfile.active = true;
            artistProfile.creation_date = new Date();
            artistProfile.name = signUpArtistForm.field("first_name").value() + signUpArtistForm.field("last_name").value();
            
            
            String accessKeyId = Play.application().configuration().getString("s3.key.id");
            String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
            String bucketName = Play.application().configuration().getString("s3.bucket.name");
            String profileImageFolder = Play.application().configuration().getString("s3.bucket.profileFolder");
            
            String fileName = profileImageFolder +"/"+user.user_id+"."+fileExtension;
                
            AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
            AmazonS3Client s3client = new AmazonS3Client(credentials);
            
            boolean fileExist = s3client.doesObjectExist(bucketName, fileName);
            if(fileExist)
            {
                s3client.deleteObject(bucketName, fileName);
            }
            
            s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
            
            String imageURL = s3client.getResourceUrl(bucketName, fileName);
            artistProfile.user_image_path = imageURL;
            artistProfile.save();
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    
    public static String updateProfile(Form<UpdateProfileForm> updateProfileForm, User tempUser, File tempFile, String fileExtension)
    {
        String response = "";
        try
        {
            ArtistProfile tempProfile = ArtistProfile.find.where().eq("user_id",tempUser).findUnique();
            if(tempProfile != null)
            {
                tempProfile.user_country = updateProfileForm.field("user_country").value();
                tempProfile.user_city = updateProfileForm.field("user_city").value();
                tempProfile.user_address = updateProfileForm.field("user_address").value();
                tempProfile.phone_number = updateProfileForm.field("phone_number").value();
                tempProfile.user_profile = updateProfileForm.field("user_profile").value();
                
                String accessKeyId = Play.application().configuration().getString("s3.key.id");
                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
                String bucketName = Play.application().configuration().getString("s3.bucket.name");
                String profileImageFolder = Play.application().configuration().getString("s3.bucket.profileFolder");
            
                String fileName = profileImageFolder +"/"+tempUser.user_id+"."+fileExtension;
                
                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
                AmazonS3Client s3client = new AmazonS3Client(credentials);
            
                boolean fileExist = s3client.doesObjectExist(bucketName, fileName);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileName);
                }
                Random rand = new Random();
                int  random = rand.nextInt(10000) + 1;
                fileName = profileImageFolder +"/"+tempUser.user_id+"_"+random+"."+fileExtension;
                s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				    tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
            
                String imageURL = s3client.getResourceUrl(bucketName, fileName);
                tempProfile.user_image_path = imageURL;
                tempProfile.save();
                
                
            }
            else
            {
                tempProfile = new ArtistProfile();
                tempProfile.user_id = tempUser;
                tempProfile.user_country = updateProfileForm.field("user_country").value();
                tempProfile.user_city = updateProfileForm.field("user_city").value();
                tempProfile.user_address = updateProfileForm.field("user_address").value();
                tempProfile.phone_number = updateProfileForm.field("phone_number").value();
                tempProfile.user_profile = updateProfileForm.field("user_profile").value();
                tempProfile.active = true;
                tempProfile.creation_date = new Date();
                tempProfile.name = tempUser.user_first_name + " " + tempUser.user_last_name;
                
                String accessKeyId = Play.application().configuration().getString("s3.key.id");
                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
                String bucketName = Play.application().configuration().getString("s3.bucket.name");
                String profileImageFolder = Play.application().configuration().getString("s3.bucket.profileFolder");
            
                String fileName = profileImageFolder +"/"+tempUser.user_id+"."+fileExtension;
                
                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
                AmazonS3Client s3client = new AmazonS3Client(credentials);
            
                boolean fileExist = s3client.doesObjectExist(bucketName, fileName);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileName);
                }
            
                s3client.putObject(new PutObjectRequest(bucketName, fileName, 
				    tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
            
                String imageURL = s3client.getResourceUrl(bucketName, fileName);
                tempProfile.user_image_path = imageURL;
                tempProfile.save();
                
                
            }
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
    
    public static Session loginUser(UserLogin userData) throws Exception
    {
        Session response = new Session();
        User tempUser = User.find.where().eq("user_mail", userData.getEmail() ).findUnique();
            
        if(tempUser != null)
        {
                if(tempUser.user_password.equals(userData.getPassword())&& tempUser.user_status == 1)
                {
                	
                	
                	UserBilling tempBilling = UserBilling.find.where().eq("user_id",tempUser).findUnique();
                
                	UserDto tempUserDto = new UserDto();
                	tempUserDto.setEmail(tempUser.user_mail);
                	tempUserDto.setId(tempUser.user_id);
                	tempUserDto.setName(tempUser.user_first_name);
                	tempUserDto.setSurname(tempUser.user_last_name);
                	tempUserDto.setUsername(tempUser.username);
                	tempUserDto.setBase_datos(tempUser.base_datos);
                	
                	response.setUser(tempUserDto);
                	response.setUserBilling(tempBilling);
                    
                }
                else
                {
                	throw new Exception("Usuario o Password Errado. Usuario: "+userData.getEmail() +" Password: "+ userData.getPassword());
                }
        }
        else
        {
                throw new Exception("Usuario no Existe.Usuario: "+userData.getEmail() +" Password: "+ userData.getPassword());
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
    
    public static Session changeUserPassword(long userId, ChangePasswordDto tempdata ) throws Exception
    {
    	
    	
        User tempUser = User.find.where().eq("user_id",userId).findUnique();
        if(tempUser != null)
        {
        	if(tempUser.user_password.equals(tempdata.getOldPassword()))
        	{
        		tempUser.updatePassword(tempdata.getNewPassword());
        		tempUser.update();
        		Session tempSession = new Session();
        		
        		UserBilling tempBilling = UserBilling.find.where().eq("user_id",tempUser).findUnique();
                
            	UserDto tempUserDto = new UserDto();
            	tempUserDto.setEmail(tempUser.user_mail);
            	tempUserDto.setId(tempUser.user_id);
            	tempUserDto.setName(tempUser.user_first_name);
            	tempUserDto.setSurname(tempUser.user_last_name);
            	tempUserDto.setUsername(tempUser.username);
            	
            	tempSession.setUser(tempUserDto);
            	tempSession.setUserBilling(tempBilling);
            	return tempSession;
        		
        	}
        	else
        	{
        		throw new Exception("El password anterior no coincide");
        	}
        }
        else
        {
        	throw new Exception("El usuario no existe");
        }
        
        
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