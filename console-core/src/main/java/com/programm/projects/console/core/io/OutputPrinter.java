package com.programm.projects.console.core.io;

import com.programm.projects.console.core.Configurable;
import com.programm.projects.console.core.io.formatters.Formatter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OutputPrinter extends AbstractFormattedOutput implements AdvancedOutput {

    private final PrintStream out;
    private final List<Configurable> configurables = new ArrayList<>();

    // Configs
    private boolean enabled;

    public OutputPrinter(PrintStream out) {
        this.out = out;
        restoreConfig();
    }

    @Override
    protected void doPrint(String text, Object... args) {
        if(!enabled) return;
        out.print(text);
    }

    @Override
    protected void doPrintln(String text, Object... args) {
        if(!enabled) return;
        out.println(text);
    }

    @Override
    public void newLine() {
        if(!enabled) return;
        out.println();
    }

    @Override
    public OutputPrinter addFormatter(Formatter formatter) {
        if(formatter instanceof Configurable){
            configurables.add((Configurable) formatter);
        }

        super.addFormatter(formatter);
        return this;
    }

    @Override
    public FormattedOutput removeFormatter(Formatter formatter) {
        if(formatter instanceof Configurable){
            configurables.remove(formatter);
        }

        super.removeFormatter(formatter);
        return this;
    }

    @Override
    public Configurable restoreConfig() {
        this.enabled = true;

        for(Configurable config : configurables){
            config.restoreConfig();
        }

        return this;
    }

    @Override
    public OutputPrinter setConfig(Object key, Object value) {
        for(Configurable config : configurables){
            config.setConfig(key, value);
        }

        if(IO.OUT_ENABLE.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }

        return this;
    }
}
