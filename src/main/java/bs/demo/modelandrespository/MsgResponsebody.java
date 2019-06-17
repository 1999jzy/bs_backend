package bs.demo.modelandrespository;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class MsgResponsebody {
    private int Status;
    private String Msg;
    public MsgResponsebody(int Status,String Msg){
        this.Status = Status;
        this.Msg = Msg;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
