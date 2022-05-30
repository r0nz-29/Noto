package com.raunits.noto;

public class CSS {
    public static final String stylesheet = new StringBuilder()
            // body
            .append("body {")
            .append("font-size: 16px;")
            .append("font-family: Helvetica;")
            .append("word-wrap: break-word;")
            .append("padding-left: 8px;")
            .append("color: #333;")
            .append("-webkit-font-smoothing: antialiased;")
            .append("}")

            // headings
            .append("h1, h2, h3, h4, h5, h6 { ")
            .append("border-bottom: 1px solid rgba(0, 0, 0, 0.2);")
            .append("display: inline-block;")
            .append("margin-top: 0px;")
            .append("padding-bottom: 4px;")
            .append("margin-bottom: 8px;")
            .append("color: #000;")
            .append(" }")

            // links
            .append("a {")
            .append("color: #4183C4;")
            .append("text-decoration: none;")
            .append("}")

            // code
            .append("code {")
            .append("border: 1px solid #ccc;")
            .append("background-color: #f8f8f8;")
            .append("border-radius: 3px;")
            .append("white-space: nowrap;")
            .append("font-family: JetBrains Mono, Consolas, monospace;")
            .append("}")

            //code block
            .append("pre {")
            .append("background-color: #f8f8f8;")
            .append("border: 1px solid #ccc;")
            .append("padding: 6px 10px;")
            .append("border-radius: 3px;")
    .append("font-family: JetBrains Mono, Consolas, monospace;")
            .append("overflow: auto;")
            .append("margin-right: 16px;")
            .append("}")

            .toString();
}
