package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

public final class SimpleController implements Controller {

    public String nextString = new String();
    public List<String> list = new ArrayList<>();

    public SimpleController() {
        nextString = null;
    }

    public void setString(String string) {
        if(string == null) {
            throw new IllegalArgumentException();
        } else {
            nextString = string;
        }
    }

    public String getString() {
        return nextString;
    
    }

    public List<String> getAllStringPrint() {
        return list;

    }

    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
        } 
        list.add(nextString);
        System.out.println(nextString);
    }
}
  