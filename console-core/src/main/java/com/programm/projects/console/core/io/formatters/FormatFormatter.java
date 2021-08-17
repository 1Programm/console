package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;
import com.programm.projects.console.core.utils.RUID;

public class FormatFormatter extends DefaultConfigurableFormatter{

    public static final RUID ENABLED = RUID.Get(IO.RUID_GROUP, "formatter.append.enabled");

    private final String format;
    private boolean enabled;

    public FormatFormatter(String format) {
        this.format = format;
    }

    @Override
    protected String format(String text, boolean enabled, Object... args) {
        if(!enabled || !this.enabled) return text;
        if(text == null) return null;

        StringBuilder sb = new StringBuilder();
        int before = 0;
        int index = format.indexOf("{}");

        while(index != -1){
            sb.append(format, before, index);
            sb.append(text);
            before = index + 2;
            index = format.indexOf("{}", before);
        }

        if(before < format.length()){
            sb.append(format, before, format.length());
        }

        return sb.toString();
    }

    @Override
    public FormatFormatter restoreConfig() {
        this.enabled = true;
        super.restoreConfig();
        return this;
    }

    @Override
    public FormatFormatter setConfig(Object key, Object value) {
        if(ENABLED.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }

        super.setConfig(key, value);
        return this;
    }

    public FormatFormatter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
