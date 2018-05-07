package com.bluelotussoftware.example.jsf.carello;

import com.bluelotussoftware.example.cdi.Configuration;
import com.bluelotussoftware.example.cdi.LogLevel;
import com.bluelotussoftware.example.cdi.LoggingMethod;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Named
@ConversationScoped
public class CarelloMBean implements Serializable{

    private LinkedHashMap<Integer,Product> mapAvailableProducts;
    private LinkedHashMap<Integer, Product> mapCartProducts;
    private List<Product> available;
    private List<Product> buyed;

    Logger log = Logger.getLogger(CarelloMBean.class.getName());

    AtomicInteger next = new AtomicInteger();

    @Inject
    private Conversation conversation;

    @Inject
    private Configuration conf;

    public CarelloMBean(){

    }

    @PostConstruct
    public void init(){

        mapAvailableProducts = new LinkedHashMap<Integer, Product>();
        mapCartProducts = new LinkedHashMap<Integer,Product>();
        String simulatedCart = conf.getConf().getProperty("simulatedCart");
        Boolean simulated = simulatedCart != null ? Boolean.valueOf(simulatedCart) : Boolean.FALSE;
        if (simulated) {
            caricaProdottiSim();
        }
        else {
            //load from db
        }
        conversation.begin();
    }

    private void caricaProdottiSim() {
        long id = nextValAtomic();
        Product p1 = new Product(nextValAtomic(), "playstation4", 500D);
        mapAvailableProducts.put(p1.getProductId(), p1);
        Product p2 = new Product(nextValAtomic(), "Fujitsu Intel Core 7", 800D);
        mapAvailableProducts.put(p2.getProductId(), p2);
        Product p3 = new Product(nextValAtomic(), "Borsa da viaggio", 200D);
        mapAvailableProducts.put(p3.getProductId(), p3);
    }

    public List<Product> getAvailable() {
        available= new ArrayList<Product>(mapAvailableProducts.values());
        return available;
    }

    public void setAvailable(List<Product> available) {
    }

    public Collection<Product> getBuyed() {
        buyed = new ArrayList<Product>(mapCartProducts.values());
        return buyed;
    }

    public void setBuyed(List<Product> buyed) {
    }

    @LoggingMethod(LogLevel.INFO)
    public void removeFromCarello(Integer id) {
        if (mapCartProducts.containsKey(id)) {
            Product p = mapCartProducts.remove(id);
            mapAvailableProducts.put(p.getProductId(), p);
        }

    }

    @LoggingMethod(LogLevel.INFO)
    public void addToCarello(Integer id) {
        if (mapAvailableProducts.containsKey(id)) {
            Product p = mapAvailableProducts.remove(id);
            mapCartProducts.put(p.getProductId(),p);
        }
    }

    @LoggingMethod(LogLevel.INFO)
    public String destroy(boolean end){
        String redirectUrl  = "indexProdotti";
        if (end && mapCartProducts.size() > 0){
            log.info("Hai comprato con successo..");
            redirectUrl = "prodottiAcquistati";
        }
        else redirectUrl = "indexProdotti";
        conversation.end();
        return redirectUrl;
    }


    private Integer nextValAtomic() {
        return next.getAndIncrement();
    }



}
