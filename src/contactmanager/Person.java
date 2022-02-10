package contactmanager;


public class Person {

    private int Id;
    private String Firstname;
    private String Lastname;
    private String City;
    private String Phonenumber;

    public Person(int Id,String firstname,String lastname,String city, String phonenumber) {
        this.Id=Id;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.City = city ;
        this.Phonenumber = phonenumber;

    }

    public int getId(){
        return Id;
    }

public String getFirstname() {
       return Firstname;
   }

   public void setFirstname(String firstname) {
       Firstname = firstname;
   }

   public String getLastname() {
       return Lastname;
   }

   public void setLastname(String lastname) {
       Lastname = lastname;
   }

   public String getCity() {
       return City;
   }


   public void setCity(String city) {
       City = city;
   }   

   
   public String getPhonenumber() {
       return Phonenumber;
   }




   public void setPhonenumber(String phonenumber) {
       Phonenumber = phonenumber;
   }
   @Override
   public boolean equals(Object obj) {
       if (obj == null) {
           return false;
       }

       if (obj.getClass() != this.getClass()) {
           return false;
       }

       final Person other = (Person) obj;
       if ((this.Firstname == null) ? (other.Firstname != null) : !this.Firstname.equals(other.Firstname)) {
           return false;
       }

       if (this.City != other.City) {
           return false;
       }

       return true;
   }

    @Override
   public String toString() {
       
       return Firstname+" "+Lastname+" - "+ City ;
   }


}
