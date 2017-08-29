package view.kz.web.Inform;

import view.kz.web.AppController.BundleManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class Inform {

    private List<RequestType> requestForStudents;
    private List<RequestType> requestForTeachers;
    private List<RequestType> requestForWorkers;


    public List<RequestType> getRequestForStudents() {
        requestForStudents = new ArrayList<>();
        requestForStudents.add(RequestType.REQUEST_TYPE1);
        requestForStudents.add(RequestType.REQUEST_TYPE2);
        requestForStudents.add(RequestType.REQUEST_TYPE3);
        requestForStudents.add(RequestType.REQUEST_TYPE4);
        requestForStudents.add(RequestType.REQUEST_TYPE5);
        requestForStudents.add(RequestType.REQUEST_TYPE6);
        requestForStudents.add(RequestType.REQUEST_TYPE10);
        return requestForStudents;
    }

    public List<RequestType> getRequestForTeachers() {
        requestForTeachers = new ArrayList<>();
        requestForTeachers.add(RequestType.REQUEST_TYPE7);
        requestForTeachers.add(RequestType.REQUEST_TYPE8);
        requestForTeachers.add(RequestType.REQUEST_TYPE9);
        return requestForTeachers;
    }

    public List<RequestType> getRequestForWorkers() {
        requestForWorkers = new ArrayList<>();
        requestForWorkers.add(RequestType.REQUEST_TYPE7);
        requestForWorkers.add(RequestType.REQUEST_TYPE8);
        requestForWorkers.add(RequestType.REQUEST_TYPE9);
        return requestForWorkers;
    }
}
