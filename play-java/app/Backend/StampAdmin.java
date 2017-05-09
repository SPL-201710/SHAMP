package Backend;

import models.*;
import forms.*;
import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import play.Play;
import java.text.SimpleDateFormat;
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


public final class StampAdmin
{
	
    
    public static List<Stamp> getAllStamp()
    {
        List<Stamp> response = null;
        response = Stamp.find.where().eq("active",true).findList();
        return response;
    }
    
    
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
				
                tempStamp.stamp_small_image_path =  smallURL;
                tempStamp.stamp_large_image_path =  largeURL;
                
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
    
    
    
    
}