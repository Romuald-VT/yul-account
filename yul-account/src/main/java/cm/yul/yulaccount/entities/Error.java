package cm.yul.yulaccount.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    
    private int ErrorCode;
    private String ErrorMessage;
}
