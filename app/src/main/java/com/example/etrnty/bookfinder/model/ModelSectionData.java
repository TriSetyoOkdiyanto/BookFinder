package com.example.etrnty.bookfinder.model;

import com.example.etrnty.bookfinder.model.books.ModelItem;

import java.util.List;

public class ModelSectionData {
    private String headerTitle;
    private List<ModelItem> allItemsInSection;

    public ModelSectionData() {
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<ModelItem> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(List<ModelItem> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
