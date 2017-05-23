package Backend;

import models.*;
import forms.*;
import imageProcessing.ImageProcessor;
import play.data.DynamicForm.*;
import play.data.Form.*;
import wrappers.StampWrapper;
import play.data.*;
import play.Play;
import java.text.SimpleDateFormat; 
import java.util.Date;  
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.nio.file.Files;
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

import Interfaces.IStampDto;
import dto.CreateRatingDto;
import dto.RatingDto;
import dto.StampBasicDto;

import java.util.Random;


public final class StampAdmin
{
    public static String createStamp(Form<CreateStampForm> createStampForm, 
    String token,
    long userId,
    File smallImage ,String smallImageExtension,
    File largeImage, String largeImageExtension)  
    {
        String response = "";
        try
        {
            Categories tempCategory = Categories.find.where().eq("category_name",createStampForm.field("stamp_category").value()).findUnique();
            User tempUser = User.find.where().eq("user_id",userId).findUnique();
            if(tempCategory != null && tempUser != null)
            {
                Stamp tempStamp = new Stamp();
                tempStamp.stamp_status = 1;
                tempStamp.user_id = tempUser;
                tempStamp.category_id = tempCategory;
                tempStamp.stamp_name = createStampForm.field("stamp_name").value();
                tempStamp.stamp_short_description = createStampForm.field("stamp_short_description").value();
                tempStamp.stamp_long_description = createStampForm.field("stamp_long_description").value();
                double tempPrice = Double.parseDouble(createStampForm.field("stamp_price").value());
                tempStamp.stamp_price = tempPrice;
                tempStamp.name = createStampForm.field("stamp_name").value();
                tempStamp.active = true;
                tempStamp.creation_date = new Date();
                tempStamp.stamp_buyer="Publica";
                tempStamp.save();
                
                String accessKeyId = Play.application().configuration().getString("s3.key.id");
                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
                String bucketName = Play.application().configuration().getString("s3.bucket.name");
                String smallImageFolder = Play.application().configuration().getString("s3.bucket.smallImage");
                String largeImageFolder = Play.application().configuration().getString("s3.bucket.largeImage");
                String negativeImageFolder = Play.application().configuration().getString("s3.bucket.negative");
                String blackWhiteImageFolder = Play.application().configuration().getString("s3.bucket.blackwhite");
                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
                AmazonS3Client s3client = new AmazonS3Client(credentials);
                
                String fileNameSmall = smallImageFolder +"/"+tempStamp.stamp_id+"."+smallImageExtension;
                String fileNameLarge = largeImageFolder +"/"+tempStamp.stamp_id+"."+largeImageExtension;
                String negativeFile = negativeImageFolder+"/"+tempStamp.stamp_id+"."+smallImageExtension;
                String blackWhiteFile = blackWhiteImageFolder+"/"+tempStamp.stamp_id+"."+smallImageExtension;
                String value = Play.application().configuration().getString("feature.filters");
                boolean filters = false;
                if(value != null)
                {
                	if(value.equals("true"))
                	{
                		filters = true;
                	}
                }
                
                boolean fileExist = s3client.doesObjectExist(bucketName, fileNameSmall);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileNameSmall);
                }
                fileExist = s3client.doesObjectExist(bucketName, fileNameLarge);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileNameLarge);
                }
                
                if(filters)
                {
                	fileExist = s3client.doesObjectExist(bucketName, negativeFile);
                	if(fileExist)
                    {
                        s3client.deleteObject(bucketName, negativeFile);
                    }
                	fileExist = s3client.doesObjectExist(bucketName, blackWhiteFile);
                	if(fileExist)
                    {
                        s3client.deleteObject(bucketName, blackWhiteFile);
                    }
                	File tempFile = new File("./Negative");
                	if(!tempFile.exists()){
                		tempFile.mkdir();
                	}
                	File blackWhiteFilterFile = ImageProcessor.applyBlackandWhite(smallImage, "./Negative/"+tempStamp.stamp_id+"."+smallImageExtension);
                	s3client.putObject(new PutObjectRequest(bucketName, blackWhiteFile, 
                			blackWhiteFilterFile).withCannedAcl(CannedAccessControlList.PublicRead));
                	
                	tempFile = new File("./BlackWhite");
                	if(!tempFile.exists()){
                		tempFile.mkdir();
                	}
                	
                	File negativeFilterFile = ImageProcessor.applyNegative(smallImage, "./BlackWhite/"+tempStamp.stamp_id+"."+smallImageExtension);
                	s3client.putObject(new PutObjectRequest(bucketName, negativeFile, 
                			negativeFilterFile).withCannedAcl(CannedAccessControlList.PublicRead));
                	tempStamp.setStamp_blackwhite(s3client.getResourceUrl(bucketName, blackWhiteFile));
                	tempStamp.setStamp_negative(s3client.getResourceUrl(bucketName, negativeFile));
                	
              
                	tempFile = new File("./Negative/"+tempStamp.stamp_id+"."+smallImageExtension);
                	tempFile.delete();
                	
                	tempFile = new File("./BlackWhite/"+tempStamp.stamp_id+"."+smallImageExtension);
                	tempFile.delete();
                	
                }
                
                s3client.putObject(new PutObjectRequest(bucketName, fileNameSmall, 
				smallImage).withCannedAcl(CannedAccessControlList.PublicRead));
				
				s3client.putObject(new PutObjectRequest(bucketName, fileNameLarge, 
				largeImage).withCannedAcl(CannedAccessControlList.PublicRead));
				
				String smallURL = s3client.getResourceUrl(bucketName, fileNameSmall);
				String largeURL = s3client.getResourceUrl(bucketName, fileNameLarge);
				
				tempStamp.setStamp_small_image_path(smallURL);  
                tempStamp.setStamp_large_image_path(largeURL); 
                
                tempStamp.update();
                
            }
            else
            {
                response ="La categoria seleccionada no es valida. " +createStampForm.field("stamp_categoty").value() ;
            }
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    public static String createPrivateStamp(Form<CreateStampPrivateForm> createStampForm, 
    	    String token,
    	    long userId,
    	    File smallImage ,String smallImageExtension,
    	    File largeImage, String largeImageExtension)  
    	    {
    	        String response = "";
    	        try
    	        {
    	            Categories tempCategory = Categories.find.where().eq("category_name",createStampForm.field("stamp_category").value()).findUnique();
    	            User tempUser = User.find.where().eq("user_id",userId).findUnique();
    	            if(tempCategory != null && tempUser != null)
    	            {
    	                Stamp tempStamp = new Stamp();
    	                tempStamp.stamp_status = 1;
    	                tempStamp.user_id = tempUser;
    	                tempStamp.category_id = tempCategory;
    	                tempStamp.stamp_name = createStampForm.field("stamp_name").value();
    	                tempStamp.stamp_short_description = createStampForm.field("stamp_short_description").value();
    	                tempStamp.stamp_long_description = createStampForm.field("stamp_long_description").value();
    	                double tempPrice = Double.parseDouble(createStampForm.field("stamp_price").value());
    	                tempStamp.stamp_price = tempPrice;
    	                tempStamp.name = createStampForm.field("stamp_name").value();
    	                tempStamp.active = true;
    	                tempStamp.creation_date = new Date();
    	                User buyer = User.find.where().eq("user_mail", createStampForm.field("stamp_buyer").value()).eq("user_status", 1).eq("user_type", 1).findUnique();
    	                if(buyer!= null)
    	                {
    	                	tempStamp.stamp_buyer=buyer.user_mail;
    	                }
    	                else
    	                {
    	                	throw new Exception ("El mail ingresado no corresponde a ningun usuario activo. "+createStampForm.field("stamp_buyer").value());
    	                }
    	                
    	                tempStamp.save();
    	                
    	                String accessKeyId = Play.application().configuration().getString("s3.key.id");
    	                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
    	                String bucketName = Play.application().configuration().getString("s3.bucket.name");
    	                String smallImageFolder = Play.application().configuration().getString("s3.bucket.smallImage");
    	                String largeImageFolder = Play.application().configuration().getString("s3.bucket.largeImage");
    	                
    	                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
    	                AmazonS3Client s3client = new AmazonS3Client(credentials);
    	                
    	                String fileNameSmall = smallImageFolder +"/"+tempStamp.stamp_id+"."+smallImageExtension;
    	                String fileNameLarge = largeImageFolder +"/"+tempStamp.stamp_id+"."+largeImageExtension;
    	                
    	                boolean fileExist = s3client.doesObjectExist(bucketName, fileNameSmall);
    	                if(fileExist)
    	                {
    	                    s3client.deleteObject(bucketName, fileNameSmall);
    	                }
    	                fileExist = s3client.doesObjectExist(bucketName, fileNameLarge);
    	                if(fileExist)
    	                {
    	                    s3client.deleteObject(bucketName, fileNameLarge);
    	                }
    	                s3client.putObject(new PutObjectRequest(bucketName, fileNameSmall, 
    					smallImage).withCannedAcl(CannedAccessControlList.PublicRead));
    					
    					s3client.putObject(new PutObjectRequest(bucketName, fileNameLarge, 
    					largeImage).withCannedAcl(CannedAccessControlList.PublicRead));
    					
    					String smallURL = s3client.getResourceUrl(bucketName, fileNameSmall);
    					String largeURL = s3client.getResourceUrl(bucketName, fileNameLarge);
    					
    	                tempStamp.setStamp_small_image_path(smallURL);  
    	                tempStamp.setStamp_large_image_path(largeURL); 
    	                
    	                tempStamp.update();
    	                
    	            }
    	            else
    	            {
    	                response ="La categoria seleccionada no es valida. " +createStampForm.field("stamp_categoty").value() ;
    	            }
    	            
    	        }
    	        catch (Exception ex)
    	        {
    	            response = ex.getMessage();
    	            System.out.println(ex.getMessage());
    	        }
    	        
    	        return response;
    	    }
    
    public static String updateStamp(Form<UpdateStampForm> updateStampForm,
    Stamp tempStamp,
    String token,
    long userId,
    File smallImage ,String smallImageExtension,
    File largeImage, String largeImageExtension)  
    {
        String response = "";
        try
        {
            Categories tempCategory = Categories.find.where().eq("category_name",updateStampForm.field("stamp_category").value()).findUnique();
            User tempUser = User.find.where().eq("user_id",userId).findUnique();
            if(tempCategory != null && tempUser != null)
            {
                tempStamp.category_id = tempCategory;
                tempStamp.stamp_name = updateStampForm.field("stamp_name").value();
                tempStamp.stamp_short_description = updateStampForm.field("stamp_short_description").value();
                tempStamp.stamp_long_description = updateStampForm.field("stamp_long_description").value();
                double tempPrice = Double.parseDouble(updateStampForm.field("stamp_price").value());
                tempStamp.stamp_price = tempPrice;
                tempStamp.name = updateStampForm.field("stamp_name").value();
                tempStamp.creation_date = new Date();
                tempStamp.update();
                
                String accessKeyId = Play.application().configuration().getString("s3.key.id");
                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
                String bucketName = Play.application().configuration().getString("s3.bucket.name");
                String smallImageFolder = Play.application().configuration().getString("s3.bucket.smallImage");
                String largeImageFolder = Play.application().configuration().getString("s3.bucket.largeImage");
                
                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
                AmazonS3Client s3client = new AmazonS3Client(credentials);
                
                String fileNameSmall = smallImageFolder +"/"+tempStamp.stamp_id+"."+smallImageExtension;
                String fileNameLarge = largeImageFolder +"/"+tempStamp.stamp_id+"."+largeImageExtension;
                
                boolean fileExist = s3client.doesObjectExist(bucketName, fileNameSmall);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileNameSmall);
                }
                fileExist = s3client.doesObjectExist(bucketName, fileNameLarge);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileNameLarge);
                }
                
                Random rand = new Random();
                int  random = rand.nextInt(10000) + 1;
                
                fileNameSmall = smallImageFolder +"/"+tempStamp.stamp_id+"_"+random+"."+smallImageExtension;
                fileNameLarge = largeImageFolder +"/"+tempStamp.stamp_id+"_"+random+"."+largeImageExtension;
                s3client.putObject(new PutObjectRequest(bucketName, fileNameSmall, 
				smallImage).withCannedAcl(CannedAccessControlList.PublicRead));
				
				s3client.putObject(new PutObjectRequest(bucketName, fileNameLarge, 
				largeImage).withCannedAcl(CannedAccessControlList.PublicRead));
				
				String smallURL = s3client.getResourceUrl(bucketName, fileNameSmall);
				String largeURL = s3client.getResourceUrl(bucketName, fileNameLarge);
				
                tempStamp.stamp_small_image_path =  smallURL;
                tempStamp.stamp_large_image_path =  largeURL;
                
                tempStamp.update();
                
            }
            else
            {
                response ="La categoria seleccionada no es valida. " +updateStampForm.field("stamp_categoty").value() ;
            }
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }

    public static List<IStampDto> getStamps()
    {
    	List<Stamp> stamps = Stamp.find.where().eq("stamp_status",1).eq("stamp_buyer","Publica").findList();
    	List<IStampDto> response = new ArrayList<IStampDto>();
    	for(int i = 0 ; i<stamps.size();i++)
    	{
    		StampWrapper tempWrapper = new StampWrapper(stamps.get(i));
    		response.add(tempWrapper.getStampDto());
    	}
    	return response;
    }
    
    public static IStampDto  getStamp(long stampId)
    {
    	Stamp tempStamp = Stamp.find.where().eq("stamp_id", stampId).eq("stamp_buyer", "Publica").findUnique();
    	StampWrapper tempWrapper = new StampWrapper(tempStamp);
    	return tempWrapper.getStampDto();
    }
    
    public static List<IStampDto> getPrivateStamps(long user_id)
    {
    	User tempUser = User.find.where().eq("user_id", user_id).findUnique();
    	List<IStampDto> response = new ArrayList<IStampDto>();
    	if(tempUser != null)
    	{
    		List<Stamp> stamps = Stamp.find.where().eq("stamp_status",1).eq("stamp_buyer", tempUser.user_mail).findList();
    		for(int i = 0; i<stamps.size();i++)
    		{
    			StampWrapper tempWrapper = new StampWrapper(stamps.get(i));
    			response.add(tempWrapper.getStampBasicDto());
    		}
    	}
    	
    	return response;
    }
    
    public static IStampDto  getPrivateStamp(long stampId, long user_id)
    {
    	User tempUser = User.find.where().eq("user_id", user_id).findUnique();
    	Stamp tempStamp = Stamp.find.where().eq("stamp_id", stampId).eq("stamp_buyer", tempUser.user_mail).findUnique();
    	StampWrapper tempWrapper = new StampWrapper(tempStamp);
    	return tempWrapper.getStampBasicDto();
    }
    
    public static RatingDto createRating(CreateRatingDto tempRating, long id)
    {
    	RatingDto response = new RatingDto();
    	User tempUser = User.find.where().eq("user_id", id).findUnique();
    	if(tempUser != null)
    	{
    		StampRating rating = new StampRating();
    		rating.setCreation_date(new Date());
    		rating.rating_status = 1;
    		rating.stamp_comments = tempRating.getStamp_comments();
    		rating.stamp_id = Stamp.find.where().eq("stamp_id", tempRating.getStamp_id()).findUnique();
    		rating.stamp_rating = tempRating.getStamp_rating();
    		rating.user_id = tempUser;
    		rating.save();
    		
    		StampWrapper tempWrapper =  new StampWrapper(rating.stamp_id);
    		tempWrapper.updateRating();
    		
    		
   		 	response.setCreation_date(rating.creation_date);
   		 	response.setRating_id(rating.rating_id);
   		 	response.setStamp_comments(rating.stamp_comments);
   		 	response.setStamp_id(rating.stamp_id.getStamp_id());
   		 	response.setStamp_rating(rating.stamp_rating);
   		 	response.setUser_email(rating.user_id.user_mail);
   		
    	}
    	
    	return response;
    }
    
    
    
}