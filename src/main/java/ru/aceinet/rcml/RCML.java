package ru.aceinet.rcml;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RCML extends JavaPlugin {
    public static Component compile(String code, Object... args) {
        Parser parser = new Parser(String.format(code, args));
        Compiler compiler = new Compiler(parser);
        return compiler.compile();
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(compile("Thank you for using [bold:true][color:255,0,0]RCML[reset]!"));
    }
}
