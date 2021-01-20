package matching;

import javax.persistence.*;

import matching.external.Payment;
import matching.external.PaymentService;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Match_table")
public class Match {

    @Id
    private Long id;
    private Integer price;
    private String status;
    private String student; //학생정보 관리를 위한 student 정보 추가

    @PostPersist
    public void onPostPersist(){
        MatchRequested matchRequested = new MatchRequested();
        BeanUtils.copyProperties(this, matchRequested);
        matchRequested.publishAfterCommit();

        Payment payment = new Payment();

        //변수 setting
        payment.setMatchId(Long.valueOf(this.getId()));
        payment.setPrice(Integer.valueOf(this.getPrice()));
        payment.setStudent(String.valueOf(this.getStudent()));
        payment.setPaymentAction("Approved");

        MatchApplication.applicationContext.getBean(PaymentService.class)
                .paymentRequest(payment);
    }

    @PreUpdate
    public void onPreUpdate(){
        if("cancel".equals(status)) {
            MatchCanceled matchCanceled = new MatchCanceled();
            BeanUtils.copyProperties(this, matchCanceled);
            matchCanceled.publishAfterCommit();
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }


    /** student 정보 추가 **/
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }

}
