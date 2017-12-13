package view.kz.web;

import view.kz.persistence.SystemUser;
import view.kz.persistence.types.PositionTypes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ServicesMainModel {

    public String getPosition(PositionTypes position){
        switch (position){
            case OPERATOR:{
                return "Оператор";
            }case PRODUCER:{
                return "Продюсер";
            }case INSTALLER:{
                return "Монтажник";
            }case JOURNALIST:{
                return "Журналист";
            }default:{
                return "Администратор Сайта";
            }
        }
    }
}
