package view.kz.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbLocator {
    public static  <T> T lookUp(Class<T> ejbClass) {
        Context ctx = null;
        T ejb = null;
        try {
            ctx = new InitialContext();
            String lookUpPath = "java:global/redesign/" + ejbClass.getSimpleName();
            ejb = (T)ctx.lookup(lookUpPath);
        }
        catch(NamingException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        finally {
            if(ctx != null) {
                try {
                    ctx.close();
                }
                catch(NamingException e) {
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            }
        }
        return ejb;
    }
}
