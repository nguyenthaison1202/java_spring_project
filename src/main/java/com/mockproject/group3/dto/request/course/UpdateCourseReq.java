package com.mockproject.group3.dto.request.course;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateCourseReq {
    @NotNull(message = "FIELD_REQUIRED")
    private String title;

    @NotNull(message = "FIELD_REQUIRED")
    private String description;

    @Min(value = 0, message = "INCORRECT_PRICE")
    private double price;

    @NotNull
    private int categoryId;

    public UpdateCourseReq(String title, String description, double price, int categoryId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
