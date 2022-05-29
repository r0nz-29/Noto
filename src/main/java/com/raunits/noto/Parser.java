package com.raunits.noto;

import java.util.HashMap;

public class Parser {
    private final HashMap<String, String> replacements = new HashMap<>();
    private final String[] rules = {
            // headings
            Constants.H6,
            Constants.H5,
            Constants.H4,
            Constants.H3,
            Constants.H2,
            Constants.H1,

            // styles
            Constants.BOLD,
            Constants.ITALICS,

            Constants.SIZED_IMAGE,
            Constants.IMAGE,
            Constants.HYPERLINK,
            Constants.P,
            Constants.LIST,
            Constants.NEWLINE,
            Constants.HEADING_BUG,
            Constants.LIST_BUG
    };

    public Parser() {
        this.populate();
    }

    public String parse(String input) {
        String html = input;

        for(String rule : this.rules) {
            html = html.replaceAll(rule, replacements.get(rule));
        }

        return html;
    }

    private void populate() {

        // headings
        replacements.put(Constants.H6, Constants.H6_REPLACEMENT);
        replacements.put(Constants.H5, Constants.H5_REPLACEMENT);
        replacements.put(Constants.H4, Constants.H4_REPLACEMENT);
        replacements.put(Constants.H3, Constants.H3_REPLACEMENT);
        replacements.put(Constants.H2, Constants.H2_REPLACEMENT);
        replacements.put(Constants.H1, Constants.H1_REPLACEMENT);

        // styles
        replacements.put(Constants.BOLD, Constants.BOLD_REPLACEMENT);
        replacements.put(Constants.ITALICS, Constants.ITALICS_REPLACEMENT);
        replacements.put(Constants.PARAGRAPH, Constants.PARAGRAPH_REPLACEMENT);
        replacements.put(Constants.NEWLINE, Constants.NEWLINE_REPLACEMENT);
        replacements.put(Constants.HEADING_BUG, Constants.HEADING_FIX);
        replacements.put(Constants.LIST_BUG, Constants.LIST_FIX);
        replacements.put(Constants.P, Constants.P_REPLACEMENT);
        replacements.put(Constants.IMAGE, Constants.IMAGE_REPLACEMENT);
        replacements.put(Constants.SIZED_IMAGE, Constants.SIZED_IMAGE_REPLACEMENT);
        // Links
        replacements.put(Constants.HYPERLINK, Constants.HYPERLINK_REPLACEMENT);

        replacements.put(Constants.LIST, Constants.LIST_REPLACEMENT);
    }

}
