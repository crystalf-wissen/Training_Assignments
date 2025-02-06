import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class NashornDemo{
    public static void main(String[] args) {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();

            //ScriptEngine engine = mgr.getEngineByName("Javascript");
            //engine.eval("print('Hello Everyone!')");

            ScriptEngine engine = mgr.getEngineByName("nashorn");
            engine.eval(new FileReader("demo.js"));

            Invocable inv = (Invocable) engine;

            inv.invokeFunction("abc");
            inv.invokeFunction("xyz", 40,50);

            String cname = (String) inv.invokeFunction("greet", "Wissen","Technology");
            System.out.println("Welcome to "+cname);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}