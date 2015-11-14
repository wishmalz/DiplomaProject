package com.bakalenko.speech.recognition;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by Wish on 11.10.2015.
 */
public class Replacer {

    static public String replaceWords(String inputText) {
        StringBuffer result = new StringBuffer(0);
        String[] words = inputText.split("[\\s]");  // split string to array of words by space

        for (String singleUtteredWord : words) {
            switch (singleUtteredWord) {
                case "and": {
                    result.append("and ");
                    break;
                }
                case "array": {
                    result.append("array ");
                    break;
                }
                case "asm": {
                    result.append("asm ");
                    break;
                }
                case "assign": {
                    result.append(":= ");
                    break;
                }
                case "asterisk": {
                    result.append("*");
                    break;
                }
                case "at": {
                    result.append("@");
                    break;
                }
                case "backslash": {
                    result.append("\\");
                    break;
                }
                case "begin": {
                    result.append("Begin\n");
                    break;
                }
                case "boolean": {
                    result.append("boolean ");
                    break;
                }
                case "byte": {
                    result.append("byte ");
                    break;
                }
                case "caret": {
                    result.append("^");
                    break;
                }
                case "case": {
                    result.append("case ");
                    break;
                }
                case "char": {
                    result.append("char ");
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
                case "const": {
                    result.append("const ");
                    break;
                }
                case "constructor": {
                    result.append("Constructor ");
                    break;
                }
                case "destructor": {
                    result.append("Destructor ");
                    break;
                }
                case "div": {
                    result.append("div");
                    break;
                }
                case "divide": {
                    result.append("/ ");
                    break;
                }
                case "do": {
                    result.append("do ");
                    break;
                }
                case "dot": {
                    result.append(".");
                    break;
                }
                case "double": {
                    result.append("double ");
                    break;
                }
                case "downto": {
                    result.append("downto ");
                    break;
                }
                case "else": {
                    result.append("else ");
                    break;
                }
                case "end": {
                    result.append("end");
                    break;
                }
                case "equals": {
                    result.append("= ");
                    break;
                }
                case "export": {
                    result.append("export ");
                    break;
                }
                case "extended": {
                    result.append("extended ");
                    break;
                }
                case "false": {
                    result.append("false");
                    break;
                }
                case "file": {
                    result.append("file ");
                    break;
                }
                case "for": {
                    result.append("for ");
                    break;
                }
                case "full-stop": {
                    result.append(".");
                    break;
                }
                case "function": {
                    result.append("Function ");
                    break;
                }
                case "goto": {
                    result.append("goto ");
                    break;
                }
                case "if": {
                    result.append("if ");
                    break;
                }
                case "implementation": {
                    result.append("implementation ");
                    break;
                }
                case "in": {
                    result.append("in ");
                    break;
                }
                case "inherited": {
                    result.append("inherited ");
                    break;
                }
                case "inline": {
                    result.append("inline ");
                    break;
                }
                case "integer": {
                    result.append("integer ");
                    break;
                }
                case "interface": {
                    result.append("Interface ");
                    break;
                }
                case "label": {
                    result.append("label ");
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
                case "less": {
                    result.append("< ");
                    break;
                }
                case "library": {
                    result.append("library ");
                    break;
                }
                case "minus": {
                    result.append("- ");
                    break;
                }
                case "mod": {
                    result.append("mod");
                    break;
                }
                case "more": {
                    result.append("> ");
                    break;
                }
                case "multiply": {
                    result.append("* ");
                    break;
                }
                case "name": {
                    result.append("name");
                    break;
                }
                case "new": {
                    result.append("new ");
                    break;
                }
                case "newline": {
                    result.append("\n");
                    break;
                }
                case "nil": {
                    result.append("nil");
                    break;
                }
                case "not": {
                    result.append("not ");
                    break;
                }
                case "not-equal": {
                    result.append("<> ");
                    break;
                }
                case "object": {
                    result.append("object ");
                    break;
                }
                case "of": {
                    result.append("of ");
                    break;
                }
                case "or": {
                    result.append("or ");
                    break;
                }
                case "packed": {
                    result.append("packed ");
                    break;
                }
                case "pi": {
                    result.append("pi");
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
                case "program": {
                    result.append("Program ");
                    break;
                }
                case "quote": {
                    result.append("'");
                    break;
                }
                case "random": {
                    result.append("random");
                    break;
                }
                case "randomize": {
                    result.append("randomize");
                    break;
                }
                case "read": {
                    result.append("read");
                    break;
                }
                case "real": {
                    result.append("real ");
                    break;
                }
                case "record": {
                    result.append("record ");
                    break;
                }
                case "repeat": {
                    result.append("repeat ");
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
                case "set": {
                    result.append("set ");
                    break;
                }
                case "shl": {
                    result.append("shl");
                    break;
                }
                case "shr": {
                    result.append("shr");
                    break;
                }
                case "single": {
                    result.append("single ");
                    break;
                }
                case "slash": {
                    result.append("/");
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
                case "string": {
                    result.append("string ");
                    break;
                }
                case "tab": {
                    result.append("    ");
                    break;
                }
                case "then": {
                    result.append("then ");
                    break;
                }
                case "to": {
                    result.append("to ");
                    break;
                }
                case "true": {
                    result.append("true");
                    break;
                }
                case "type": {
                    result.append("type ");
                    break;
                }
                case "unit": {
                    result.append("unit ");
                    break;
                }
                case "until": {
                    result.append("until ");
                    break;
                }
                case "uses": {
                    result.append("uses ");
                    break;
                }
                case "value": {
                    result.append("value ");
                    break;
                }
                case "var": {
                    result.append("var\n");
                    break;
                }
                case "variable": {
                    result.append("variable ");
                    break;
                }
                case "while": {
                    result.append("while");
                    break;
                }
                case "with": {
                    result.append("with");
                    break;
                }
                case "word": {
                    result.append("word ");
                    break;
                }
                case "write": {
                    result.append("write ");
                    break;
                }
                case "xor": {
                    result.append("xor ");
                    break;
                }

                default: {
                    result.append(singleUtteredWord);
                    break;
                }
            }
        }

        return result.toString();
    }
}
