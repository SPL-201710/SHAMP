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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class IndexController extends Controller 
{

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() 
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            String userName = UserAdmin.getUser(token,Long.parseLong(userId)).user_first_name;
            return redirect("/catalog");
    
        }
        else
        {
            return ok(index.render(token,null,null));
        }
        
        
    }
    
    public Result updateProfile() 
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            
            
            User tempUser = User.find.where().eq("user_id",Long.parseLong(userId)).findUnique();
            String userName = tempUser.user_first_name;
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            if(tempUser.user_type == 2)
            {
                ArtistProfile tempProfile = ArtistProfile.find.where().eq("user_id",tempUser).findUnique();
                
                UpdateProfileForm updateProfileForm = new UpdateProfileForm();
                
                updateProfileForm.user_country = tempProfile.user_country;
                updateProfileForm.user_city = tempProfile.user_city;
                updateProfileForm.user_address = tempProfile.user_address;
                updateProfileForm.phone_number = tempProfile.phone_number;
                updateProfileForm.user_profile = tempProfile.user_profile;
                Form<UpdateProfileForm> tempForm = Form.form(UpdateProfileForm.class).fill(updateProfileForm);
                return ok(update_profile.render(tempForm,tempUser,tempProfile.user_image_path,null,token,userName,tempOptions,"updateProfile"));
            }
            return redirect("/");
    
        }
        else
        {
            return redirect("/");
        }
        
        
    }
    
    public Result createCategory()
    {
        
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = User.find.where().eq("user_id",Long.parseLong(userId)).findUnique();
            if(tempUser.user_type == 3)
            {
                List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
                Form<CreateCategoryForm> createCategoryForm2 = Form.form(CreateCategoryForm.class);
                return ok(create_category.render(createCategoryForm2,null,token,tempUser.user_first_name,tempOptions,"createCategory"));
                
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
    
    public Result viewShoppingCart()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = User.find.where().eq("user_id",Long.parseLong(userId)).findUnique();
            if(tempUser.user_type == 1)
            {
                List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
                Order tempOrder = Order.find.where().eq("user_id",tempUser).eq("order_status",1).findUnique();
                Shirt tempShirt = Shirt.find.where().eq("user_id",tempUser).eq("order_id",tempOrder).findUnique();
                List<StampShirt> listStamp = StampShirt.find.where().eq("shirt_id",tempShirt).findList();
                return ok(view_shopping_cart.render(listStamp,tempShirt,token,tempUser.user_first_name,tempOptions));
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
    /*
    public Result showCatalog(long id)
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
            List<Categories> listCategories = Categories.getCategories();
            List<Stamp> listStamp = null;
            if(id != 0)
            {
                boolean control = false;
                Categories tempCategory = null;
                for(int i = 0;i<listCategories.size() && !control;i++)
                {
                    if(listCategories.get(i).category_id == id)
                    {
                        tempCategory = listCategories.get(i);
                        control = true;
                    }
                }
                if(tempUser.user_type == 2)
                {
                    listStamp =  Stamp.find.where().eq("user_id",tempUser).eq("category_id",tempCategory).eq("stamp_status",1).eq("active",true).findList();
                }
                else if(tempUser.user_type == 3)
                {
                    listStamp =  Stamp.find.where().eq("category_id",tempCategory).eq("stamp_status",1).eq("active",true).findList();
                }
                
            }
            else
            {
                if(listCategories.size()>0)
                {
                    Categories tempCategory = listCategories.get(0);
                    if(tempUser.user_type == 2)
                    {
                        listStamp =  Stamp.find.where().eq("user_id",tempUser).eq("category_id",tempCategory).eq("stamp_status",1).eq("active",true).findList();
                    }
                    else if(tempUser.user_type == 3)
                    {
                        listStamp =  Stamp.find.where().eq("category_id",tempCategory).eq("stamp_status",1).eq("active",true).findList();
                    }
                    
                }
                else
                {
                    listStamp = new ArrayList<Stamp>();
                }
            }
            
            return ok(catalog.render(listCategories,listStamp,token,userName,tempOptions,"catalog"));
        }
        
        return redirect("/");
    }
    */
    public Result showCatalog(long id)
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
            List<Categories> listCategories = Categories.getCategories();
            List<Stamp> listStamp = null;
            if(id != 0)
            {
                boolean control = false;
                Categories tempCategory = null;
                for(int i = 0;i<listCategories.size() && !control;i++)
                {
                    if(listCategories.get(i).category_id == id)
                    {
                        tempCategory = listCategories.get(i);
                        control = true;
                    }
                }
                if(tempUser.user_type == 2)
                {
                    listStamp =  Stamp.find.where().eq("user_id",tempUser).eq("stamp_status",1).eq("active",true).findList();
                }
                else if(tempUser.user_type == 3)
                {
                    listStamp =  Stamp.find.where().eq("stamp_status",1).eq("active",true).findList();
                }
                
            }
            else
            {
                if(listCategories.size()>0)
                {
                    Categories tempCategory = listCategories.get(0);
                    if(tempUser.user_type == 2)
                    {
                        listStamp =  Stamp.find.where().eq("user_id",tempUser).eq("stamp_status",1).eq("active",true).findList();
                    }
                    else if(tempUser.user_type == 3)
                    {
                        listStamp =  Stamp.find.where().eq("stamp_status",1).eq("active",true).findList();
                    }
                    
                }
                else
                {
                    listStamp = new ArrayList<Stamp>();
                }
            }
            if(tempUser.user_type == 2)
            {
                return ok(catalog2.render(listStamp,token,userName,tempOptions,"catalog"));
            }
            else
            {
                return ok(catalog3.render(listStamp,token,userName,tempOptions,"catalog"));
            }
            
            
        }
        
        return redirect("/");
    }
    
    
    
    
    
    public Result adminUsers()
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
            if(tempUser.user_type == 3)
            {
                List<User> listUsers =  User.find.where().eq("user_type",1).eq("active",true).eq("user_status",1).findList();
                return ok(admin_users.render(listUsers,null,token,userName,tempOptions,"adminUsers"));
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
    
    public Result createShirt()
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
            if(tempUser.user_type == 3)
            {
                Form<CreateShirtForm> createShirtForm = Form.form(CreateShirtForm.class);
                return ok(create_shirt.render(createShirtForm,Shirt.getShirtColor(),Shirt.getShirtSex(),null,token,tempUser.user_first_name,tempOptions,"createShirt"));
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
    
    public Result adminCategories()
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
            if(tempUser.user_type == 3)
            {
                List<Categories> listCategories =  Categories.find.where().eq("active",true).gt("category_id",0).findList();
                return ok(admin_categories.render(listCategories,null,token,tempUser.user_first_name,tempOptions,"adminCategories"));
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
    
    public Result adminShirts()
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
            if(tempUser.user_type == 3)
            {
                List<Shirt> listShirts =  Shirt.find.where().eq("active",true).findList();
                return ok(admin_shirts.render(listShirts,null,token,tempUser.user_first_name,tempOptions,"adminShirts"));
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
    
    
    
    
    
    
    
    public Result adminArtists()
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
            if(tempUser.user_type == 3)
            {
                List<User> listUsers =  User.find.where().eq("user_type",2).eq("active",true).eq("user_status",1).findList();
                return ok(admin_artists.render(listUsers,null,token,userName,tempOptions,"adminArtists"));
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
    
    
    public Result viewStamp(long id)
    {
        String token = session("token");
        String userId = session("user_id");
        String userName = null;
        List<UserOptions> tempOptions = null;
        List<UserOptions> tempOptionsStamp = null;
        List<UserOptions> tempOptionsArtist = null;
        if(token!= null)
        {
            User tempUser = User.find.where().eq("user_id",Long.parseLong(userId)).findUnique();
            userName = tempUser.user_first_name;
            tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            tempOptionsStamp = UserAdmin.getUserStampOptions(token,Long.parseLong(userId));
            tempOptionsArtist = UserAdmin.getUserArtistOptions(token,Long.parseLong(userId));
        }
        else
        {
            return redirect("/");
        }
        
        List<Categories> listCategories = Categories.getCategories();
        if(id != 0)
        {
            boolean control = false;
            Stamp tempStamp = Stamp.find.where().eq("stamp_id",id).findUnique();
            if(tempStamp !=null)
            {
                User tempArtist = User.find.where().eq("user_id",tempStamp.user_id.user_id).findUnique();
                ArtistProfile tempArtistProfile = ArtistProfile.find.where().eq("user_id",tempArtist).findUnique();
                session("stamp_id", ""+tempStamp.stamp_id);
                return ok(viewStamp.render(listCategories,tempStamp,tempArtist,tempArtistProfile,tempOptionsStamp,tempOptionsArtist,
                token,userName,tempOptions,"catalog"));
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
    
    
    
    
    
    public Result registerUser()
    {
        String user = session("token");
        Form <SignUpForm> signUpForm = Form.form(SignUpForm.class);
        return ok(register_user.render(signUpForm,user,null));
    }
    
    public Result registerArtist()
    {
        String user = session("token");
        Form <SignUpArtistForm> signUpArtistForm = Form.form(SignUpArtistForm.class);
        return ok(register_artist.render(signUpArtistForm,user,null));
    }
    
    public Result login()
    {
        String token = session("token");
        String userId = session("user_id");
        Form <SignInForm> signInForm = Form.form(SignInForm.class);
        if(token != null)
        {
            
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            String userName = UserAdmin.getUser(token,Long.parseLong(userId)).user_first_name;
            return ok(login.render(signInForm,null,token,userName,tempOptions));
    
        }
        else
        {
            return ok(login.render(signInForm,null,token,null,null));
        }
        
    }
    
    public Result changePassword()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            
            Form<ChangePasswordForm> tempForm = Form.form(ChangePasswordForm.class);
            return ok(change_password.render(tempForm,null,token,tempUser.user_first_name,tempOptions,"changePassword"));
        }
        else
        {
            return redirect("/");
        }
    }
    
    public Result updateUser()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            UserBilling tempBilling = UserAdmin.getUserBilling(token, tempUser);
            UpdateUserForm updateForm = new UpdateUserForm();
            updateForm.user_country = tempBilling.user_country;
            updateForm.user_city = tempBilling.user_city;
            updateForm.user_address = tempBilling.user_address;
            updateForm.phone_number = tempBilling.phone_number;
            updateForm.credit_card = tempBilling.user_credit_card;
            updateForm.name_card = tempBilling.name_card;
            Calendar cal = Calendar.getInstance();
            cal.setTime(tempBilling.expiration_date);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            updateForm.expiration_date = day +"/"+month+"/"+year;
            updateForm.cvv = tempBilling.cvv;
            
            Form<UpdateUserForm> tempForm = Form.form(UpdateUserForm.class).fill(updateForm);
            return ok(update_user.render(tempForm,null,token,tempUser.user_first_name,tempOptions,tempUser));
        }
        else
        {
            return redirect("/");
        }
        
    }
    
    public Result addStamp(Long id)
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            if(tempUser.user_type == 1)
            {
                Stamp tempStamp = Stamp.find.where().eq("stamp_id",id).findUnique();
                if(tempStamp != null)
                {
                    AddStampForm tempAddForm = new AddStampForm();
                    tempAddForm.stamp_id = ""+tempStamp.stamp_id;
                    tempAddForm.stamp_name = tempStamp.stamp_name;
                    
                    Form<AddStampForm> tempForm = Form.form(AddStampForm.class).fill(tempAddForm);
                    return ok(add_stamp_shirt.render(tempForm,null,tempStamp,StampShirt.getStampLocation(),StampShirt.getStampSize(),token,tempUser.user_first_name,tempOptions));
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
        else
        {
            return redirect("/");
        }
    }
    
    
    public Result createStamp()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            if(tempUser.user_type == 2)
            {
                Form<CreateStampForm> tempForm = Form.form(CreateStampForm.class);
                List<Categories> listCategories = Categories.getCategories();
                List <String> categoriesNames = new ArrayList<String>();
                for(int i = 0;i<listCategories.size();i++)
                {
                    categoriesNames.add(listCategories.get(i).category_name);
                }
                
                return ok(create_stamp.render(tempForm,null,token,tempUser.user_first_name,tempOptions,categoriesNames,"createStamp"));
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
    
    public Result updateStamp(long id)
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            if(tempUser.user_type == 2)
            {
                Stamp tempStamp = Stamp.find.where().eq("stamp_id",id).findUnique();
                if(tempStamp != null && tempStamp.user_id.equals(tempUser))
                {
                    UpdateStampForm updateStampForm = new UpdateStampForm();
                    updateStampForm.stamp_id = tempStamp.stamp_id;
                    updateStampForm.stamp_name = tempStamp.stamp_name;
                    updateStampForm.stamp_short_description = tempStamp.stamp_short_description;
                    updateStampForm.stamp_long_description = tempStamp.stamp_long_description;
                    updateStampForm.stamp_price = tempStamp.stamp_price;
                    Form<UpdateStampForm> tempForm = Form.form(UpdateStampForm.class).fill(updateStampForm);
                    List<Categories> listCategories = Categories.getCategories();
                    List <String> categoriesNames = new ArrayList<String>();
                    for(int i = 0;i<listCategories.size();i++)
                    {
                        categoriesNames.add(listCategories.get(i).category_name);
                    }
                
                    return ok(update_stamp.render(tempForm,tempStamp,null,token,tempUser.user_first_name,tempOptions,categoriesNames,"catalog"));
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
        else
        {
            return redirect("/");
        }
    }
    
    
    
    
    
    
    
    public Result editShirt(long id)
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            Shirt tempShirt = Shirt.find.where().eq("shirt_id",id).eq("user_id",tempUser).findUnique();
            if(tempShirt != null)
            {
                EditShirtForm editShirtForm = new EditShirtForm();
                editShirtForm.shirt_id = ""+id;
                Form<EditShirtForm> tempForm = Form.form(EditShirtForm.class).fill(editShirtForm);
                return ok(edit_shirt.render(tempForm,Shirt.getShirtSize(),Shirt.getShirtColor(),Shirt.getShirtSex(),null,token,tempUser.user_first_name,tempOptions));
                
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
    
    public Result viewOrders()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser =  UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            List<Order> listOrders = Order.find.where().eq("user_id",tempUser).eq("order_status",2).findList();
            List<OrderShirt> listOrderShirt = new ArrayList<OrderShirt>();
            for(int i = 0; i<listOrders.size();i++)
            {
                OrderShirt tempOrderShirt = OrderShirt.find.where().eq("order_id",listOrders.get(i)).findUnique();
                listOrderShirt.add(tempOrderShirt);
            }
            
            return ok(view_orders.render(listOrders,listOrderShirt,token,tempUser.user_first_name,tempOptions));
        }
        else
        {
            return redirect("/");
        }
    }

}
