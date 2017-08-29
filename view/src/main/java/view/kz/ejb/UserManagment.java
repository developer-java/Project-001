package view.kz.ejb;

import view.kz.persistence.SystemUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManagment {

    @PersistenceContext(unitName = "unitBase")
    private EntityManager em;

    public SystemUser getUserById(Long id){
        return em.find(SystemUser.class,id);
    }
    public SystemUser getUserByLoginAndPassword(String login, String pass){
        return (SystemUser) em.createQuery("SELECT u FROM SystemUser u WHERE u.login = :login AND u.password = :password")
                .setParameter("login",login)
                .setParameter("password",pass)
                .getSingleResult();
    }

}
