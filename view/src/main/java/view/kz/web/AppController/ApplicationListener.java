package view.kz.web.AppController;

import liquibase.integration.servlet.LiquibaseServletListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private LiquibaseServletListener liquibaseServletListener;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        liquibaseServletListener = new LiquibaseServletListener();
        liquibaseServletListener.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (liquibaseServletListener!=null){
            liquibaseServletListener.contextDestroyed(servletContextEvent);
        }
    }
}
