package com.leoc.javafxjson.persistence;

import com.leoc.javafxjson.domain.Catalog;

public interface CatalogPersistence {
    Catalog getCatalog();

    void save(Catalog catalog);
}
