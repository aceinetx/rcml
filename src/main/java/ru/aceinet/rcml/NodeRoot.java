package ru.aceinet.rcml;

import java.util.ArrayList;

public class NodeRoot extends Node {
    public ArrayList<Node> children;

    public NodeRoot() {
        children = new ArrayList<>();
    }

    public boolean compile(Compiler compiler) {
        for (Node child : children) {
            if (!child.compile(compiler))
                return false;
        }
        return true;
    }
}
