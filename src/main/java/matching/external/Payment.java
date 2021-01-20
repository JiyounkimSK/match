package matching.external;

public class Payment {

    private Long matchId;
    private Integer price;
    private String paymentAction;

    public Long getMatchId() {
        return matchId;
    }
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getPaymentAction() {
        return paymentAction;
    }
    public void setPaymentAction(String paymentAction) {
        this.paymentAction = paymentAction;
    }

    /** student 정보 추가 **/
    private String student;
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }

}
