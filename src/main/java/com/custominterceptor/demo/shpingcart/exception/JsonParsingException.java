package com.custominterceptor.demo.shpingcart.exception;

public class JsonParsingException extends  RuntimeException{
    public JsonParsingException(String messsage)
    {
        super(messsage);
    }
}
