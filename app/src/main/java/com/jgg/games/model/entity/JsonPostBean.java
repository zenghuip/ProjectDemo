package com.jgg.games.model.entity;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class JsonPostBean {
    private String method;
    private SystemParams system;
    private Params params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SystemParams getSystem() {
        return system;
    }

    public void setSystem(SystemParams system) {
        this.system = system;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
