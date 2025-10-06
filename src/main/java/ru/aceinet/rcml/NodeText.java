package ru.aceinet.rcml;

import net.kyori.adventure.text.Component;

public class NodeText extends Node {
    public String text;

    public NodeText() {
        text = "";
    }

    public boolean compile(Compiler compiler) {
        Component component = Component.text(text);
        for (NodeTag tag : compiler.tags) {
            component = tag.apply(compiler, component);
            if (tag.name.equals("reset")) {
                component = Component.text(text);
            }

            if (component == null)
                return false;
        }
        if (compiler.doReset) {
            compiler.tags.clear();
        }
        compiler.component = compiler.component.append(component);

        return true;
    }
}
