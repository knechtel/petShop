package com.micheliknechtel.petshop.infrastructure.log;

import com.google.gson.GsonBuilder;

public class StructuredLog {

    public String payload;
    public Action action;

    public StructuredLog(Action action, String payload) {
        this.action = action;
        this.payload = payload;
    }

    public String toJson() {
            return new GsonBuilder().create().toJson(this);
    }
}
