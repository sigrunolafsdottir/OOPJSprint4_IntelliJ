package LiveMiniDemo2021;

import java.io.Serializable;

public class Response implements Serializable {

    private String res;

    public String getRes(){
        return res;
    }

    public void setRes(String res){
        this.res=res;
    }
}
