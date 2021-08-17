package com.programm.projects.console.core;

import com.programm.projects.console.core.io.OutputPrinter;
import com.programm.projects.console.core.io.formatters.AlignmentFormatter;
import com.programm.projects.console.core.io.formatters.ArgsFormatter;

public class Main {

    public static void main(String[] args) {
        OutputPrinter out = new OutputPrinter(System.out)
                .addFormatter(new ArgsFormatter("{}"))
                .addFormatter(new AlignmentFormatter("%"))
                .setConfig(ArgsFormatter.ENABLED, true)
                .setConfig(AlignmentFormatter.ENABLED, true)
                .setConfig(AlignmentFormatter.CLEANUP, true);

        int size = 20;
        out.println("|%{}<[-](Hello)|", size);
        out.println("|%{}>[+](Bla)|", size);
        out.println("|%{}|(World)|", size);

    }

}
