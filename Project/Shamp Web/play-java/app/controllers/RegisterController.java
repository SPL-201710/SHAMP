package controllers;

import play.libs.Json;
import play.libs.Json.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.*;
import models.*;
import forms.*;

import views.html.*;
import java.util.*;

import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import play.mvc.Http.*;
import views.html.*;

import Backend.UserAdmin;
import Backend.StampAdmin;

import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

public class RegisterController extends Controller {

    
    public Result registerUser() 
    {
        Form<SignUpForm> signUpForm = Form.form(SignUpForm.class).bindFromRequest();
        String token = session("token");
        String userId = session("user_id");
        
        if(signUpForm.hasErrors())
        {
            return badRequest(register_user.render(signUpForm,token,null));
        }
        else
        {
            String message = "";
            if(UserAdmin.registerUser(signUpForm).equals(""))
            {
                message = "Usuario creado con exito";
            }
            else
            {
                message = "Se ha presentado un error al crear el usuario.";
            }
            
            Form <SignUpForm> signUpForm2 = Form.form(SignUpForm.class);
            return ok(register_user.render(signUpForm2,userId,message));
        }
        
    }
    
    public Result registerArtist() 
    {
        Form<SignUpArtistForm> signUpArtistForm = Form.form(SignUpArtistForm.class).bindFromRequest();
        String token = session("token");
        String userId = session("user_id");
        if(signUpArtistForm.hasErrors())
        {
            return badRequest(register_artist.render(signUpArtistForm,userId,null));
        }
        else
        {
            MultipartFormData body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart imageFile = body.getFile("image");
            File tempFile =  (File) imageFile.getFile();
            
            String fileExtension = imageFile.getFilename();
            String [] tempArray = fileExtension.split("\\.");
            
            if(tempArray.length >0)
            {
                fileExtension = tempArray[tempArray.length -1];
            }
            else
            {
                fileExtension = "";
            }
            
            String message = "";
            
            
            if(UserAdmin.registerArtist(signUpArtistForm,tempFile,fileExtension).equals(""))
            {
                message = "Artista creado con exito";
            }
            else
            {
                message = "Se ha presentado un error al crear el artista.";
            }
            
            Form <SignUpArtistForm> signUpArtistForm2 = Form.form(SignUpArtistForm.class);
            return ok(register_artist.render(signUpArtistForm2,userId,message));
        }
        
    }
    
    public Result loginInUser()
    {
        String token = session("token");
        Form<SignInForm> signInForm = Form.form(SignInForm.class).bindFromRequest();
        if(signInForm.hasErrors())
        {
            return badRequest(login.render(signInForm,null,token,null,null));
        }
        else
        {
            User tempUser =  UserAdmin.loginUser(signInForm);
            if(tempUser != null)
            {
                session("token", ""+tempUser.user_id);
                session("user_id",""+tempUser.user_id);
                if(tempUser.user_type == 1)
                {
                    List<Order> tempOrder = Order.find.where().eq("user_id",tempUser).eq("order_status",1).findList();
                    
                    if(tempOrder == null || tempOrder.size() == 0)
                    {
                        Order tempOrder2 = new Order();
                        tempOrder2.order_status =1;
                        tempOrder2.user_id = tempUser;
                        tempOrder2.save();
                    }
                }
                
                return redirect("/");
            }
            else
            {
                return badRequest(login.render(signInForm,"Usuario o Password incorrecto",token,null,null));
            }
        }
        
    }
    
    public Result logOutUser()
    {
        session().remove("token");
        session().remove("user_id");
        return redirect("/");
    }
    
    public Result createStamp()
    {
        String token = session("token");
        String userId = session("user_id");
        String message = "";
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            Form<CreateStampForm> createStampForm = Form.form(CreateStampForm.class).bindFromRequest();
            Form<CreateStampForm> tempForm = Form.form(CreateStampForm.class);
            List<Categories> listCategories = Categories.getCategories();
            List <String> categoriesNames = new ArrayList<String>();
            for(int i = 0;i<listCategories.size();i++)
            {
                categoriesNames.add(listCategories.get(i).category_name);
            }
            
            if(tempUser.user_type == 2)
            {
                if(createStampForm.hasErrors())
                {
                    return badRequest(create_stamp.render(createStampForm,null,token,tempUser.user_first_name,tempOptions,categoriesNames));
                }
                else
                {
                    MultipartFormData body = request().body().asMultipartFormData();
                    Http.MultipartFormData.FilePart smallImageFile = body.getFile("small_image");
                    File smallTempFile =  (File) smallImageFile.getFile();
                    
                    Http.MultipartFormData.FilePart largeImageFile = body.getFile("large_image");
                    File largeTempFile =  (File) largeImageFile.getFile();
            
                    String smallFileExtension = smallImageFile.getFilename();
                    String largeFileExtension = largeImageFile.getFilename();
                    String [] tempArray = smallFileExtension.split("\\.");
            
                    if(tempArray.length >0)
                    {
                        smallFileExtension = tempArray[tempArray.length -1];
                    }
                    else
                    {
                        smallFileExtension = "";
                    }
                    
                    tempArray = largeFileExtension.split("\\.");
                    
                    if(tempArray.length >0)
                    {
                        largeFileExtension = tempArray[tempArray.length -1];
                    }
                    else
                    {
                        largeFileExtension = "";
                    }
                    
                    message = StampAdmin.createStamp(createStampForm, token,Long.parseLong(userId),smallTempFile,smallFileExtension,largeTempFile,largeFileExtension);
                    
                    if(message.equals(""))
                    {
                        message = "La estampa se ha creado con exito";
                        return ok(create_stamp.render(tempForm,message,token,tempUser.user_first_name,tempOptions,categoriesNames));
                    }
                    else
                    {
                        message = "Se ha presentado un error al crear la estampa " + message;
                        return ok(create_stamp.render(createStampForm,message,token,tempUser.user_first_name,tempOptions,categoriesNames));
                    }
                    
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
    
    public Result addStamp()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            Form<AddStampForm> addStampForm = Form.form(AddStampForm.class).bindFromRequest();
            if(tempUser.user_type == 1)
            {
                Stamp tempStamp = Stamp.find.where().eq("stamp_id",Long.parseLong(addStampForm.field("stamp_id").value())).findUnique();
                if(tempStamp != null)
                {
                    if(addStampForm.hasErrors())
                    {
                        return badRequest(add_stamp_shirt.render(addStampForm,null,tempStamp,StampShirt.getStampLocation(),StampShirt.getStampSize(),token,tempUser.user_first_name,tempOptions));
                    }
                    else
                    {
                        Order tempOrder = Order.find.where().eq("user_id",tempUser).eq("order_status",1).findUnique();
                        if(tempOrder != null)
                        {
                            List<OrderShirt> listShirt = OrderShirt.find.where().eq("order_id",tempOrder).findList();
                            if(listShirt.size()>0)
                            {
                                OrderShirt tempOrderShirt = listShirt.get(0);
                                Shirt tempShirt = tempOrderShirt.shirt_id;
                                
                                StampShirt tempStampShirt = new StampShirt();
                                tempStampShirt.shirt_id = tempShirt;
                                tempStampShirt.stamp_id = tempStamp;
                                tempStampShirt.stamp_size = addStampForm.field("stamp_size").value();
                                tempStampShirt.stamp_location = addStampForm.field("stamp_location").value();
                                tempStampShirt.save();
                                
                                tempShirt.addStampPrice(tempStamp);
                                tempShirt.update();
    
                                
                                return redirect("/viewShoppingCart");
                                
                            }
                            else
                            {
                                Shirt tempShirt = new Shirt();
                                tempShirt.user_id = tempUser;
                                tempShirt.order_id = tempOrder;
                                tempShirt.save();
                                
                                StampShirt tempStampShirt = new StampShirt();
                                tempStampShirt.shirt_id = tempShirt;
                                tempStampShirt.stamp_id = tempStamp;
                                tempStampShirt.stamp_size = addStampForm.field("stamp_size").value();
                                tempStampShirt.stamp_location = addStampForm.field("stamp_location").value();
                                tempStampShirt.save();
                                
                                tempShirt.addStampPrice(tempStamp);
                                tempShirt.update();
                                
                                OrderShirt tempOrderShirt = new OrderShirt();
                                tempOrderShirt.shirt_quantity = 1;
                                tempOrderShirt.order_id = tempOrder;
                                tempOrderShirt.shirt_id = tempShirt;
                                tempOrderShirt.save();
                                return redirect("/viewShoppingCart");
                                
                            }
                        }
                        else
                        {
                            return redirect("/");   
                        }
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
        else
        {
            return redirect("/");
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
            Form<ChangePasswordForm> changePasswordForm = Form.form(ChangePasswordForm.class).bindFromRequest();
            Form<ChangePasswordForm> tempForm = Form.form(ChangePasswordForm.class);
            String message = "";
            message = UserAdmin.changeUserPassword(token, Long.parseLong(userId), changePasswordForm);
            if(message.equals(""))
            {
                message = "Se ha actualizado con el exito el password";
                
                return ok(change_password.render(tempForm,message,token,tempUser.user_first_name,tempOptions));
            }
            else
            {
                return ok(change_password.render(tempForm,message,token,tempUser.user_first_name,tempOptions));
            }
            
        }
        else
        {
            return redirect("/");
        }
    }
    
    public Result removeShirtStamp(long id)
    {
        String token = session("token");
        String userId = session("user_id");
        if(token !=null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            StampShirt tempStampShirt = StampShirt.find.where().eq("stamp_shirt_id",id).findUnique();
            if(tempStampShirt != null)
            {
                
                Shirt tempShirt = tempStampShirt.shirt_id;
                tempShirt.removeStampPrice(tempStampShirt.stamp_id);
                tempShirt.update();
                tempStampShirt.delete();
            }
            
            return redirect("/viewShoppingCart");
        }
        else
        {
            return redirect("/");
        }
    }
    
    public Result cancelOrder()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            if(tempUser.user_type == 1)
            {
                Order tempOrder = Order.find.where().eq("user_id",tempUser).eq("order_status",1).findUnique();
                tempOrder.order_status = 3;
                tempOrder.update();
                
                Order tempOrder2 = new Order();
                tempOrder2.order_status =1;
                tempOrder2.user_id = tempUser;
                tempOrder2.save();
                
                return redirect("/viewShoppingCart");
                
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
    
    
    
    public Result updateUser()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            
            Form<UpdateUserForm> updateUserForm = Form.form(UpdateUserForm.class).bindFromRequest();
            if(updateUserForm.hasErrors())
            {
                return badRequest(update_user.render(updateUserForm,null,token,tempUser.user_first_name,tempOptions,tempUser));
            }
            else
            {
                String message = UserAdmin.updateUserProfile(token, Long.parseLong(userId), updateUserForm);
                
                if(message.equals(""))
                {
                    message = "se ha realizado la actualizacion con exito";
                    return ok(update_user.render(updateUserForm,message,token,tempUser.user_first_name,tempOptions,tempUser));
                }
                else
                {
                    message = "Se ha presentado un error al actualizar el perfil de usuario. "+message;
                    return ok(update_user.render(updateUserForm,message,token,tempUser.user_first_name,tempOptions,tempUser));
                }
            }
            
            
        }
        else
        {
            return redirect("/");
        }
        
    }
    
    
    public Result editShirt()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            List<UserOptions> tempOptions = UserAdmin.getUserGeneralOptions(token,Long.parseLong(userId));
            Form<EditShirtForm> editShirtForm = Form.form(EditShirtForm.class).bindFromRequest();
            Shirt tempShirt = Shirt.find.where().eq("shirt_id",Long.parseLong(editShirtForm.field("shirt_id").value())).eq("user_id",tempUser).findUnique();
            if(tempShirt != null)
            {
                tempShirt.shirt_size = editShirtForm.field("shirt_size").value();
                tempShirt.shirt_color = editShirtForm.field("shirt_color").value();
                tempShirt.shirt_sex = editShirtForm.field("shirt_sex").value();
                tempShirt.update();
                return redirect("/viewShoppingCart");
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
    
    public Result checkOut()
    {
        String token = session("token");
        String userId = session("user_id");
        if(token != null)
        {
            User tempUser = UserAdmin.getUser(token,Long.parseLong(userId));
            if(tempUser.user_type == 1)
            {
                Order tempOrder = Order.find.where().eq("user_id",tempUser).eq("order_status",1).findUnique();
                tempOrder.order_status = 2;
                tempOrder.order_date = new Date();
                tempOrder.update();
                
                Order tempOrder2 = new Order();
                tempOrder2.order_status =1;
                tempOrder2.user_id = tempUser;
                tempOrder2.save();
                
                return redirect("/viewShoppingCart");
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
