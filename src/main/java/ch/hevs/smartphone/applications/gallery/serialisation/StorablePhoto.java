package ch.hevs.smartphone.applications.gallery.serialisation;

import ch.hevs.smartphone.applications.contacts.errors.BusinessException;
import ch.hevs.smartphone.applications.gallery.Photo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface StorablePhoto {
    // To read JSON File
    ArrayList<Photo> read() ;

    // To write JSON File
    void write(File destination, ArrayList<Photo> photos) ;
}
