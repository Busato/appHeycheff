package com.example.home.testeheycheff;

import java.io.Serializable;

public class Wand implements Serializable {

    protected String wood;
    protected String core;
    protected String length;

    public Wand(String wood, String core, String length){
        this.wood=wood;
        this.core=core;
        this.length=length;
    }
    public Wand(){

    }

    public String getCore() {
        return core;
    }

    public String getLength() {
        return length;
    }

    public String getWood() {
        return wood;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }
}
