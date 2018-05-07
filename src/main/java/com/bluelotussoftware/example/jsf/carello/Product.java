package com.bluelotussoftware.example.jsf.carello;

import java.io.Serializable;

public class Product implements Serializable, Comparable{

    private Integer productId;
    private String tipoProdotto;
    private Double prezzo;

    public Product(){
        productId = null;
        tipoProdotto = "";
        Double prezzo = 0D;
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String tipoProdotto, Double prezzo) {
        this.productId = productId;
        this.tipoProdotto = tipoProdotto;
        this.prezzo = prezzo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public void setTipoProdotto(String tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return (getProductId().equals(product.getProductId())) ;
    }

    @Override
    public int hashCode() {
        int result = getProductId().hashCode();
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", tipoProdotto='" + tipoProdotto + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return getProductId().compareTo(((Product)o).productId);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Product(productId, tipoProdotto, prezzo);
    }
}
