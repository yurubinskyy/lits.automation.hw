package com.lits.rubinskyy.hw3.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lits.rubinskyy.hw3.Models.R;

public class AccessInfo {
    @JsonProperty("r")
    private R r;

    public R getR ()
    {
        return r;
    }

    public void setR (R r)
    {
        this.r = r;
    }

    @Override
    public String toString() {
        return "AccessInfo{" +
                "r=" + r +
                '}';
    }
}
