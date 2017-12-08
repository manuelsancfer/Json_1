package com.example.manuelsanchezferna.json;

/**
 * Created by manuel.sanchez.ferna on 01/12/2017.
 */

public class ConsultaResponse {
    private String users;
    private int success;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ConsultaResponse{" +
                "users='" + users + '\'' +
                ", success=" + success +
                '}';
    }
}
