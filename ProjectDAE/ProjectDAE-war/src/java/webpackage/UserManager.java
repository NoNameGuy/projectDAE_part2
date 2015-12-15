package webpackage;

//import entidades.GrupoUtilizador.GRUPO;
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
public class UserManager implements Serializable {

    private String username;
    private String password;

    private boolean loginFlag = true;
    private static final Logger logger = Logger.getLogger("webpackage.UserManager");

    public UserManager() {
    }

    /**
     * Verifica se existe algum utilizador autenticado.
     *
     * @return true se há algum utilizador autenticado e, falso em caso
     * contrário.
     */
    public boolean isSomeUserAuthenticated() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    /**
     * Verifica se o utilizador atual pertence a determinado role. Não é
     * utilizado neste projeto.
     *
     * @param role a verificar
     * @return boolean true se o utilizador atual pertencer ao role e false em
     * caso contrário.
     */
    public boolean isUserInRole(String role) {
        return (isSomeUserAuthenticated()
                && FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role));
    }

    public String tratarLoginErrado() {
        if (isSomeUserAuthenticated()) {
            logout();
        }

        // return "index.xhtml?faces-redirect=true"; //Usar esta linha se a página inicial for a página index
        return "index_login.xhtml?faces-redirect=true"; //Usar esta linha se a página inicial for a página index_login
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            System.out.println("UserManager: " + this.username + ", " + this.password);
            request.login(this.username, this.password);
        } catch (ServletException e) {
            logger.log(Level.WARNING, e.getMessage());
            return "error?faces-redirect=true";
        }

        if (isUserInRole("Administrator")) {
            return "/faces/admin/admin_index?faces-redirect=true";
        }
        if (isUserInRole("Responsible")) {
            return "faces/responsible/estudante_ucs_listar?faces-redirect=true";
        }
        //O if já não era necessário
        if (isUserInRole("Participant")) {
            return "faces/participant/participant_index?faces-redirect=true";
        }

        //!!!!!!!!!!!!
        return "Nunca chega aqui";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();

        // remove data from beans:
        for (String bean : context.getExternalContext().getSessionMap().keySet()) {
            context.getExternalContext().getSessionMap().remove(bean);
        }

        // destroy session:
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();

        // using faces-redirect to initiate a new request:
        //return "/index.xhtml?faces-redirect=true"; //Usar esta linha se a página inicial for a página index
        return "/index_login.xhtml?faces-redirect=true"; //Usar esta linha se a página inicial for a página index_login
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

    public void prepareLogin() {
        loginFlag = true;
    }

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String clearLogin() {
        if (isSomeUserAuthenticated()) {
            logout();
        }
        return "index_login.xhtml?faces-redirect=true";
    }

}
