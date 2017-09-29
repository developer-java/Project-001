package view.kz.ejb;

import view.kz.persistence.SystemSetting;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(mappedName = "ejb/SettingManagementEJB")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class SettingManagement {
    @PersistenceContext(unitName = "sud1401unit")
    private EntityManager em;

    public SystemSetting getSettingByParam(String param) {
        if(param != null) {
            List<SystemSetting> res = em.createQuery("SELECT ss FROM SystemSetting ss WHERE ss.param = :param")
                .setParameter("param", param)
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
            if(res.size() > 0) {
                return res.get(0);
            }
        }
        return null;
    }

    public List<SystemSetting> getSettings() {
        return em.createQuery("SELECT ss FROM SystemSetting ss").getResultList();
    }
}
