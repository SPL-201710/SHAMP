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

public final class CategoriesAdmin{
    
    public static String createCategory(Form<CreateCategoryForm> createCategoryForm)
    {
        String response = "";
        try
        {
            Categories tempCategory = new Categories();
            tempCategory.category_name = createCategoryForm.field("category_name").value();
            tempCategory.category_description_url = createCategoryForm.field("category_description_url").value();
            tempCategory.name = createCategoryForm.field("category_name").value();
            tempCategory.active = true;
            tempCategory.creation_date = new Date();
            tempCategory.save();
            
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        
        return response;
    }
    
    public static String removeCategory(long id)
    {
        String response = "";
        try
        {
            Categories noCategory = Categories.find.where().eq("category_id", -1).findUnique();
            
            Categories tempCategory = Categories.find.where().eq("category_id",id).findUnique();
            tempCategory.active = false;
            tempCategory.update();
            
            List<Stamp> listStamps =  Stamp.find.where().eq("category_id",tempCategory).findList();
            for(int i = 0; i<listStamps.size();i++)
            {
                listStamps.get(i).category_id = noCategory;
                listStamps.get(i).update();
            }
        }
        catch (Exception ex)
        {
            response = ex.getMessage();
        }
        return response;
    }
    
}