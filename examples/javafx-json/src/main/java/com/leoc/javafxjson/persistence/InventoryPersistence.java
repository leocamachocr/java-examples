package com.leoc.javafxjson.persistence;

import com.leoc.javafxjson.json.JsonUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import static com.leoc.javafxjson.Resources.BASE_PATH;

public class InventoryPersistence {
    private static final JsonUtil jsonUtil = new JsonUtil();
    private File sourceFile;

    //Inventory -> Map<String,String>
    //JsonUtil
    //File -> Path
    //Tareas:
    // - leer
    // - escribir

    public InventoryPersistence() {
        sourceFile = new File(BASE_PATH + "/inventory.json");
        System.out.println(sourceFile.getAbsolutePath());//informativo
    }

    public List<Map<String, String>> getInventory() {
        try {
            return jsonUtil.asObject(sourceFile.toURI().toURL(), List.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;//TODO: manejar la excepcion para notificar
        }

    }


    public void save(List<Map<String, String>> inventory) {
        jsonUtil.toFile(sourceFile, inventory);
    }
}
