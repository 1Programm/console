package com.programm.projects.console.core.io;

import com.programm.projects.console.core.io.formatters.Formatter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFormattedOutput implements FormattedOutput {

    private final List<Formatter> formatters = new ArrayList<>();

    protected abstract void doPrint(String text, Object... args);
    protected abstract void doPrintln(String text, Object... args);

    @Override
    public void print(String text, Object... args) {
        String formattedText = formatText(text, args);

        if(formattedText != null){
            doPrint(formattedText, args);
        }
    }

    @Override
    public void println(String text, Object... args) {
        String formattedText = formatText(text, args);

        if(formattedText != null){
            doPrintln(formattedText, args);
        }
    }

    private String formatText(String text, Object... args){
        for(Formatter formatter : formatters){
            text = formatter.format(text, args);
        }

        return text;
    }

    @Override
    public FormattedOutput addFormatter(Formatter formatter) {
        formatters.add(formatter);
        return this;
    }

    @Override
    public FormattedOutput removeFormatter(Formatter formatter) {
        formatters.remove(formatter);
        return this;
    }
}
