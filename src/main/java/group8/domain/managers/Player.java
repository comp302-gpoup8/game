package group8.domain.managers;

import java.io.Serializable;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Player implements Serializable {

    public Integer remainingLives;
    public Integer score;
    public String name;

}