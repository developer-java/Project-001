package view.kz.ejb;

import view.kz.persistence.Graphics;
import view.kz.persistence.RequestType1;
import view.kz.persistence.SystemUser;
import view.kz.persistence.types.PositionTypes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class UserManagment {

    @PersistenceContext(unitName = "unitBase")
    private EntityManager em;

    public SystemUser getUserById(Long id) {
        return em.find(SystemUser.class, id);
    }

    public SystemUser getUserByIinAndPassword(String login, String pass) {

        return (SystemUser) em.createQuery("SELECT u FROM SystemUser u WHERE u.login = :login AND u.password = :password")
                .setParameter("login", login)
                .setParameter("password", pass)
                .getSingleResult();
    }

    public void saveUser(SystemUser user) {
        em.persist(user);
    }

    public List<SystemUser> getSystemUserIsFree(Date date) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT g.user FROM Graphics g WHERE");
        sb.append(dateHandle(date));
        return em.createQuery(sb.toString()).getResultList();
    }

    public List<SystemUser> getSystemUserIsFreePostion(Date date, PositionTypes positionTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT g.user FROM Graphics g WHERE");
        sb.append(dateHandleAndPostionType(date));
        return em.createQuery(sb.toString())
                .setParameter("pos", positionTypes)
                .getResultList();
    }

    private String dateHandle(Date date) {
        if (date.getHours() >= 8 && date.getHours() < 9) {
            return " (g.A08_00_09_00 = true)";
        } else if (date.getHours() >= 9 && date.getHours() < 10) {
            return " (g.A09_00_10_00 = true)";
        } else if (date.getHours() >= 10 && date.getHours() < 11) {
            return " (g.A10_00_11_00 = true)";
        } else if (date.getHours() >= 11 && date.getHours() < 12) {
            return " (g.A11_00_12_00 = true)";
        } else if (date.getHours() >= 12 && date.getHours() < 13) {
            return " (g.A12_00_13_00 = true)";
        } else if (date.getHours() >= 13 && date.getHours() < 14) {
            return " (g.A13_00_14_00 = true)";
        } else if (date.getHours() >= 14 && date.getHours() < 15) {
            return " (g.A14_00_15_00 = true)";
        } else if (date.getHours() >= 15 && date.getHours() < 16) {
            return " (g.A15_00_16_00 = true)";
        } else if (date.getHours() >= 16 && date.getHours() < 17) {
            return " (g.A16_00_17_00 = true)";
        } else if (date.getHours() >= 17 && date.getHours() < 18) {
            return " (g.A17_00_18_00 = true)";
        } else if (date.getHours() >= 18 && date.getHours() < 19) {
            return " (g.A18_00_19_00 = true)";
        } else if (date.getHours() >= 19 && date.getHours() < 20) {
            return " (g.A19_00_20_00 = true)";
        } else if (date.getHours() >= 20 && date.getHours() < 21) {
            return " (g.A20_00_21_00 = true)";
        } else if (date.getHours() >= 21 && date.getHours() < 22) {
            return " (g.A21_00_22_00 = true)";
        } else if (date.getHours() >= 22 && date.getHours() < 23) {
            return " (g.A22_00_23_00 = true)";
        } else {
            return " (g.user is not null)";
        }
    }

    private String dateHandleAndPostionType(Date date) {
        if (date.getHours() >= 8 && date.getHours() < 9) {
            return " (g.user.postion = :pos and g.A08_00_09_00 = true)";
        } else if (date.getHours() >= 9 && date.getHours() < 10) {
            return " (g.user.postion = :pos and g.A09_00_10_00 = true)";
        } else if (date.getHours() >= 10 && date.getHours() < 11) {
            return " (g.user.postion = :pos and g.A10_00_11_00 = true)";
        } else if (date.getHours() >= 11 && date.getHours() < 12) {
            return " (g.user.postion = :pos and g.A11_00_12_00 = true)";
        } else if (date.getHours() >= 12 && date.getHours() < 13) {
            return " (g.user.postion = :pos and g.A12_00_13_00 = true)";
        } else if (date.getHours() >= 13 && date.getHours() < 14) {
            return " (g.user.postion = :pos and g.A13_00_14_00 = true)";
        } else if (date.getHours() >= 14 && date.getHours() < 15) {
            return " (g.user.postion = :pos and g.A14_00_15_00 = true)";
        } else if (date.getHours() >= 15 && date.getHours() < 16) {
            return " (g.user.postion = :pos and g.A15_00_16_00 = true)";
        } else if (date.getHours() >= 16 && date.getHours() < 17) {
            return " (g.user.postion = :pos and g.A16_00_17_00 = true)";
        } else if (date.getHours() >= 17 && date.getHours() < 18) {
            return " (g.user.postion = :pos and g.A17_00_18_00 = true)";
        } else if (date.getHours() >= 18 && date.getHours() < 19) {
            return " (g.user.postion = :pos and g.A18_00_19_00 = true)";
        } else if (date.getHours() >= 19 && date.getHours() < 20) {
            return " (g.user.postion = :pos and g.A19_00_20_00 = true)";
        } else if (date.getHours() >= 20 && date.getHours() < 21) {
            return " (g.user.postion = :pos and g.A20_00_21_00 = true)";
        } else if (date.getHours() >= 21 && date.getHours() < 22) {
            return " (g.user.postion = :pos and g.A21_00_22_00 = true)";
        } else if (date.getHours() >= 22 && date.getHours() < 23) {
            return " (g.user.postion = :pos and g.A22_00_23_00 = true)";
        } else {
            return " (g.user is not null and g.user.position = :pos)";
        }
    }

    public void updateUser(SystemUser user) {
        em.merge(user);
    }

    public Graphics getGraphicsByUserId(Long id) {
        List<Graphics> graphics = em.createQuery("SELECT g FROM Graphics g where g.user.id = :id")
                .setParameter("id", id)
                .getResultList();
        if (graphics != null || !graphics.isEmpty()) {
            return graphics.get(0);
        }
        return null;
    }

    public void updateGraphics(Graphics graphics){
        em.merge(graphics);
    }

    public void addRequestType1(RequestType1 request){
        if(request.getId()==null){
            em.persist(request);
        }else{
            em.merge(request);
        }
    }

}
