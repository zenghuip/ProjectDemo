package com.jgg.games.model.entity;

import java.io.Serializable;

/**
 * Created by sugl on 2017/3/23 0023.
 */

public class GraphQlModel extends ServerStatus implements Serializable {

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public static int getInt(String string) {
        int result = 0;
        try {
            result = Integer.parseInt(string);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public long getLong(String string) {
        long result = 0;
        try {
            result = Long.parseLong(string);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public double getDouble(String string) {
        double result = 0;
        try {
            result = Double.parseDouble(string);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public static float getFloat(String string) {
        float result = 0;
        try {
            result = Float.parseFloat(string);
        } catch (Exception e) {
            return 0;
        }
        return result;
    }
}
