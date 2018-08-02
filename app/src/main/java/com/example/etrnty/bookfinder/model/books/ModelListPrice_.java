package com.example.etrnty.bookfinder.model.books;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListPrice_ {

    @SerializedName("amountInMicros")
    @Expose
    private Float amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public Float getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Float amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
