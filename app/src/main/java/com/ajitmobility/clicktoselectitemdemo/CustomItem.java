package com.ajitmobility.clicktoselectitemdemo;

/**
 * Created by Ajitsharma on 9/23/2016.
 */

public class CustomItem implements Listable {

    int    id;
    String name;

    public CustomItem(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getLabel() {
        return name;
    }

}
