package mobile_project.memo;

import java.io.Serializable;

public class MemoObject implements Serializable {

    public String  memoContent;
    public String category ="";
    public String date ="";
    public  MemoObject(String respond){
        String[] res =respond.split("//");

        this.memoContent = res[2];
        if(res[0].equals("")){
            category = res[0];
        }
        if(res[1].equals("")){
            this.date = res[1];
        }
    }


}
