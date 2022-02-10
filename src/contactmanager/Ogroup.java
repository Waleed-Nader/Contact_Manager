package contactmanager;


public class Ogroup {

    private int gId;
    private String groupName;
    private String groupDesc;

    public Ogroup(int gId,String groupName,String groupDesc) {

        this.gId=gId;
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        
    }



    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
        @Override
    public String toString() {
        return gId+" "+ groupName+" "+ groupDesc ;
    }
}
