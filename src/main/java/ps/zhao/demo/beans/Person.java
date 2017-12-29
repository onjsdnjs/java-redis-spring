package ps.zhao.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable{

    private String name = null;
    private int age = 0;
}
