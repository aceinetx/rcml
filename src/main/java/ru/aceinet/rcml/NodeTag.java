package ru.aceinet.rcml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class NodeTag extends Node {
    public String name;
    public String value;

    public static final String rgb_regex = "(\\d*),\\s?(\\d*),\\s?(\\d*)";

    public NodeTag() {
        name = "";
        value = "";
    }

    public boolean compile(Compiler compiler) {
        compiler.tags.add(this);
        return true;
    }

    public @Nullable Component apply(Compiler compiler, Component component) {
        switch (name) {
            case "color": {
                Pattern pattern = Pattern.compile(rgb_regex);
                Matcher matcher = pattern.matcher(value);

                int r = 0, g = 0, b = 0;
                while (matcher.find()) {
                    if (matcher.groupCount() != 3) {
                        compiler.component = Component
                                .text("RCML03: for tag color: doesn't match, expected 3 groups, found "
                                        + matcher.groupCount());
                        return null;
                    }
                    try {
                        r = Integer.parseInt(matcher.group(1));
                        g = Integer.parseInt(matcher.group(2));
                        b = Integer.parseInt(matcher.group(3));
                    } catch (NumberFormatException e) {
                        compiler.component = Component.text("RCML04: for tag color: " + e.getMessage());
                        return null;
                    }
                }

                return component.color(TextColor.color(r, g, b));
            }
            case "bold":
            case "italic":
            case "obfuscated":
            case "strikethrough":
            case "underlined":
                boolean flag = false;
                TextDecoration decoration = TextDecoration.UNDERLINED;
                switch (name) {
                    case "bold":
                        decoration = TextDecoration.BOLD;
                        break;
                    case "italic":
                        decoration = TextDecoration.ITALIC;
                        break;
                    case "obfuscated":
                        decoration = TextDecoration.OBFUSCATED;
                        break;
                    case "strikethrough":
                        decoration = TextDecoration.STRIKETHROUGH;
                        break;
                    case "underlined":
                        decoration = TextDecoration.UNDERLINED;
                        break;
                }

                if (value.equals("true")) {
                    flag = true;
                } else if (value.equals("false")) {
                    flag = false;
                } else {
                    compiler.component = Component
                            .text(
                                    String.format("RCML02: for tag %s: value should be false|true, found %s",
                                            name,
                                            value));
                    return null;
                }

                return component.decoration(decoration, flag);
            case "reset":
                if (!value.isEmpty()) {
                    compiler.component = Component.text("RCML05: tag reset should not have a value");
                    return null;
                }
                compiler.doReset = true;
                return component;
            default:
                compiler.component = Component.text("RCML01: Unknown tag name: " + name);
                return null;
        }
    }
}
