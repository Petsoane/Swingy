package com.lpetsoan.wtc.validators;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.lpetsoan.wtc.validators.interfaces.DateValid;

public class PhoneBill {
    @DateValid
    private LocalDate date;
    private Double total;
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public Double getTotal(){
        return total;
    }

    public void setTotal(Double total){
        this.total = total;
    }

    public String toString(){
        return "This is just a simple test don jude me..." + total;
    }

    
}
