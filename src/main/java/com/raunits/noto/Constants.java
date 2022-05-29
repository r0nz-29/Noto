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

    public static final String HYPERLINK = "\\[(.+)\\]\\((.+)\\)";

    public static final String NEWLINE = "\n";
    public static final String HEADING_BUG = "(</h[1-6]>)<br/>";
    public static final String LIST_BUG = "(</li>)<br/>";
    public static final String LIST = "(\\*)([^\\n]+)";
    public static final String IMAGE = "!\\[(.+)\\]\\((.+)\\)";
    public static final String SIZED_IMAGE = "!\\[(.+)\\]\\((.+)\\)\\(([0-5]*[0-9]*[0-9]*), ([0-5]*[0-9]*[0-9]*)\\)";

    // replacements
    public static final String H1_REPLACEMENT = "<h1>$1</h1>";
    public static final String H2_REPLACEMENT = "<h2>$1</h2>";
    public static final String H3_REPLACEMENT = "<h3>$1</h3>";
    public static final String H4_REPLACEMENT = "<h4>$1</h4>";
    public static final String H5_REPLACEMENT = "<h5>$1</h5>";
    public static final String H6_REPLACEMENT = "<h6>$1</h6>";

    public static final String BOLD_REPLACEMENT = "<b>$1</b>";
    public static final String ITALICS_REPLACEMENT = "<i>$1</i>";
    public static final String PARAGRAPH_REPLACEMENT = "<p>$1</p>";

    public static final String HYPERLINK_REPLACEMENT = "<a href='$2'>$1</a>";
    public static final String IMAGE_REPLACEMENT = "<img src='$2' alt='$1' />";
    public static final String SIZED_IMAGE_REPLACEMENT = "<img src='$2' alt='$1' width='$3' height='$4' />";
    public static final String NEWLINE_REPLACEMENT = "<br/>";
    public static final String HEADING_FIX = "$1";
    public static final String LIST_FIX = "$1";
    public static final String P_REPLACEMENT = "<p>$1</p>";
    public static final String LIST_REPLACEMENT = "<li>$2</li>";
}
