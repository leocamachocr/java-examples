package com.leoc.javafxjson.persistence;

import com.leoc.javafxjson.domain.Catalog;
import com.leoc.javafxjson.json.JsonUtil;

import java.io.File;
import java.net.MalformedURLException;

import static com.leoc.javafxjson.config.Resources.BASE_PATH;

public class CatalogPersistenceFile implements CatalogPersistence {
    private static final JsonUtil jsonUtil = new JsonUtil();
    private File sourceFile;
    //Catalog
    //JsonUtil
    //File -> Path
    //Tareas:
    // - leer
    // - escribir

    public CatalogPersistenceFile() {
        sourceFile = new File(BASE_PATH + "/catalog.json");
        System.out.println(sourceFile.getAbsolutePath());//informativo
    }

    public Catalog getCatalog() {
        try {
            return jsonUtil.asObject(sourceFile.toURI().toURL(), Catalog.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;//TODO: manejar la excepcion para notificar
        }

    }

    public void save(Catalog catalog) {
        jsonUtil.toFile(sourceFile, catalog);
    }
}
