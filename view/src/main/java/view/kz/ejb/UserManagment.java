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

    public SystemUser getUserByIinAndPassword(String iin, String pass){
        return (SystemUser) em.createQuery("SELECT u FROM SystemUser u WHERE u.iin = :login AND u.password = :password")
                .setParameter("login",iin)
                .setParameter("password",pass)
                .getSingleResult();
    }

}
