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


public final class ShirtAdmin
{
    public static String createShirt(Form<CreateShirtForm> createShirtForm, 
    String token,
    long userId,
    File shirtImage ,
    String shirtImageExtension)  
    {
        String response = "";
        try
        {
            
            User tempUser = User.find.where().eq("user_id",userId).findUnique();
            if(tempUser != null)
            {
                Shirt tempShirt = new Shirt();
                tempShirt.user_id = tempUser;
                tempShirt.shirt_size = createShirtForm.field("shirt_size").value();
                tempShirt.shirt_color = createShirtForm.field("shirt_color").value();
                tempShirt.shirt_sex = createShirtForm.field("shirt_sex").value();
                Double tempDouble = Double.parseDouble(createShirtForm.field("shirt_price").value());
                tempShirt.shirt_price = tempDouble;
                tempShirt.active = true;
                tempShirt.name = createShirtForm.field("shirt_name").value();
                tempShirt.creation_date =  new Date();
                tempShirt.save();
                
                String accessKeyId = Play.application().configuration().getString("s3.key.id");
                String accessKeyPassword = Play.application().configuration().getString("s3.key.password");
                String bucketName = Play.application().configuration().getString("s3.bucket.name");
                String shirtImageFolder = Play.application().configuration().getString("s3.bucket.shirtImage");
                
                
                AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeyPassword);
                AmazonS3Client s3client = new AmazonS3Client(credentials);
                
                String fileNameShirt = shirtImageFolder +"/"+tempShirt.shirt_id+"."+shirtImageExtension;
                
                
                boolean fileExist = s3client.doesObjectExist(bucketName, fileNameShirt);
                if(fileExist)
                {
                    s3client.deleteObject(bucketName, fileNameShirt);
                }
               
                s3client.putObject(new PutObjectRequest(bucketName, fileNameShirt, 
				shirtImage).withCannedAcl(CannedAccessControlList.PublicRead));
				
				
				
				String shirtURL = s3client.getResourceUrl(bucketName, fileNameShirt);
				
				
                tempShirt.shirt_small_image_path = shirtURL;
                tempShirt.shirt_large_image_path =  shirtURL;
                
                tempShirt.update();
                
            }
            else
            {
                response ="Usuario no valido";
            }
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    public static String removeShirt(long id)
    {
        String response = "";
        try
        {
            Shirt tempShirt = Shirt.find.where().eq("shirt_id",id).findUnique();
            tempShirt.active = false;
            tempShirt.update();
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        return response;
    }
    
    public static List<Shirt> getAll()
    {
        List<Shirt> response = null;
            response = Shirt.find.where().eq("active",true).findList();
        return response;
    }
    
    
}