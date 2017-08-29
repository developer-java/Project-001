package view.kz.web.Inform;

import view.kz.web.AppController.BundleManager;

public enum RequestType {

    REQUEST_TYPE1(BundleManager.getInterface("request.type1"),"#"),
    REQUEST_TYPE2(BundleManager.getInterface("request.type2"),"#"),
    REQUEST_TYPE3(BundleManager.getInterface("request.type3"),"#"),
    REQUEST_TYPE4(BundleManager.getInterface("request.type4"),"#"),
    REQUEST_TYPE5(BundleManager.getInterface("request.type5"),"#"),
    REQUEST_TYPE6(BundleManager.getInterface("request.type6"),"#"),
    REQUEST_TYPE7(BundleManager.getInterface("request.type7"),"#"),
    REQUEST_TYPE8(BundleManager.getInterface("request.type8"),"#"),
    REQUEST_TYPE9(BundleManager.getInterface("request.type9"),"#"),
    REQUEST_TYPE10(BundleManager.getInterface("request.type10"),"#"),
    ;

    private RequestType(String bundle,String link){
        requestName = bundle;
    }

    private String requestName;
    private String link;
    private String img="../../resources/img/star.png";

    public String getRequestName() {
        return requestName;
    }

    public String getImg() {
        return img;
    }

    public String getLink() {
        return link;
    }
}
