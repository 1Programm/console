package com.programm.projects.console.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Readable Unique Identifier
 */
public final class RUID {

    private static final Map<String, Map<String, RUID>> groupMap = new HashMap<>();

    public static RUID Get(String group, String id){
        Map<String, RUID> idMap = groupMap.computeIfAbsent(group, g -> new HashMap<>());

        if(idMap.containsKey(id)){
            throw new IllegalArgumentException("ID: [" + id + "] is already used by GROUP: [" + group + "]!");
        }

        RUID newRUID = new RUID(id);
        idMap.put(id, newRUID);
        return newRUID;
    }

    private final String id;

    private RUID(String id){
        this.id = id;
    }

    public String id(){
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(o.equals(id)) return true;
        return id.equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
