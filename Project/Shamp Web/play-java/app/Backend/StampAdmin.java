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
                tempStamp.save();
                
                tempStamp.stamp_small_image_path =  "stampSmallImage/"+tempStamp.stamp_id+"."+smallImageExtension;
                tempStamp.stamp_large_image_path =  "stampLargeImage/"+tempStamp.stamp_id+"."+largeImageExtension;
                
                tempStamp.update();
                
                //try
                //{
                    
                    FileUtils.moveFile(smallImage, new File("public/stampSmallImage", tempStamp.stamp_id+"."+smallImageExtension));
                    FileUtils.moveFile(largeImage, new File("public/stampLargeImage", tempStamp.stamp_id+"."+largeImageExtension));
                //}
                //catch (Exception ex)
                //{
                    
                //}
                
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
    
}