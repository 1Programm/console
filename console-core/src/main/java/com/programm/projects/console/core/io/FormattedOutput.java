package com.programm.projects.console.core.io;

import com.programm.projects.console.core.io.formatters.Formatter;

public interface FormattedOutput extends Output {

    FormattedOutput addFormatter(Formatter formatter);
    FormattedOutput removeFormatter(Formatter formatter);

}
