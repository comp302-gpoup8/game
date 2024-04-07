package domain;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is a class that saves the states of objects via serialization and restores them when the program is rerun.
 * I had written this for another project so I'll need to adjust it for this one -- Yilmaz
 */
public class ModManager{

    public String saveLocation;
    public String output;

    public ModManager(String d, String o) {
        this.saveLocation = d;
        this.output = o;
    }

    /**
     * Serilalizes the object and saves it to the savepath (it was stored in the objects in the original project)
     * @param object
     */
    public static void saveModule(Object object) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TODO"));
            out.writeObject(object);
            out.close();
            System.out.println("Successfully saved the object state");
        } catch (IOException fileNotFound) {
            System.out.println("Couldn't save the state.");
        }
    }

    /**
     * Deserializes and restores an object that is in the specified path so the program can use it again.
     * @param modulePath
     * @return
     */
    public static Object restoreModule(String modulePath) {
        try {
            ObjectInputStream mod = new ObjectInputStream(new FileInputStream(modulePath));
            if (mod.readObject() == null) {
                System.out.println("Previous module does not exist.");
                mod.close();
                return null;
            } else {
                Object previous =  mod.readObject();
                mod.close();
                System.out.println("Successfully restored the old module.");
                return previous;
            }
        } catch (IOException e) {
            System.out.println("Couldn't find the object at the specified path.");
        } catch (ClassNotFoundException e) {
            System.out.println("Something went wrong while attempting to read the previous state");
        }
        return null;
    }
}
