package web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class UserManager implements Serializable{

    private String username;
    private String password;
	
    private boolean loginFlag = false;
    private static final Logger logger = Logger.getLogger("web.UserManager");

    public UserManager() {
    }

   
    public boolean isSomeUserAuthenticated() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }       
    
    
    public boolean isUserInRole(String role) {
        return (isSomeUserAuthenticated() &&
                FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role));
    }    
    
    public String clearLogin(){
        if(isSomeUserAuthenticated()){
            return logout();
        }
        
        return "/faces/index_login.xhtml?faces-redirect=true";
    }
	
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.logout();
            System.out.println("dados: " +  this.username + ", " + this.password);
            request.login(this.username, this.password);
        } catch (ServletException e) {
            logger.log(Level.WARNING, e.getMessage());
            return "/faces/error?faces-redirect=true";
            //return "/faces/error?faces-redirect=true";
        }
        
        if(isUserInRole("Administrator")){
            return "/faces/admin/admin_index?faces-redirect=true";
        }
        
        if(isUserInRole("Participant")){
            return "/faces/participant/participant_index?faces-redirect=true"; // TODO
        }        

        if(isUserInRole("Responsible")){
            return "/faces/responsible/responsible_index??faces-redirect=true"; // TODO
        }
      
                
        this.loginFlag = false;
        return "/faces/error?faces-redirect=true";
    }	
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();



        // remove data from beans:
        for (String bean : context.getExternalContext().getSessionMap().keySet()) {
            context.getExternalContext().getSessionMap().remove(bean);
        }

        // destroy session:
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        this.loginFlag = false;
        try {
            //request.login("",""); // When dealing with BASIC authentication
            request.logout();
            this.username = null;
            this.password = null;
        } catch (ServletException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // using faces-redirect to initiate a new request
	return "/faces/index_login.xhtml?faces-redirect=true";
    }
	
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void prepareLogin(){
        loginFlag = true;
    }

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }	
}
