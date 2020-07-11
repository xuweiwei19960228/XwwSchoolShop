package cn.xww.o2o.enums;

public enum ShopStateEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOPID(-1002,"shopid为空"),NULL_SHOP(-1003,"SHOP信息为空");
    private String stateInfo;
    private int state;


    //依据传入的state返回相应的枚举值
    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }

        }
        return null;
    }



    private ShopStateEnum(int state, String stateInfo ){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public int getState() {
        return state;
    }


}
