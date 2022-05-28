package com.raunits.noto;

public class Constants {
    // regexes
    public static final String H1 = "#\\s?([^\n]+)";
    public static final String H2 = "#{2}\\s?([^\n]+)";
    public static final String H3 = "#{3}\\s?([^\n]+)";
    public static final String H4 = "#{4}\\s?([^\n]+)";
    public static final String H5 = "#{5}\\s?([^\n]+)";
    public static final String H6 = "#{6}\\s?([^\n]+)";
    public static final String BOLD = "\\*\\*\\s?([^\\n]+)\\*\\*";
    public static final String ITALICS = "_([^_`]+)_";
    public static final String PARAGRAPH = "([^\\n]+\\n?)";
    public static final String P = "~\\s?([^\n]+)";

    public static final String HYPERLINK = "\\[([^\\]]+)\\]\\(([^)]+)\\)";

    public static final String NEWLINE = "\n";
    public static final String HEADING_BUG = "(</h[1-6]>)<br/>";
    public static final String LIST_BUG = "(</li>)<br/>";
    public static final String LIST = "(\\*)([^\\n]+)";

    // replacements
    public static final String H1_REPLACEMENT = String.format("<h1 style='%s'>$1</h1>", CSS.heading);
    public static final String H2_REPLACEMENT = String.format("<h2 style='%s'>$1</h2>", CSS.heading);
    public static final String H3_REPLACEMENT = String.format("<h3 style='%s'>$1</h3>", CSS.heading);
    public static final String H4_REPLACEMENT = String.format("<h4 style='%s'>$1</h4>", CSS.heading);
    public static final String H5_REPLACEMENT = String.format("<h5 style='%s'>$1</h5>", CSS.heading);
    public static final String H6_REPLACEMENT = String.format("<h6 style='%s'>$1</h6>", CSS.heading);

    public static final String BOLD_REPLACEMENT = "<b>$1</b>";
    public static final String ITALICS_REPLACEMENT = "<i>$1</i>";
    public static final String PARAGRAPH_REPLACEMENT = String.format("<p style='%s'>$1</p>", CSS.wordWrap);

    public static final String HYPERLINK_REPLACEMENT = String.format("<a href='$2' style='%s'>$1</a>", CSS.wordWrap);

    public static final String NEWLINE_REPLACEMENT = "<br/>";
    public static final String HEADING_FIX = "$1";
    public static final String LIST_FIX = "$1";
    public static final String P_REPLACEMENT = String.format("<p style='%s'>$1</p>", CSS.wordWrap);
    public static final String LIST_REPLACEMENT = "<li>$2</li>";
}
