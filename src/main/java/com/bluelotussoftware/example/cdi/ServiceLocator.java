package com.bluelotussoftware.example.cdi;

import javax.ejb.Remote;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

    /**
     * es:
     * java:global/scope-examples-jsf-2-3/EmailCheckSecurity!com.bluelotussoftware.example.boundary.EmailCheckRemote
     * java:app/scope-examples-jsf-2-3/EmailCheckSecurity!com.bluelotussoftware.example.boundary.EmailCheckRemote
     *
     * @param serviceClass ejb implmentation class type
     * @param application war name
     * @param <T>
     * @return
     */
    public static <T> T locateService(Class<T> serviceClass, String application) {
        T res = null;
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            //appname: "scope-examples-jsf-2-3";
            Remote annotation = serviceClass.getAnnotation(Remote.class);
            if (annotation != null) {
                String beanInterfaceSuffix = "!" + annotation.value()[0].getName();
                String jndi = "java:global/" + application + "/" + serviceClass.getSimpleName() + beanInterfaceSuffix;
                res = (T) initialContext.lookup(jndi);
            }else throw new NamingException("Wrong Ejb Remote class");
        } catch (NamingException e) {
            System.err.println("Cannot lookup ejb module " + serviceClass + " - "  + e.getMessage());

        }
        return res;
    }
}