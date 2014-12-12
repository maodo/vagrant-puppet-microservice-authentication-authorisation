package com.example.addressfinder.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Address {

    @NotNull
    @JsonProperty
    private String postcode;

    @NotNull
    @JsonProperty
    private List<String> lines;

    public Address() {}

    public Address(String postcode, String... lines) {
        this.postcode = postcode;
        this.lines = new ArrayList<>();
        this.lines.addAll(Arrays.asList(lines));
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (lines != null ? !lines.equals(address.lines) : address.lines != null) return false;
        if (postcode != null ? !postcode.equals(address.postcode) : address.postcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postcode != null ? postcode.hashCode() : 0;
        result = 31 * result + (lines != null ? lines.hashCode() : 0);
        return result;
    }
}
