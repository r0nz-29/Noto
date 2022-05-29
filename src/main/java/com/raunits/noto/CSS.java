package com.raunits.noto;

public class CSS {
    public static final String stylesheet = new StringBuilder()
            .append("body { font: 16px Arial; word-wrap: break-word;}")

            .append("h1, h2, h3, h4, h5, h6 { ")
            .append("border-bottom: 2px solid rgba(0, 0, 0, 0.2);")
            .append("display: inline-block;")
            .append("margin-top: 0px;")
            .append("margin-bottom: 8px;")
            .append(" }")

            .append("a {")
            .append("text-decoration: none;")
            .append("}")

            .toString();
}
