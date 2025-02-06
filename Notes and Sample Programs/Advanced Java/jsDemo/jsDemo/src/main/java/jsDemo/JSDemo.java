package jsDemo;

import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class JSDemo{
    public static void main(String[] args) {
        try {

            ScriptEngineManager mgr = new ScriptEngineManager();
            List<ScriptEngineFactory> list = mgr.getEngineFactories();
            list.forEach(sef -> System.out.println(sef.getEngineName()));
 
            ScriptEngine engine = mgr.getEngineByName("graal.js");
            engine.eval(new FileReader("demo.js"));
 
            Invocable inv = (Invocable) engine;
            inv.invokeFunction("abc");
            inv.invokeFunction("add", 10, 20);
            String name = (String)
            inv.invokeFunction("greet", "Wissen","Technology");
 
            System.out.println("Welcome to " + name);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}