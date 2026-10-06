public class Task{
    private String title;
    private boolean done;
    public Task(String title){
        this.title = title;
        this.done = false;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public boolean isCompleted(){
        return done;
    }
    public void setCompleted(boolean done){
        this.done = done;
    }
    @Override 
    public String toString(){
        if(done){
            return ("[✓]" + title);
        }else{
            return title;
        }
    }
}