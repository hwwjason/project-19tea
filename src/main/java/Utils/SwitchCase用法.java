package Utils;

public class SwitchCase用法 {
    private String  complementedAddressCode(String addressCode){
        int length = addressCode.length();
        if(length < 12){
            switch (length){
                case 6:
                    addressCode = addressCode.concat("000000");
                    break;
                case 7:
                    addressCode = addressCode.concat("00000");
                    break;
                default:
                    break;
            }
        }
        return addressCode;
    }
}
