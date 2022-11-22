package com.alphabet.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "solve")
public class Solve {
    private int sr;
    private int sc;
    private int er;
    private int ec;

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public int getEr() {
        return er;
    }

    public void setEr(int er) {
        this.er = er;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }

    @Override
    public String toString() {
        return sr + "::" + sc + "::" + er + "::" + ec;
    }

}
