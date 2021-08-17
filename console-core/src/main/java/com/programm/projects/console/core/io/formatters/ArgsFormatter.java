package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;
import com.programm.projects.console.core.utils.RUID;

public class ArgsFormatter extends DefaultConfigurableFormatter {

    public static final RUID ENABLED = RUID.Get(IO.RUID_GROUP, "formatter.args.enabled");
    public static final RUID CLEANUP = RUID.Get(IO.RUID_GROUP, "formatter.args.cleanup");

    private final String key;
    private boolean enabled;
    private boolean cleanup;

    public ArgsFormatter(String key) {
        this.key = key;
    }

    @Override
    public String format(String text, boolean enabled, Object... args) {
        if(!enabled || !this.enabled) return text;
        if(text == null) return null;
        if(args == null) return text;

        StringBuilder sb = new StringBuilder();
        int before = 0;
        int index = text.indexOf(key);
        int c = 0;

        if(index == -1) return text;

        while(index >= 0){
            sb.append(text, before, index);
            String objString = "";

            if(c < args.length) {
                Object obj = args[c];

                if (obj == null) {
                    objString = "" + null;
                } else {
                    objString = obj.toString();
                }
            }
            else if(!cleanup){
                objString = key;
            }

            sb.append(objString);
            index += key.length();
            before = index;

            index = text.indexOf(key, index);
            c++;
        }

        if(before < text.length()){
            sb.append(text, before, text.length());
        }

        return sb.toString();
    }

    @Override
    public ArgsFormatter restoreConfig() {
        this.enabled = true;
        this.cleanup = false;
        super.restoreConfig();
        return this;
    }

    @Override
    public ArgsFormatter setConfig(Object key, Object value) {
        if(ENABLED.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }
        else if(CLEANUP.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.cleanup = (boolean) value;
        }

        super.setConfig(key, value);
        return this;
    }

    public ArgsFormatter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public ArgsFormatter setCleanup(boolean cleanup) {
        this.cleanup = cleanup;
        return this;
    }
}
