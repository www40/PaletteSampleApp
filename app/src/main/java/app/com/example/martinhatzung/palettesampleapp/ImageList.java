
package app.com.example.martinhatzung.palettesampleapp;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ImageList{
    @Expose public List<Data> data;
    @Expose public Number status;
    @Expose public boolean success;
}
