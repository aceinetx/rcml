package ru.aceinet.rcml;

import net.kyori.adventure.text.Component;

public class RCML {
    public static Component compile(String code, Object... args) {
        Parser parser = new Parser(String.format(code, args));
        Compiler compiler = new Compiler(parser);
        return compiler.compile();
    }
}
