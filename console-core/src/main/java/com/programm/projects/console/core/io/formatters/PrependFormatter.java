package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;
import com.programm.projects.console.core.utils.RUID;

public class PrependFormatter extends DefaultConfigurableFormatter{

    public static final RUID ENABLED = RUID.Get(IO.RUID_GROUP, "formatter.prepend.enabled");

    private final String prepend;
    private boolean enabled;

    public PrependFormatter(String prepend) {
        this.prepend = prepend;
    }

    @Override
    protected String format(String text, boolean enabled, Object... args) {
        if(!enabled || !this.enabled) return text;
        if(text == null) return null;

        return prepend + text;
    }

    @Override
    public PrependFormatter restoreConfig() {
        this.enabled = true;
        super.restoreConfig();
        return this;
    }

    @Override
    public PrependFormatter setConfig(Object key, Object value) {
        if(ENABLED.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }

        super.setConfig(key, value);
        return this;
    }

    public PrependFormatter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
