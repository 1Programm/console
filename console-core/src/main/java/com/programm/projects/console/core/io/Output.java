package com.programm.projects.console.core.io;

public interface Output {

    void print(String text, Object... args);

    void println(String text, Object... args);

    void newLine();

}
