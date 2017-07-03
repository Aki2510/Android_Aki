

package sg.vinova.aki.webservicejsonretrofit.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindGust {

    @SerializedName("Speed")
    @Expose
    private Speed speed;
    @SerializedName("Direction")
    @Expose
    private Direction direction;

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
