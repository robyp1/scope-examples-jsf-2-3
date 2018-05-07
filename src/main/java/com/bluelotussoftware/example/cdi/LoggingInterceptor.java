package com.bluelotussoftware.example.cdi;


import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@LoggingMethod
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor implements Serializable{

    private Logger log = Logger.getLogger(LoggingInterceptor.class.getName());

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        Method method = ic.getMethod();
        LogLevel loglevel2 = null;
        if (method.isAnnotationPresent(LoggingMethod.class))
        {
            LoggingMethod annotation = method.getAnnotation(LoggingMethod.class);
            LogLevel logLevel = annotation.value();
            loglevel2 = logLevel;
            String input= String.format("{%s}: <{%s}> called. Parameters={%s}",
                    ic.getTarget().getClass().getName(),
                    ic.getMethod().getName(),
                    buildString(ic.getParameters()));
            logging(input, logLevel);
        }
        Object proceed=null;
        try {
             proceed = ic.proceed();
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw e;
        }
        if (loglevel2 !=null) {
            String output = String.format("Result of calling {%s}: <{%s}> : %s" ,ic.getTarget().getClass().getName(),
                    ic.getMethod().getName(),proceed!=null ? proceed : "");
            logging(output, loglevel2);
        }
        return proceed;
    }

    private String buildString(Object[] parameters) {
        List<Object> params = Arrays.asList(parameters);
        StringBuilder sb =new StringBuilder();
       for (Object param: params){
            sb.append(param).append(", ");
        }
        String result = sb.toString().trim();
        return (!result.isEmpty()) ? result.substring(0, result.lastIndexOf(",")) : result;
    }

    private void logging(String msg, LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
                log.info(msg);
                break;
            case FINE:
                log.fine(msg);
                break;
            case SEVERE:
                log.severe(msg);
                break;
            case WARNIGN:
                log.warning(msg);
                break;
            case TRACE:
                log.finer(msg);
                break;
        }

    }
}

