package controllers;

import play.mvc.*;

import views.html.*;

import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import forms.*;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.*;
import Backend.*;
import dto.ReportDto;

public class ReportsController extends Controller 
{
	
	public Result viewCreateReport()
	{
		String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            if(tempUser.user_type == 3)
            {
                Form<CreateReportForm> tempForm = Form.form(CreateReportForm.class);
                List<Categories> listCategories = Categories.getCategories();
                List <String> categoriesNames = new ArrayList<String>();
                for(int i = 0;i<listCategories.size();i++)
                {
                    categoriesNames.add(listCategories.get(i).category_name);
                }
                
                return ok(create_report.render(tempForm,null,token,tempUser.user_first_name,tempOptions,categoriesNames,"createReport"));
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
	
	
	
	public Result createReport() throws Exception
	{
		
		try
		{
			String token = session("token");
	        String userId = session("user_id");
	        String userName = null;
	        List<UserOptions> tempOptions = null;
	        if(token!= null)
	        {
	            User tempUser = User.find.where().eq("user_id",Long.parseLong(userId)).findUnique();
	            userName = tempUser.user_first_name;
	            tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
	            if(tempUser != null)
	            {
	            	Form<CreateReportForm> createReportForm = Form.form(CreateReportForm.class).bindFromRequest();
	                Form<CreateReportForm> tempForm = Form.form(CreateReportForm.class);
	                String artistMail =createReportForm.field("artistMail").value();
	                String userMail = createReportForm.field("userMail").value();
	                String initialDate = createReportForm.field("intialDate").value();
	                String endDate = createReportForm.field("endDate").value();
	                String minValue = createReportForm.field("minValue").value();
	                String maxValue = createReportForm.field("maxValue").value();
	                String stampId =createReportForm.field("stampId").value();
	                boolean boolArtistMail = false;
	                boolean boolUserMail =false;
	                boolean boolInitialDate =false;
	                boolean boolEndDate = false;
	                boolean boolMinValue = false;
	                boolean boolMaxValue = false;
	                boolean boolStampId = false;
	                
	                long longStampId = 0;
	                Date dateInitialDate = new Date();
	                Date dateEndDate = new Date();
	                double doubleMinValue = 0;
	                double doubleMaxValue = 0;
	                
	                if(artistMail !=  null && !artistMail.equals(""))
	                {
	                	boolArtistMail = true;
	                }
	                
	                if(userMail != null && !userMail.equals(""))
	                {
	                	boolUserMail = true;
	                }
	                
	                if(initialDate != null && !initialDate.equals(""))
	                {
	                	boolInitialDate = true;
	                	dateInitialDate= new SimpleDateFormat("dd/MM/yyyy").parse(initialDate);
	                	if(endDate != null && !endDate.equals(""))
	                	{
	                		boolEndDate = true;
	                		dateEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(initialDate);
	                	}
	                }
	                
	                if(minValue!= null && !minValue.equals(""))
	                {
	                	boolMinValue = true;
	                	doubleMinValue = Double.parseDouble(minValue);
	                	if(maxValue != null && !maxValue.equals(""))
	                	{
	                		boolMaxValue = true;
	                		doubleMinValue = Double.parseDouble(maxValue);
	                	}
	                }
	                
	                if(stampId!=null && !stampId.equals(""))
	                {
	                	boolStampId = true;
	                	longStampId = Long.parseLong(stampId);
	                }
	                
	                List<ReportDto> reportData = ReportAdmin.createReport(
	                		boolEndDate, dateInitialDate, dateEndDate, 
	                		boolArtistMail, artistMail, 
	                		boolUserMail, userMail, 
	                		boolStampId, longStampId, 
	                		boolMaxValue, doubleMinValue, doubleMaxValue);
	                
	            	
	                
	                return ok(viewReport.render(reportData,token,userName,tempOptions,"createReport"));
	            	
	            }
	            else
	            {
	            	return redirect("/");
	            }
	            
	        }
	        
	        return redirect("/");
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			System.out.println(ex.toString());
			System.out.println(ex.getStackTrace());
			throw ex;
			
		}
		
        
	}

}
