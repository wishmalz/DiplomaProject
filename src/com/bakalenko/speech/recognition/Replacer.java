package com.bakalenko.speech.recognition;

/**
 * Created by Wish on 11.10.2015.
 */
public class Replacer {

    static public String replaceWords(String inputText) {
        StringBuffer result = new StringBuffer(0);
        String[] words = inputText.split("[\\s]");  // split string to array of words by space

        for (String singleUtteredWord : words) {
            switch (singleUtteredWord) {
                case "divide": {
                    result.append("/ ");
                    break;
                }
                case "slash": {
                    result.append("/");
                    break;
                }
                case "multiply":
                case "asterisk": {
                    result.append("* ");
                    break;
                }
                case "at": {
                    singleUtteredWord = "@";
                    result.append(singleUtteredWord);
                    break;
                }
                case "backslash": {
                    result.append("\\ ");
                    break;
                }
                case "caret": {
                    result.append("^ ");
                    break;
                }
                case "colon": {
                    result.append(": ");
                    break;
                }
                case "comma": {
                    result.append(", ");
                    break;
                }
                case "full-stop":
                case "dot": {
                    result.append(".");
                    break;
                }
                case "equals": {
                    result.append("= ");
                    break;
                }
                case "left-parenthesis": {
                    result.append("(");
                    break;
                }
                case "left-square-bracket": {
                    result.append("[");
                    break;
                }
                case "assign": {
                    result.append(":= ");
                    break;
                }
                case "less": {
                    result.append("< ");
                    break;
                }
                case "minus": {
                    result.append("- ");
                    break;
                }
                case "more": {
                    result.append("> ");
                    break;
                }
                case "newline": {
                    result.append("\n");
                    break;
                }
                case "var": {
                    result.append("var\n");
                    break;
                }
                case "begin": {
                    result.append("begin\n");
                    break;
                }
                case "not-equal": {
                    result.append("<> ");
                    break;
                }
                case "plus": {
                    result.append("+ ");
                    break;
                }
                case "procedure": {
                    result.append("Procedure ");
                    break;
                }
                case "function": {
                    result.append("Function ");
                    break;
                }
                case "program": {
                    result.append("Program ");
                    break;
                }
                case "quote": {
                    result.append("'");
                    break;
                }
                case "right-parenthesis": {
                    result.append(")");
                    break;
                }
                case "right-square-bracket": {
                    result.append("]");
                    break;
                }
                case "semicolon": {
                    result.append(";\n");
                    break;
                }
                case "space": {
                    result.append(" ");
                    break;
                }
                case "square": {
                    result.append("sqr");
                    break;
                }
                case "square-root": {
                    result.append("sqrt");
                    break;
                }
                case "tab": {
                    result.append("    ");
                    break;
                }
                default: {
                    result.append(singleUtteredWord + " ");
                    break;
                }
            }
        }

        return result.toString();
    }
}
