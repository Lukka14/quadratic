import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// using lombok library to add Constructors, getters & setters and override methods: hashCode, toString and equals
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private double re;
    private double im;
}
