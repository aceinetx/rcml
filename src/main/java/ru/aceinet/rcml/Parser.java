package ru.aceinet.rcml;

public class Parser {
    protected String code;
    protected int pos;

    protected NodeRoot root;

    public Parser(String code) {
        this.code = code;
    }

    protected Node parseTag() {
        NodeTag node = new NodeTag();
        boolean onValue = false;

        pos++;
        while (pos < code.length()) {
            char c = this.code.charAt(pos);
            if (node.value.isEmpty() && node.name.isEmpty() && c == '[') {
                NodeText text = new NodeText();
                text.text = "[";
                return text;
            }

            if (c == ':' && !onValue) {
                pos++;
                onValue = true;
                continue;
            } else if (c == ']') {
                break;
            }

            if (!onValue) {
                node.name += c;
            } else {
                node.value += c;
            }

            pos++;
        }
        node.value = node.value.trim();
        return node;
    }

    protected Node parseText() {
        NodeText node = new NodeText();
        while (pos < code.length()) {
            char c = code.charAt(pos);
            if (c == '[') {
                pos--;
                break;
            }

            node.text += c;

            pos++;
        }
        node.text = node.text.replaceAll("]]", "]");

        return node;
    }

    public void parse() {
        root = new NodeRoot();
        while (pos < code.length()) {
            char c = this.code.charAt(pos);
            if (c == '[') {
                Node node = parseTag();
                root.children.add(node);
            } else {
                Node node = parseText();
                root.children.add(node);
            }
            pos++;
        }
    }
}
