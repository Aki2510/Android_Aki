
        package sg.vinova.aki.webservicejsonretrofit.model.entity;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RealFeelTemperature {

    @SerializedName("Minimum")
    @Expose
    private Minimum minimum;
    @SerializedName("Maximum")
    @Expose
    private Maximum maximum;

    public Minimum getMinimum() {
        return minimum;
    }

    public void setMinimum(Minimum minimum) {
        this.minimum = minimum;
    }

    public Maximum getMaximum() {
        return maximum;
    }

    public void setMaximum(Maximum maximum) {
        this.maximum = maximum;
    }

}