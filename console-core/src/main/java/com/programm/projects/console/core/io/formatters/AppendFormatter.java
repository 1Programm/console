package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;
import com.programm.projects.console.core.utils.RUID;

public class AppendFormatter extends DefaultConfigurableFormatter {

    public static final RUID ENABLED = RUID.Get(IO.RUID_GROUP, "formatter.append.enabled");

    private final String append;
    private boolean enabled;

    public AppendFormatter(String prepend) {
        this.append = prepend;
    }

    @Override
    protected String format(String text, boolean enabled, Object... args) {
        if(!enabled || !this.enabled) return text;
        if(text == null) return null;

        return append + text;
    }

    @Override
    public AppendFormatter restoreConfig() {
        this.enabled = true;
        super.restoreConfig();
        return this;
    }

    @Override
    public AppendFormatter setConfig(Object key, Object value) {
        if(ENABLED.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }

        super.setConfig(key, value);
        return this;
    }

    public AppendFormatter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
