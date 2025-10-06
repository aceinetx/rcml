package ru.aceinet.rcml;

import java.util.ArrayList;

import net.kyori.adventure.text.Component;

public class Compiler {
    protected Parser parser;
    protected Component component;
    protected ArrayList<NodeTag> tags;
    protected boolean doReset;

    public Compiler(Parser parser) {
        this.parser = parser;
        this.component = Component.text("");
        this.tags = new ArrayList<>();
        this.doReset = false;
    }

    public Component compile() {
        if (this.parser.root == null)
            this.parser.parse();
        this.parser.root.compile(this);
        return this.component;
    }
}
