package Utils.Enums;

/**
 * 枚举类
 */
public enum CertificationTypeEnums {
    ALL("00","全部"),
    IDENTITY_CARD("01","中华人民共和国居民身份证");

    private final String code;
    private final String name;

    CertificationTypeEnums(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static String getName(String value) {
        CertificationTypeEnums[] certificationTypeEnums = values();
        for (CertificationTypeEnums businessModeEnum : certificationTypeEnums) {
            if (businessModeEnum.toString().equals(value)) {
                return businessModeEnum.getName();
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.code;
    }
}
