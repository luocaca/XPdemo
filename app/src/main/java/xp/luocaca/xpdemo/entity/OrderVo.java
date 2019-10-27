package xp.luocaca.xpdemo.entity;

import java.util.List;

public class OrderVo {

    /**
     * amount : fk/fWgJNecs=
     * pageTotal : 1
     * tradeNum : J0+Qy64k42E=
     * orderList : [{"paySt":"2","amount":"0.20","settleAmtStr":"未知","orderTime":"21:31:15","orderId":"00005415720966753307910092","orderTp":"1","payImg":"http://nxt.nongxinyin.com/fvp-qp/headImages/icons/icon-zhifubao.png"},{"paySt":"2","amount":"1.00","settleAmtStr":"未知","orderTime":"12:30:18","orderId":"00005415720642182735020687","orderTp":"1","payImg":"http://nxt.nongxinyin.com/fvp-qp/headImages/icons/icon-zhifubao.png"}]
     * retCode : 0000
     * retMsg : 成功
     * orgid : 007434
     * outlayAmount : 0.00
     * currentPage : 1
     * seq : 20191027215031000010
     * funCode : 10008
     * sign : sZd8KjNBJeWS4csv+754GRwKX7/cadj2zh76izD5DDpj2uI3mQMC0Q==
     */

    public String amount;
    public String pageTotal;
    public String tradeNum;
    public String retCode;
    public String retMsg;
    public String orgid;
    public String outlayAmount;
    public String currentPage;
    public String seq;
    public String funCode;
    public String sign;
    public List<OrderListbean> orderList;

    public static class OrderListbean {
        /**
         * paySt : 2
         * amount : 0.20
         * settleAmtStr : 未知
         * orderTime : 21:31:15
         * orderId : 00005415720966753307910092
         * orderTp : 1
         * payImg : http://nxt.nongxinyin.com/fvp-qp/headImages/icons/icon-zhifubao.png
         */

        public String paySt;
        public String amount;
        public String settleAmtStr;
        public String orderTime;
        public String orderId;
        public String orderTp;
        public String payImg;
    }
}
