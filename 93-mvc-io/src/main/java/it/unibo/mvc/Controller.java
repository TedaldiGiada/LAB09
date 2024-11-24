package it.unibo.mvc;

import java.util.List;

public interface Controller {
    void setString(String string);

    String getString();

    List<String> getAllStringPrint();

    void printCurrentString();

}
