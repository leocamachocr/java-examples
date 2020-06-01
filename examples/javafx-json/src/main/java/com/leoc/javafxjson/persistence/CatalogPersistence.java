package com.leoc.javafxjson.persistence;

import com.leoc.javafxjson.domain.Catalog;
import com.leoc.javafxjson.json.JsonUtil;

import java.io.File;
import java.net.MalformedURLException;

public class CatalogPersistence {
    JsonUtil jsonUtil = new JsonUtil();
    private static String basePath = "c:\\repository\\";//cambiar esta ruta por la que mejor le convenga

    public Catalog getCatalog(String name) throws MalformedURLException {
        File file = new File(basePath + name + ".json");
        return jsonUtil.asObject(file.toURI().toURL(), Catalog.class);

    }
    public void saveCatalog(Catalog catalog, String name){
        File file = new File(basePath + name + ".json");
        jsonUtil.toFile(file,catalog);
    }
}
