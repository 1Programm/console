package com.programm.projects.console.core.io.formatters;

public interface Formatter {

    /**
     * A method to format some text and some arguments in some way.
     * @param text The text which will be used.
     * @param args The arguments which will be used.
     * @return Returns the formatted text or the input text if nothing was formatted.
     */
    String format(String text, Object... args);

}
