package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;

public abstract class DefaultConfigurableFormatter implements ConfigurableFormatter {

    private boolean globalEnabled;

    public DefaultConfigurableFormatter() {
        restoreConfig();
    }

    protected abstract String format(String text, boolean enabled, Object... args);

    @Override
    public final String format(String text, Object... args) {
        return format(text, globalEnabled, args);
    }

    @Override
    public DefaultConfigurableFormatter restoreConfig() {
        globalEnabled = true;

        return this;
    }

    @Override
    public DefaultConfigurableFormatter setConfig(Object key, Object value) {
        if(IO.FORMATTERS_ENABLE.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.globalEnabled = (boolean) value;
        }

        return this;
    }

    public DefaultConfigurableFormatter setGlobalEnabled(boolean globalEnabled) {
        this.globalEnabled = globalEnabled;
        return this;
    }
}
