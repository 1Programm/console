package com.programm.projects.console.core.io.formatters;

import com.programm.projects.console.core.io.IO;
import com.programm.projects.console.core.utils.RUID;

public class AlignmentFormatter extends DefaultConfigurableFormatter {

    public static final RUID ENABLED = RUID.Get(IO.RUID_GROUP, "formatter.align.enabled");
    public static final RUID CLEANUP = RUID.Get(IO.RUID_GROUP, "formatter.align.cleanup");

    private final String key;
    private boolean enabled;
    private boolean cleanup;

    public AlignmentFormatter(String key) {
        this.key = key;
    }

    @Override
    public String format(String text, boolean enabled, Object... args) {
        if((!enabled || !this.enabled) && !cleanup) return text;
        if(text == null) return null;

        // EXAMPLES:
        // - "%10<[-](Hello)" -> "Hello-----"
        // - "%10>[+](Bla)"   -> "+++++++Bla"
        // - "%10|(World)"    -> "   World  "

        StringBuilder sb = new StringBuilder();
        int before = 0;
        int index = text.indexOf(key);

        if(index == -1) return text;

        boolean skipped = true;

        while(index != -1){
            sb.append(text, before, index);
            int pos = index + 1;
            int prepos = pos;
            if(pos >= text.length()) break;

            int num;
            char op;
            String fill = " ";
            String content;

            StringBuilder _num = new StringBuilder();
            while(pos < text.length()){
                char c = text.charAt(pos);
                if(Character.isDigit(c)){
                    _num.append(c);
                    pos++;
                }
                else {
                    break;
                }
            }

            if(!_num.isEmpty()){
                num = Integer.parseInt(_num.toString());
                char c = text.charAt(pos);

                if(c == '<' || c == '|' || c == '>'){
                    op = c;
                    pos++;
                    c = text.charAt(pos);
                    if(c == '['){
                        int nextClose = text.indexOf(']', pos);

                        if(nextClose != -1){
                            fill = text.substring(pos + 1, nextClose);
                            pos = nextClose + 1;
                        }
                    }

                    c = text.charAt(pos);
                    if(c == '('){
                        int nextClose = text.indexOf(')', pos);

                        if(nextClose != -1){
                            content = text.substring(pos + 1, nextClose);
                            pos = nextClose + 1;

                            if((!enabled || !this.enabled) && cleanup){
                                sb.append(content);
                            }
                            else {
                                if (op == '<') {
                                    alignLeft(sb, num, fill, content);
                                } else if (op == '|') {
                                    alignMid(sb, num, fill, content);
                                } else {
                                    alignRight(sb, num, fill, content);
                                }
                            }
                            skipped = false;
                        }
                    }
                }
            }

            if(skipped){
                sb.append(key);
                pos = prepos;
            }

            before = pos;
            index = text.indexOf(key, pos);
        }

        if(before < text.length()){
            sb.append(text, before, text.length());
        }

        return sb.toString();
    }

    private void alignLeft(StringBuilder sb, int num, String fill, String content){
        int fillLength = Math.max(0, num - content.length());

        sb.append(content);

        int complete = fillLength / fill.length();
        int rest = fillLength % fill.length();

        sb.append(fill.repeat(complete));
        if(rest > 0){
            sb.append(fill, 0, rest);
        }
    }

    private void alignMid(StringBuilder sb, int num, String fill, String content){
        int fillLength = Math.max(0, num - content.length());
        int fill1 = fillLength / 2;
        int fill2 = fill1 + (fillLength % 2 == 0 ? 0 : 1);

        int complete1 = fill1 / fill.length();
        int rest1 = fill1 % fill.length();

        sb.append(fill.repeat(complete1));
        if(rest1 > 0) sb.append(fill, 0, rest1);

        sb.append(content);

        int complete2 = fill2 / fill.length();
        int rest2 = fill2 % fill.length();

        sb.append(fill.repeat(complete2));
        if(rest2 > 0) sb.append(fill, 0, rest2);
    }

    private void alignRight(StringBuilder sb, int num, String fill, String content){
        int fillLength = Math.max(0, num - content.length());

        int complete = fillLength / fill.length();
        int rest = fillLength % fill.length();

        sb.append(fill.repeat(complete));
        if(rest > 0){
            sb.append(fill, 0, rest);
        }

        sb.append(content);
    }

    @Override
    public AlignmentFormatter restoreConfig() {
        this.enabled = true;
        this.cleanup = false;
        super.restoreConfig();
        return this;
    }

    @Override
    public AlignmentFormatter setConfig(Object key, Object value) {
        if(ENABLED.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.enabled = (boolean) value;
        }
        else if(CLEANUP.equals(key)){
            if(!(value instanceof Boolean)){
                throw new IllegalArgumentException("Illegal argument: " + Boolean.class + " expected: " + value);
            }

            this.cleanup = (boolean) value;
        }

        super.setConfig(key, value);
        return this;
    }

    public AlignmentFormatter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AlignmentFormatter setCleanup(boolean cleanup) {
        this.cleanup = cleanup;
        return this;
    }

}
