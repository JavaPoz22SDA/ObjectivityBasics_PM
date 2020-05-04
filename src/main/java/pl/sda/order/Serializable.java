package pl.sda.order;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface Serializable {

    void saveOrder(Order z, String fileName);
    Order readOrder(String fileName);

}
